package com.ocp.evalformation.ui.rh

import androidx.lifecycle.*
import com.ocp.evalformation.data.local.entity.*
import com.ocp.evalformation.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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
    val allEvaluations    = repo.evaluationDao.getAllLive()
    val pendingInvitations = repo.invitationDao.getPendingLive()
    val pendingCount       = repo.invitationDao.countPendingLive()

    // ── EvaluationAvecFormation join ───────────────────────────────────────────
    val evaluationsAvecFormation: LiveData<List<EvaluationAvecFormation>> =
        MediatorLiveData<List<EvaluationAvecFormation>>().also { mediator ->
            fun refresh() {
                val evals      = allEvaluations.value  ?: return
                val formations = allFormations.value   ?: return
                val map        = formations.associateBy { it.id }
                mediator.value = evals.map { EvaluationAvecFormation(it, map[it.formationId]) }
            }
            mediator.addSource(allEvaluations) { refresh() }
            mediator.addSource(allFormations)  { refresh() }
        }

    // ── Filter state ───────────────────────────────────────────────────────────
    private val _filterTheme = MutableLiveData<String?>(null)
    private val _filterAnnee = MutableLiveData<String?>(null)
    private val _filterMois  = MutableLiveData<String?>(null)

    val filteredEvaluations: LiveData<List<EvaluationAvecFormation>> =
        MediatorLiveData<List<EvaluationAvecFormation>>().also { mediator ->
            fun refresh() {
                val theme = _filterTheme.value
                val annee = _filterAnnee.value
                val mois  = _filterMois.value
                mediator.value = evaluationsAvecFormation.value?.filter { item ->
                    val eval = item.evaluation
                    (theme == null || item.themeNom == theme) &&
                    (annee == null || eval.createdAt.toYear() == annee) &&
                    (mois  == null || eval.createdAt.toMonth() == mois)
                } ?: emptyList()
            }
            mediator.addSource(evaluationsAvecFormation) { refresh() }
            mediator.addSource(_filterTheme)             { refresh() }
            mediator.addSource(_filterAnnee)             { refresh() }
            mediator.addSource(_filterMois)              { refresh() }
        }

    fun setFilter(theme: String?, annee: String?, mois: String?) {
        _filterTheme.value = theme
        _filterAnnee.value = annee
        _filterMois.value  = mois
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
        data class Error(val message: String)                : InvitationState()
    }

    private val _invitationState = MutableStateFlow<InvitationState>(InvitationState.Idle)
    val invitationState: StateFlow<InvitationState> = _invitationState

    fun sendFormToFlm(
        collaborateur: CollaborateurEntity,
        formation: FormationEntity,
        flmEmail: String,
        flmNom: String
    ) {
        viewModelScope.launch {
            _invitationState.value = InvitationState.Sending
            val theme = repo.themeDao.getById(formation.themeId)
            val result = repo.sendEvaluationFormToFlm(collaborateur, formation, theme, flmEmail, flmNom)
            _invitationState.value = if (result.isSuccess)
                InvitationState.Sent(result.getOrNull()!!)
            else
                InvitationState.Error(result.exceptionOrNull()?.message ?: "Erreur d'envoi")
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
data class EvaluationAvecFormation(
    val evaluation: EvaluationEntity,
    val formation: FormationEntity?
) {
    val themeNom: String       get() = formation?.themeId?.toString() ?: "—"
    val dateEvaluation: String get() = evaluation.dateEvaluation
    val flmNom: String         get() = evaluation.flmNom
}

// ── Extensions ─────────────────────────────────────────────────────────────────
private fun Long.toYear(): String {
    val cal = java.util.Calendar.getInstance()
    cal.timeInMillis = this
    return cal.get(java.util.Calendar.YEAR).toString()
}

private fun Long.toMonth(): String {
    val cal = java.util.Calendar.getInstance()
    cal.timeInMillis = this
    return String.format("%02d", cal.get(java.util.Calendar.MONTH) + 1)
}
