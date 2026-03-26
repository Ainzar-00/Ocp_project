package com.ocp.evalformation.ui.rh.evaluations

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ListenerRegistration
import com.ocp.evalformation.data.local.entity.CriteriaAverages
import com.ocp.evalformation.data.local.entity.EvaluationEntity
import com.ocp.evalformation.data.local.entity.EvaluationWithContext
import com.ocp.evalformation.data.local.entity.FlmEntity
import com.ocp.evalformation.data.local.entity.FormationEntity
import com.ocp.evalformation.data.local.entity.InvitationFlmEntity
import com.ocp.evalformation.data.local.entity.SatisfactionRate
import com.ocp.evalformation.data.repository.EvaluationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EvaluationViewModel @Inject constructor(
    private val repo: EvaluationRepository
) : ViewModel() {

    // ── List screen ───────────────────────────────────────────────
    private val _evaluations = MutableStateFlow<List<EvaluationWithContext>>(emptyList())
    val evaluations: StateFlow<List<EvaluationWithContext>> = _evaluations.asStateFlow()
    //All Evaluations

    private val _filtered = MutableStateFlow<List<EvaluationWithContext>>(emptyList())
    val filtered: StateFlow<List<EvaluationWithContext>> = _filtered.asStateFlow()

    // ── Detail screen ─────────────────────────────────────────────
    private val _selected   = MutableStateFlow<EvaluationEntity?>(null)
    private val _formation  = MutableStateFlow<FormationEntity?>(null)
    private val _invitation = MutableStateFlow<InvitationFlmEntity?>(null)
    private val _flm        = MutableStateFlow<FlmEntity?>(null)

    // ── Add these to EvaluationViewModel ─────────────────────────────

    // ── Chart data ────────────────────────────────────────────────────
    private val _criteriaAverages = MutableStateFlow<CriteriaAverages?>(null)
    val criteriaAverages: StateFlow<CriteriaAverages?> = _criteriaAverages.asStateFlow()

    private val _satisfactionRate = MutableStateFlow<SatisfactionRate?>(null)
    val satisfactionRate: StateFlow<SatisfactionRate?> = _satisfactionRate.asStateFlow()

    // Call this to compute chart data for a given year (null = all years)
    fun computeChartData(year: Int? = null) {
        viewModelScope.launch {
            val evals = if (year != null) {
                _evaluations.value.filter { item ->
                    extractYear(item.evaluation.dateEvaluation) == year
                }
            } else {
                _evaluations.value
            }

            if (evals.isEmpty()) {
                _criteriaAverages.value = null
                _satisfactionRate.value = null
                return@launch
            }

            val total = evals.size

            // ── Task 1: Average score per criterion ───────────────────
            val avgBesoin = evals.map { it.evaluation.critieres.satisfactionBesoin }.average().toFloat()
            val avgImpact = evals.map { it.evaluation.critieres.impactPerformance }.average().toFloat()
            val avgAppli  = evals.map { it.evaluation.critieres.applicationConnaissances }.average().toFloat()
            val avgGlobal = evals.map { it.evaluation.critieres.satisfactionGlobale }.average().toFloat()

            _criteriaAverages.value = CriteriaAverages(
                satisfactionBesoin       = avgBesoin,
                impactPerformance        = avgImpact,
                applicationConnaissances = avgAppli,
                satisfactionGlobale      = avgGlobal
            )

            // ── Task 2: Satisfaction vs Insatisfaction ────────────────
            // Based on satisfactionGlobale: positive = 3 or 4, negative = 1 or 2
            val positive = evals.count { it.evaluation.critieres.satisfactionGlobale >= 3 }
            val negative = evals.count { it.evaluation.critieres.satisfactionGlobale in 1..2 }

            _satisfactionRate.value = SatisfactionRate(
                positiveCount   = positive,
                negativeCount   = negative,
                total           = total,
                positivePercent = if (total > 0) positive * 100f / total else 0f,
                negativePercent = if (total > 0) negative * 100f / total else 0f
            )
        }
    }



    val selected  : StateFlow<EvaluationEntity?>    = _selected.asStateFlow()
    val formation : StateFlow<FormationEntity?>     = _formation.asStateFlow()
    val invitation: StateFlow<InvitationFlmEntity?> = _invitation.asStateFlow()
    val flm       : StateFlow<FlmEntity?>           = _flm.asStateFlow()

    private var firestoreListener: ListenerRegistration? = null

    init {
        loadAll()
//        startFirestoreSync()
    }

    private fun loadAll() {
        viewModelScope.launch {
            repo.getAllEvaluations().collect { list ->
                // For each evaluation, load formation and flm in parallel
                val withContext = list.map { eval ->
                    async {
                        val formation = repo.getFormationById(eval.formationId)
                        val flm = null // evaluator removed
                        EvaluationWithContext(eval, formation, flm)
                    }
                }.map { it.await() }

                _evaluations.value = withContext
                _filtered.value    = withContext
            }
        }
    }

    private fun startFirestoreSync() {
        firestoreListener = repo.listenToEvaluations(
            onAdded = { eval ->
                viewModelScope.launch { repo.syncToRoom(eval) }
            },
            onModified = { eval ->
                viewModelScope.launch { repo.syncToRoom(eval) }
            },
            onError = { e ->
                Log.e("EvaluationViewModel", "Firestore error: ${e.message}")
            }
        )
    }

    // Called when user taps an item
    fun select(item: EvaluationWithContext) {
        _selected.value   = item.evaluation
        _formation.value  = item.formation
        _flm.value        = item.flm
        // Also load invitation for detail screen
        viewModelScope.launch {
            _invitation.value = repo.getInvitationByFormationId(item.evaluation.formationId)
        }
    }

    fun search(
        matricule: String = "",
        entite: String = "",
        theme: String = "",
        mois: Int? = null,
        annee: Int? = null
    ) {
        _filtered.value = _evaluations.value.filter { item ->
            val eval = item.evaluation

            val matchMatricule =
                matricule.isBlank() ||
                        eval.maticuleCollaborateur.contains(matricule, ignoreCase = true)

            val matchEntite =
                entite.isBlank() ||
                        item.entite.contains(entite, ignoreCase = true)

            val matchTheme =
                theme.isBlank() ||
                        item.themeNom.contains(theme, ignoreCase = true)

            val itemMonth = extractMonth(eval.dateEvaluation)
            val itemYear = extractYear(eval.dateEvaluation)

            val matchMois = mois == null || itemMonth == mois
            val matchAnnee = annee == null || itemYear == annee

            matchMatricule && matchEntite && matchTheme && matchMois && matchAnnee
        }
    }


    // Parses "21/03/2026" → "21 mars 2026"
    fun formatDate(date: String): String {
        return try {
            val sdf  = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.FRENCH)
            val parsed = sdf.parse(date) ?: return date
            java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale.FRENCH).format(parsed)
        } catch (e: Exception) {
            date
        }
    }

    private fun extractMonth(date: String?): Int? {
        if (date.isNullOrBlank()) return null

        return try {
            when {
                date.contains("/") -> date.split("/").getOrNull(1)?.toIntOrNull()
                date.contains("-") -> date.take(7).split("-").getOrNull(1)?.toIntOrNull()
                else -> null
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun extractYear(date: String?): Int? {
        if (date.isNullOrBlank()) return null

        return try {
            when {
                date.contains("/") -> date.split("/").getOrNull(2)?.toIntOrNull()
                date.contains("-") -> date.take(4).toIntOrNull()
                else -> null
            }
        } catch (e: Exception) {
            null
        }
    }


    override fun onCleared() {
        super.onCleared()
        firestoreListener?.remove()
    }
}