package com.ocp.evalformation.data.repository

import android.util.Log
import com.ocp.evalformation.data.local.OcpDatabase
import com.ocp.evalformation.data.local.entity.*
import com.ocp.evalformation.data.remote.FirebaseRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    private val db: OcpDatabase,
    val firebase: FirebaseRepository
) {
    val themeDao         get() = db.themeDao()
    val flmDao           get() = db.flmDao()
    val collaborateurDao get() = db.collaborateurDao()

    val formationDao     get() = db.formationDao()
    val evaluationDao    get() = db.evaluationDao()
    val invitationDao    get() = db.invitationFlmDao()

    // ── Theme ──────────────────────────────────────────────────────────────────
    suspend fun addTheme(nom: String, objectifPedagogique: String): Result<ThemeEntity> {
        return try {
            val theme = ThemeEntity(nom = nom, objectifPedagogique = objectifPedagogique)
            val id = themeDao.insert(theme)
            val saved = theme.copy(id = id)
            CoroutineScope(Dispatchers.IO).launch {
                try { firebase.addTheme(saved); themeDao.markSynced(listOf(id)) }
                catch (e: Exception) { Log.e("Repo", "theme sync error", e) }
            }
            Result.success(saved)
        } catch (e: Exception) { Result.failure(e) }
    }

    // ── FLM ────────────────────────────────────────────────────────────────────
    suspend fun addFlm(flm: FlmEntity): Result<Unit> {
        return try {
            flmDao.insert(flm)
            CoroutineScope(Dispatchers.IO).launch {
                try { firebase.uploadFlms(listOf(flm)); flmDao.markSynced(listOf(flm.matricule)) }
                catch (e: Exception) { Log.e("Repo", "flm sync error", e) }
            }
            Result.success(Unit)
        } catch (e: Exception) { Result.failure(e) }
    }

    // ── Collaborateur ──────────────────────────────────────────────────────────
    suspend fun addCollaborateur(collab: CollaborateurEntity): Result<Unit> {
        return try {
            collaborateurDao.insert(collab)
            CoroutineScope(Dispatchers.IO).launch {
                try { firebase.uploadCollaborateurs(listOf(collab)); collaborateurDao.markSynced(listOf(collab.matricule)) }
                catch (e: Exception) { Log.e("Repo", "collab sync error", e) }
            }
            Result.success(Unit)
        } catch (e: Exception) { Result.failure(e) }
    }

    // ── Formation ──────────────────────────────────────────────────────────────
    suspend fun addFormation(formation: FormationEntity): Result<Long> {
        return try {
            val id = formationDao.insert(formation)
            val saved = formation.copy(id = id)
            CoroutineScope(Dispatchers.IO).launch {
                try { firebase.uploadFormations(listOf(saved)); formationDao.markSynced(listOf(id)) }
                catch (e: Exception) { Log.e("Repo", "formation sync error", e) }
            }
            Result.success(id)
        } catch (e: Exception) { Result.failure(e) }
    }

    // ── Invitation FLM ─────────────────────────────────────────────────────────
    suspend fun sendEvaluationFormToFlm(
        collaborateur: CollaborateurEntity,
        formation: FormationEntity,
        theme: ThemeEntity?,
        flmEmail: String,
        flmNom: String
    ): Result<InvitationFlmEntity> {
        return try {
            val formUrl = "https://forms.google.com/"
            val invitation = InvitationFlmEntity(
                formationId             = formation.id,
                matriculeCollaborateur  = collaborateur.matricule,
                nomCompletCollaborateur = "${collaborateur.prenom} ${collaborateur.nom}",
                themeNom                = theme?.nom ?: "—",
                emailFlm                = flmEmail,
                nomFlm                  = flmNom,
                googleFormUrl           = formUrl,
                statut                  = "ENVOYE"
            )
            val id = invitationDao.insert(invitation)
            CoroutineScope(Dispatchers.IO).launch {
                try { firebase.saveInvitation(invitation) }
                catch (e: Exception) { Log.e("Repo", "invitation sync error", e) }
            }
            Result.success(invitation.copy(id = id))
        } catch (e: Exception) { Result.failure(e) }
    }

    // ── Évaluation ─────────────────────────────────────────────────────────────
    suspend fun saveEvaluation(eval: EvaluationEntity): Result<Long> {
        return try {
            val id = evaluationDao.insert(eval)
            val saved = eval.copy(id = id)
            CoroutineScope(Dispatchers.IO).launch {
                try { firebase.uploadEvaluation(saved); evaluationDao.markSynced(listOf(id)) }
                catch (e: Exception) { Log.e("Repo", "eval sync error", e) }
            }
            Result.success(id)
        } catch (e: Exception) { Result.failure(e) }
    }

    // ── Sync ───────────────────────────────────────────────────────────────────
    suspend fun syncPendingToFirebase() {
        try {
            themeDao.getUnsynced().takeIf { it.isNotEmpty() }?.let {
                if (firebase.uploadThemes(it).isSuccess) themeDao.markSynced(it.map { t -> t.id })
            }
            flmDao.getUnsynced().takeIf { it.isNotEmpty() }?.let {
                if (firebase.uploadFlms(it).isSuccess) flmDao.markSynced(it.map { f -> f.matricule })
            }
            collaborateurDao.getUnsynced().takeIf { it.isNotEmpty() }?.let {
                if (firebase.uploadCollaborateurs(it).isSuccess) collaborateurDao.markSynced(it.map { c -> c.matricule })
            }
            formationDao.getUnsynced().takeIf { it.isNotEmpty() }?.let {
                if (firebase.uploadFormations(it).isSuccess) formationDao.markSynced(it.map { f -> f.id })
            }
            evaluationDao.getUnsynced().takeIf { it.isNotEmpty() }?.let {
                if (firebase.uploadEvaluationsBatch(it).isSuccess) evaluationDao.markSynced(it.map { e -> e.id })
            }
        } catch (e: Exception) { Log.e("Repo", "syncPending error", e) }
    }

    // ── Delete all ─────────────────────────────────────────────────────────────
    suspend fun deleteAllData() {
        evaluationDao.deleteAll()
        formationDao.deleteAll()
        collaborateurDao.deleteAll()
    }
}