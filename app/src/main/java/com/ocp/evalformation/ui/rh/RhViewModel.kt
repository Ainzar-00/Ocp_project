package com.ocp.evalformation.ui.rh

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.work.workDataOf
import com.ocp.evalformation.com.ocp.evalformation.BackgroundWork.AppreciationDateWorker
import com.ocp.evalformation.data.local.entity.*
import com.ocp.evalformation.data.repository.EvaluationRepository
import com.ocp.evalformation.data.repository.MainRepository
import com.ocp.evalformation.utils.dateHelper
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject
@HiltViewModel
class RhViewModel @Inject constructor(
    val repo: MainRepository,
    private val evaluationRepo: EvaluationRepository,
    private  val application: Application

) : AndroidViewModel(application) {

    init {

        repo.firebase.listenToInvitations(viewModelScope) { invitations ->
            viewModelScope.launch {
                invitations.forEach { invitation ->
                    val existing = repo.invitationDao.getByFormationId(invitation.formationId)
                    if (existing != null) {
                        // Update status if changed
                        if (existing.statut != invitation.statut) {
                            repo.invitationDao.update(existing.copy(statut = invitation.statut))
                        }
                    }
                }
            }
        }

        // ── Sync evaluations from Firestore on app start ──────────────
        viewModelScope.launch {
            evaluationRepo.listenToEvaluations(
                onAdded = { eval ->
                    viewModelScope.launch { evaluationRepo.syncToRoom(eval) }
                },
                onModified = { eval ->
                    viewModelScope.launch { evaluationRepo.syncToRoom(eval) }
                }
            )
        }

    }



    // ── LiveData ───────────────────────────────────────────────────────────────
    val allThemes         = repo.themeDao.getAllLive()
    val allFlms           = repo.flmDao.getAllLive()
    val allCollaborateurs = repo.collaborateurDao.getAllLive()
    val allFormations     = repo.formationDao.getAllLive()
    val allInvitations    = repo.invitationDao.getAllLive()
    val pendingInvitations = repo.invitationDao.getPendingLive()
    val pendingCount       = repo.invitationDao.countPendingLive()

    private val yearRange = dateHelper.currentYearExcelRange()

//     Total collaborateurs (all time)
    val totalCollaborateurs: LiveData<Int> =
        repo.collaborateurDao.countLive()

    // Collaborateurs with at least one formation in current year
    val collaborateursWithFormation: LiveData<Int> =
        repo.formationDao.countCollaborateursWithFormationByYear(yearRange.first, yearRange.second)

    // Distinct themes in formations in current year
    val distinctThemesCount: LiveData<Int> =
        repo.formationDao.countDistinctThemesByYear(yearRange.first, yearRange.second)

    // Total evaluations (all time)
    val totalEvaluations: LiveData<Int> =
        repo.evaluationDao.countLive()

    // En attente invitations count
    val enAttenteCount: LiveData<Int> =
        repo.invitationDao.countEnAttenteLive()

    // JSP sum where presence = true in current year
    val totalJsp: LiveData<Double?> =
        repo.formationDao.sumJspByYear(yearRange.first, yearRange.second)

    // Most recurrent theme name in current year
    val mostRecurrentTheme: LiveData<String?> =
        repo.formationDao.getMostRecurrentThemeIdByYear(yearRange.first, yearRange.second)
            .switchMap { themeId ->
                if (themeId != null) {
                    repo.themeDao.getByIdLive(themeId).map { it?.nom }
                } else {
                    MutableLiveData<String?>(null)
                }
            }

    fun sendAllByFormationIds(ids: List<Long>) {
        viewModelScope.launch {
            _invitationState.value = InvitationState.Sending
            val formations = ids.mapNotNull { repo.formationDao.getById(it) }
            val result = repo.sendAllPendingInvitations(formations)
            _invitationState.value = if (result.isSuccess)
                InvitationState.SentAll(result.getOrNull() ?: 0)
            else
                InvitationState.Error(result.exceptionOrNull()?.message ?: "Erreur")
        }
    }

    fun testAppreciationWorker() {
        viewModelScope.launch {
            val inputData = workDataOf("IS_TESTING" to true)

            val request = OneTimeWorkRequestBuilder<AppreciationDateWorker>()
                .setInputData(inputData)
                .build()

            WorkManager.getInstance(getApplication())
                .enqueue(request)

            Log.d("WorkerTest", "✅ OneTime worker enqueued with IS_TESTING=true")
        }
    }

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
            return today >= lastDay - 2
        }

    // ── Search / Filter state ──────────────────────────────────────────────────
    private val _searchQuery   = MutableLiveData<String>("")
    private val _filterService = MutableLiveData<String?>(null)
    private val _filterStatut  = MutableLiveData<InvitationStatus?>(null)
    private val _filterTheme   = MutableLiveData<String?>(null)

    val searchQuery  : LiveData<String>            = _searchQuery
    val filterService: LiveData<String?>           = _filterService
    val filterStatut : LiveData<InvitationStatus?> = _filterStatut
    val filterTheme  : LiveData<String?>           = _filterTheme

    fun setSearch(query: String)                   { _searchQuery.value   = query   }
    fun setFilterService(service: String?)         { _filterService.value = service }
    fun setFilterStatut(statut: InvitationStatus?) { _filterStatut.value  = statut  }
    fun setFilterTheme(theme: String?)             { _filterTheme.value   = theme   }

    fun clearFilters() {
        _searchQuery.value   = ""
        _filterService.value = null
        _filterStatut.value  = null
        _filterTheme.value   = null
    }

    // ── Filtered formations ────────────────────────────────────────────────────
    val filteredFormations: LiveData<List<FormationWithInvitation>> =
        MediatorLiveData<List<FormationWithInvitation>>().also { mediator ->
            fun refresh() {
                val all     = formationsWithStatus.value ?: emptyList()
                val query   = _searchQuery.value?.trim()?.lowercase() ?: ""
                val service = _filterService.value
                val statut  = _filterStatut.value
                val theme   = _filterTheme.value

                val filtersEmpty = query.isEmpty() &&
                        service == null &&
                        statut  == null &&
                        theme   == null

                Log.d("InvFilter", "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
                Log.d("InvFilter", "total    : ${all.size}")
                Log.d("InvFilter", "query    : '$query'")
                Log.d("InvFilter", "service  : $service")
                Log.d("InvFilter", "statut   : $statut")
                Log.d("InvFilter", "theme    : $theme")
                Log.d("InvFilter", "empty    : $filtersEmpty")
                Log.d("InvFilter", "eom      : $isEndOfMonth")

                mediator.value = when {
                    isEndOfMonth -> {
                        val result = all.filter { it.status != InvitationStatus.REPONDUE }
                        Log.d("InvFilter", "END_OF_MONTH → ${result.size}")
                        result
                    }

                    filtersEmpty -> {
                        Log.d("InvFilter", "EMPTY → 0")
                        emptyList()
                    }

                    else -> {
                        val result = all.filter { item ->
                            val f = item.formation

                            // ── Query ──────────────────────────────────────────
                            val matchesQuery = query.isEmpty() ||
                                    f.collaborateurMatricule.lowercase().contains(query) ||
                                    item.invitation?.nomCompletCollaborateur?.lowercase()
                                        ?.contains(query) == true ||
                                    item.invitation?.themeNom?.lowercase()
                                        ?.contains(query) == true ||
                                    allThemes.value?.find { it.id == f.themeId }
                                        ?.nom?.lowercase()?.contains(query) == true

                            // ── Service ────────────────────────────────────────
                            // Works for NON_EXPEDIEE (no invitation) via formation.division
                            val matchesService = service == null || run {
                                val invService      = item.invitation?.service
                                val formationService = f.division
                                invService?.equals(service, ignoreCase = true) == true ||
                                        formationService.equals(service, ignoreCase = true)
                            }

                            // ── Statut ─────────────────────────────────────────
                            val matchesStatut = statut == null || item.status == statut

                            // ── Theme ──────────────────────────────────────────
                            // Works for NON_EXPEDIEE (no invitation) via allThemes lookup
                            val matchesTheme = theme == null || run {
                                val invTheme = item.invitation?.themeNom
                                val formationTheme = allThemes.value
                                    ?.find { it.id == f.themeId }
                                    ?.nom
                                invTheme?.equals(theme, ignoreCase = true) == true ||
                                        formationTheme?.equals(theme, ignoreCase = true) == true
                            }

                            Log.d("InvFilter",
                                "mat=${f.collaborateurMatricule}" +
                                        " | theme=${item.invitation?.themeNom ?: allThemes.value?.find { it.id == f.themeId }?.nom}" +
                                        " | status=${item.status}" +
                                        " | svc=${item.invitation?.service ?: f.division}" +
                                        " | Q=$matchesQuery S=$matchesService" +
                                        " | St=$matchesStatut T=$matchesTheme"
                            )

                            matchesQuery && matchesService && matchesStatut && matchesTheme
                        }
                        Log.d("InvFilter", "FILTERED → ${result.size}")
                        result
                    }
                }
                Log.d("InvFilter", "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
            }

            mediator.addSource(formationsWithStatus) { refresh() }
            mediator.addSource(_searchQuery)         { refresh() }
            mediator.addSource(_filterService)       { refresh() }
            mediator.addSource(_filterStatut)        { refresh() }
            mediator.addSource(_filterTheme)         { refresh() }
            mediator.addSource(allThemes)            { refresh() } // ← needed for NON_EXPEDIEE theme lookup
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

            Log.d("SendForm", "━━━━━━━━━━ START sendFormToFlm ━━━━━━━━━━")

            try {
                val formation = item.formation
                Log.d("SendForm", "Formation ID: ${formation.id}")
                Log.d("SendForm", "Collaborateur Matricule: ${formation.collaborateurMatricule}")

                val collab = repo.collaborateurDao.getByMatricule(
                    formation.collaborateurMatricule
                )

                if (collab == null) {
                    Log.e("SendForm", "❌ Collaborateur NOT FOUND")
                    _invitationState.value = InvitationState.Error(
                        "Collaborateur introuvable : ${formation.collaborateurMatricule}"
                    )
                    return@launch
                }

                Log.d("SendForm", "✅ Collaborateur found: ${collab.prenom} ${collab.nom}")
                Log.d("SendForm", "FLM Matricule: ${collab.flmMatricule}")

                val flm = collab.flmMatricule?.let {
                    repo.flmDao.getByMatricule(it)
                }

                if (flm == null) {
                    Log.e("SendForm", "❌ FLM NOT FOUND")
                    _invitationState.value = InvitationState.Error(
                        "FLM introuvable pour : ${collab.matricule}"
                    )
                    return@launch
                }

                Log.d("SendForm", "✅ FLM found: ${flm.prenom} ${flm.nom}")
                Log.d("SendForm", "FLM Email: ${flm.email}")

                val theme = repo.themeDao.getById(formation.themeId)
                Log.d("SendForm", "Theme ID: ${formation.themeId}")
                Log.d("SendForm", "Theme Name: ${theme?.nom}")

                Log.d("SendForm", "➡️ Sending form to FLM...")

                val result = repo.sendEvaluationFormToFlm(
                    collaborateur = collab,
                    formation     = formation,
                    theme         = theme,
                    flmEmail      = flm.email,
                    flmNom        = "${flm.prenom} ${flm.nom}"
                )

                if (result.isSuccess) {
                    Log.d("SendForm", "✅ SUCCESS: ${result.getOrNull()}")
                    _invitationState.value = InvitationState.Sent(result.getOrNull()!!)
                } else {
                    Log.e("SendForm", "❌ ERROR: ${result.exceptionOrNull()?.message}")
                    _invitationState.value = InvitationState.Error(
                        result.exceptionOrNull()?.message ?: "Erreur d'envoi"
                    )
                }

            } catch (e: Exception) {
                Log.e("SendForm", "🔥 EXCEPTION: ${e.message}", e)
                _invitationState.value = InvitationState.Error(e.message ?: "Erreur")
            }

            Log.d("SendForm", "━━━━━━━━━━ END sendFormToFlm ━━━━━━━━━━")
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
                InvitationState.Error(
                    result.exceptionOrNull()?.message ?: "Erreur envoi global"
                )
        }
    }

    // ── Helpers ────────────────────────────────────────────────────────────────
    fun verifierMatricule(matricule: String, onResult: (CollaborateurEntity?) -> Unit) {
        viewModelScope.launch { onResult(repo.collaborateurDao.getByMatricule(matricule)) }
    }

    fun getFormationByMatricule(matricule: String, onResult: (FormationEntity?) -> Unit) {
        viewModelScope.launch {
            onResult(repo.formationDao.getByCollaborateur(matricule).firstOrNull())
        }
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