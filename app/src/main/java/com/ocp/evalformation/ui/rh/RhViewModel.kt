package com.ocp.evalformation.ui.rh

import androidx.lifecycle.*
import com.ocp.evalformation.data.local.entity.*
import com.ocp.evalformation.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class RhViewModel @Inject constructor(
    private val repo: MainRepository
) : ViewModel() {

    // ── LiveData ───────────────────────────────────────────────────────────────
    val allThemes         = repo.themeDao.getAllLive()
    val allFlms           = repo.flmDao.getAllLive()
    val allCollaborateurs = repo.collaborateurDao.getAllLive()
    val allFormations     = repo.formationDao.getAllLive()
    val allInvitations    = repo.invitationDao.getAllLive()
    val pendingInvitations = repo.invitationDao.getPendingLive()
    val pendingCount       = repo.invitationDao.countPendingLive()

    // ── FormationWithInvitation ────────────────────────────────────────────────
    val formationsWithStatus: LiveData<List<FormationWithInvitation>> =
        MediatorLiveData<List<FormationWithInvitation>>().also { mediator ->
            fun refresh() {
                val formations  = allFormations.value  ?: return
                val invitations = allInvitations.value ?: emptyList()
                val invMap      = invitations.associateBy { it.formationId }
                mediator.value  = formations.map { formation ->
                    FormationWithInvitation(formation, invMap[formation.id])
                }
            }
            mediator.addSource(allFormations)  { refresh() }
            mediator.addSource(allInvitations) { refresh() }
        }

    // ── End-of-month detection ─────────────────────────────────────────────────
    val isEndOfMonth: Boolean
        get() {
            val cal     = Calendar.getInstance()
            val today   = cal.get(Calendar.DAY_OF_MONTH)
            val lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
            return today >= lastDay - 2 // last 3 days of month
        }

    // ── Search / Filter state ──────────────────────────────────────────────────
    private val _searchQuery  = MutableLiveData<String>("")
    private val _filterService = MutableLiveData<String?>(null)
    private val _filterStatut  = MutableLiveData<InvitationStatus?>(null)

    val searchQuery: LiveData<String>           = _searchQuery
    val filterService: LiveData<String?>        = _filterService
    val filterStatut: LiveData<InvitationStatus?> = _filterStatut

    fun setSearch(query: String)              { _searchQuery.value  = query }
    fun setFilterService(service: String?)    { _filterService.value = service }
    fun setFilterStatut(statut: InvitationStatus?) { _filterStatut.value = statut }
    fun clearFilters() {
        _searchQuery.value  = ""
        _filterService.value = null
        _filterStatut.value  = null
    }

    // Filtered list — empty filters = show nothing; end-of-month = show all pending
    val filteredFormations: LiveData<List<FormationWithInvitation>> =
        MediatorLiveData<List<FormationWithInvitation>>().also { mediator ->
            fun refresh() {
                val all     = formationsWithStatus.value ?: emptyList()
                val query   = _searchQuery.value?.trim()?.lowercase() ?: ""
                val service = _filterService.value
                val statut  = _filterStatut.value

                val filtersEmpty = query.isEmpty() && service == null && statut == null

                mediator.value = when {
                    // End of month → show all that are not yet answered
                    isEndOfMonth -> all.filter { it.status != InvitationStatus.REPONDUE }

                    // Filters empty → show nothing (default state)
                    filtersEmpty -> emptyList()

                    // Filters active → apply them
                    else -> all.filter { item ->
                        val f = item.formation
                        val matchesQuery = query.isEmpty() ||
                            f.collaborateurMatricule.lowercase().contains(query) ||
                            item.invitation?.nomCompletCollaborateur?.lowercase()?.contains(query) == true ||
                            item.invitation?.themeNom?.lowercase()?.contains(query) == true
                        val matchesService = service == null ||
                            item.invitation?.service?.equals(service, ignoreCase = true) == true ||
                            f.division.equals(service, ignoreCase = true)
                        val matchesStatut = statut == null || item.status == statut
                        matchesQuery && matchesService && matchesStatut
                    }
                }
            }
            mediator.addSource(formationsWithStatus) { refresh() }
            mediator.addSource(_searchQuery)         { refresh() }
            mediator.addSource(_filterService)       { refresh() }
            mediator.addSource(_filterStatut)        { refresh() }
        }

    // ── Check & update statuses ────────────────────────────────────────────────
    fun checkAndUpdateInvitationStatuses() {
        viewModelScope.launch {
            try {
                val formations = repo.formationDao.getAll()
                formations.forEach { formation ->
                    val inv = repo.invitationDao.getByFormationId(formation.id)
                    if (inv != null && inv.statut == InvitationStatus.NON_EXPEDIEE) {
                        repo.invitationDao.updateStatut(inv.id, InvitationStatus.EN_ATTENTE)
                    }
                }
            } catch (e: Exception) { /* ignore */ }
        }
    }

    // ── Theme state ────────────────────────────────────────────────────────────
    sealed class ThemeState {
        object Idle    : ThemeState()
        object Loading : ThemeState()
        data class Success(val theme: ThemeEntity) : ThemeState()
        data class Error(val message: String)      : ThemeState()
    }

    private val _themeState = MutableStateFlow<ThemeState>(ThemeState.Idle)
    val themeState: StateFlow<ThemeState> = _themeState

    fun addTheme(nom: String, objectifPedagogique: String) {
        if (nom.isBlank() || objectifPedagogique.isBlank()) {
            _themeState.value = ThemeState.Error("Veuillez remplir tous les champs.")
            return
        }
        viewModelScope.launch {
            _themeState.value = ThemeState.Loading
            val result = repo.addTheme(nom.trim(), objectifPedagogique.trim())
            _themeState.value = if (result.isSuccess)
                ThemeState.Success(result.getOrNull()!!)
            else
                ThemeState.Error(result.exceptionOrNull()?.message ?: "Erreur")
        }
    }

    fun resetThemeState() { _themeState.value = ThemeState.Idle }

    // ── Invitation state ───────────────────────────────────────────────────────
    sealed class InvitationState {
        object Idle    : InvitationState()
        object Sending : InvitationState()
        data class Sent(val invitation: InvitationFlmEntity) : InvitationState()
        data class SentAll(val count: Int)                   : InvitationState()
        data class Error(val message: String)                : InvitationState()
    }

    private val _invitationState = MutableStateFlow<InvitationState>(InvitationState.Idle)
    val invitationState: StateFlow<InvitationState> = _invitationState

    // Send single invitation from FormationWithInvitation
    fun sendFormToFlm(item: FormationWithInvitation) {
        viewModelScope.launch {
            _invitationState.value = InvitationState.Sending
            try {
                val formation = item.formation
                val collab    = repo.collaborateurDao.getByMatricule(formation.collaborateurMatricule)
                if (collab == null) {
                    _invitationState.value = InvitationState.Error("Collaborateur introuvable : ${formation.collaborateurMatricule}")
                    return@launch
                }
                val flm = collab.flmMatricule?.let { repo.flmDao.getByMatricule(it) }
                if (flm == null) {
                    _invitationState.value = InvitationState.Error("FLM introuvable pour : ${collab.matricule}")
                    return@launch
                }
                val theme  = repo.themeDao.getById(formation.themeId)
                val result = repo.sendEvaluationFormToFlm(
                    collaborateur = collab,
                    formation     = formation,
                    theme         = theme,
                    flmEmail      = flm.email,
                    flmNom        = "${flm.prenom} ${flm.nom}"
                )
                _invitationState.value = if (result.isSuccess)
                    InvitationState.Sent(result.getOrNull()!!)
                else
                    InvitationState.Error(result.exceptionOrNull()?.message ?: "Erreur d'envoi")
            } catch (e: Exception) {
                _invitationState.value = InvitationState.Error(e.message ?: "Erreur")
            }
        }
    }

    // Send from collaborateur + formation params (backward compatibility)
    fun sendFormToFlm(
        collaborateur: CollaborateurEntity,
        formation: FormationEntity,
        flmEmail: String,
        flmNom: String
    ) {
        viewModelScope.launch {
            _invitationState.value = InvitationState.Sending
            val theme  = repo.themeDao.getById(formation.themeId)
            val result = repo.sendEvaluationFormToFlm(collaborateur, formation, theme, flmEmail, flmNom)
            _invitationState.value = if (result.isSuccess)
                InvitationState.Sent(result.getOrNull()!!)
            else
                InvitationState.Error(result.exceptionOrNull()?.message ?: "Erreur d'envoi")
        }
    }

    // Send all pending (end-of-month)
    fun sendAllInvitations() {
        viewModelScope.launch {
            _invitationState.value = InvitationState.Sending
            val formations = filteredFormations.value
                ?.filter { it.status == InvitationStatus.NON_EXPEDIEE }
                ?.map { it.formation }
                ?: emptyList()
            val result = repo.sendAllPendingInvitations(formations)
            _invitationState.value = if (result.isSuccess)
                InvitationState.SentAll(result.getOrNull() ?: 0)
            else
                InvitationState.Error(result.exceptionOrNull()?.message ?: "Erreur envoi global")
        }
    }

    // ── Helpers ────────────────────────────────────────────────────────────────
    fun verifierMatricule(matricule: String, onResult: (CollaborateurEntity?) -> Unit) {
        viewModelScope.launch { onResult(repo.collaborateurDao.getByMatricule(matricule)) }
    }

    fun getFormationByMatricule(matricule: String, onResult: (FormationEntity?) -> Unit) {
        viewModelScope.launch { onResult(repo.formationDao.getByCollaborateur(matricule).firstOrNull()) }
    }

    fun deleteAllData() {
        viewModelScope.launch { repo.deleteAllData() }
    }

    fun syncToFirebase() {
        viewModelScope.launch { repo.syncPendingToFirebase() }
    }
}

// ── Join model ─────────────────────────────────────────────────────────────────
data class FormationWithInvitation(
    val formation: FormationEntity,
    val invitation: InvitationFlmEntity?
) {
    val status: InvitationStatus
        get() = invitation?.statut ?: InvitationStatus.NON_EXPEDIEE
}

// ── Extensions ─────────────────────────────────────────────────────────────────
private fun Long.toYear(): String {
    val cal = Calendar.getInstance()
    cal.timeInMillis = this
    return cal.get(Calendar.YEAR).toString()
}

private fun Long.toMonth(): String {
    val cal = Calendar.getInstance()
    cal.timeInMillis = this
    return String.format("%02d", cal.get(Calendar.MONTH) + 1)
}
