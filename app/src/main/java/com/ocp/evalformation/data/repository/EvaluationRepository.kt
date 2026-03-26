package com.ocp.evalformation.data.repository

import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.ocp.evalformation.data.local.dao.CollaborateurDao
import com.ocp.evalformation.data.local.dao.EvaluationDao
import com.ocp.evalformation.data.local.dao.FlmDao
import com.ocp.evalformation.data.local.dao.FormationDao
import com.ocp.evalformation.data.local.dao.InvitationFlmDao
import com.ocp.evalformation.data.local.entity.CritieresEvaluation
import com.ocp.evalformation.data.local.entity.EvaluationEntity
import com.ocp.evalformation.data.local.entity.FlmEntity
import com.ocp.evalformation.data.local.entity.FormationEntity
import com.ocp.evalformation.data.local.entity.InvitationFlmEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EvaluationRepository @Inject constructor(
    private val firestore      : FirebaseFirestore,
    private val evaluationDao  : EvaluationDao,
    private val invitationDao  : InvitationFlmDao,
    private val formationDao   : FormationDao,
    private val collaborateurDao: CollaborateurDao,
    private val flmDao         : FlmDao
) {

    // ── Room queries ──────────────────────────────────────────────
    fun getAllEvaluations(): Flow<List<EvaluationEntity>> =
        evaluationDao.getAll()

    suspend fun getByFormationId(formationId: Long): EvaluationEntity? =
        evaluationDao.getByFormationId(formationId)

    suspend fun getInvitationByFormationId(formationId: Long): InvitationFlmEntity? =
        invitationDao.getByFormationId(formationId)

    suspend fun getFormationById(formationId: Long): FormationEntity? =
        formationDao.getById(formationId)

    // Chain: collaborateur matricule → flm matricule → flm entity
    suspend fun getFlmByCollaborateurMatricule(matricule: String): FlmEntity? {
        val collaborateur = collaborateurDao.getByMatricule(matricule) ?: return null
        val flmMatricule  = collaborateur.flmMatricule ?: return null
        return flmDao.getByMatricule(flmMatricule)
    }

    suspend fun insertEvaluation(evaluation: EvaluationEntity): Long =
        evaluationDao.insert(evaluation)

    suspend fun syncToRoom(evaluation: EvaluationEntity) {
        val existing = evaluationDao.getByFormationId(evaluation.formationId)
        if (existing != null) {
            evaluationDao.update(evaluation.copy(id = existing.id))
        } else {
            evaluationDao.insert(evaluation)
        }
        invitationDao.markAsReplied(evaluation.formationId)
    }

    // ── Firestore listener ────────────────────────────────────────
    fun listenToEvaluations(
        onAdded   : (EvaluationEntity) -> Unit,
        onModified: (EvaluationEntity) -> Unit = {},
        onError   : (Exception) -> Unit = {}
    ): ListenerRegistration {
        return firestore.collection("evaluations")
            .addSnapshotListener { snapshot, error ->
                if (error != null) { onError(error); return@addSnapshotListener }
                snapshot?.documentChanges?.forEach { change ->
                    val doc  = change.document
                    val eval = mapDocumentToEntity(doc.data ?: return@forEach)
                    when (change.type) {
                        DocumentChange.Type.ADDED    -> onAdded(eval)
                        DocumentChange.Type.MODIFIED -> onModified(eval)
                        DocumentChange.Type.REMOVED  -> {}
                    }
                }
            }
    }

    // ── Firestore → EvaluationEntity mapper ──────────────────────
    private fun mapDocumentToEntity(data: Map<String, Any>): EvaluationEntity {
        val moyens = (data["moyenAppreciation"] as? List<*>)
            ?.filterIsInstance<String>() ?: emptyList()

        // If autresMoyen is not blank, add it to the list
        val autresMoyen = (data["autresMoyen"] as? String)?.trim() ?: ""
        val moyensComplets = if (autresMoyen.isNotBlank()) {
            moyens + autresMoyen
        } else {
            moyens
        }



        return EvaluationEntity(
            formationId           = (data["formationId"] as? String)?.toLongOrNull() ?: 0L,
            intituleAction        = data["intituleAction"] as? String ?: "",
            maticuleCollaborateur = data["matricule"] as? String ?: "",
            datesFormation        = data["dates"] as? String ?: "",
            dateEvaluation        = data["dateEvaluation"] as? String ?: "",
            moyensAppreciation    = moyensComplets,  // ← merged list
            critieres             = CritieresEvaluation(
                satisfactionBesoin       = scoreToInt(data["q1SatisfactionBesoin"] as? String),
                impactPerformance        = scoreToInt(data["q2ImpactPerformance"] as? String),
                applicationConnaissances = scoreToInt(data["q3ApplicationPratique"] as? String),
                satisfactionGlobale      = scoreToInt(data["satisfactionGlobale"] as? String)
            ),
            raisonsInsatisfaction = toStringList(data,"siNonPourquoi"),
            competencesAcquises   = toStringList(data,"competencesAcquises"),
            Suggestions = data["suggestions"] as? String ?: ""
        )
    }

    private fun scoreToInt(value: String?): Int = when (value) {
        "Très satisfaisant" -> 4
        "Satisfaisant"      -> 3
        "Peu satisfaisant"  -> 2
        "Insatisfaisant"    -> 1
        else                -> 0
    }

    private fun toStringList(data: Map<String, Any>, key: String): List<String> {
        return when (val raw = data[key]) {
            is List<*> -> raw
                .mapNotNull { it?.toString() }
                .flatMap { it.split(", ") }
                .map { it.trim() }
                .filter { it.isNotBlank() }
            is String  -> raw.split(", ").map { it.trim() }.filter { it.isNotBlank() }
            else       -> emptyList()
        }
    }
}