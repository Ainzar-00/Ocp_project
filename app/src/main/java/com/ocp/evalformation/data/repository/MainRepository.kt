package com.ocp.evalformation.data.repository

import android.util.Log
import com.ocp.evalformation.com.ocp.evalformation.data.GoogleScriptApiService.RetrofitInstance
import com.ocp.evalformation.data.local.OcpDatabase
import com.ocp.evalformation.data.local.entity.*
import com.ocp.evalformation.data.remote.FirebaseRepository
import com.ocp.evalformation.data.local.dao.FormDao
import com.ocp.evalformation.utils.EmailHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    val formDao          get() = db.formDao()

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
            // Auto-create form for theme (silently ignore failure)
            CoroutineScope(Dispatchers.IO).launch {
                try { createFormForTheme(saved) }
                catch (e: Exception) { Log.w("Repo", "form creation skipped: ${e.message}") }
            }
            Result.success(saved)
        } catch (e: Exception) { Result.failure(e) }
    }

    // ── Form Creation ──────────────────────────────────────────────────────────
    suspend fun createFormForTheme(theme: ThemeEntity) {
        val googleScriptApi = RetrofitInstance.api
        try {
            val existing = formDao.getByThemeId(theme.id)
            if (existing != null) {
                Log.i("FormCreate", "Form already exists for theme ${theme.id}")
                return
            }

            val competences = theme.objectifPedagogique
                .split(",")
                .map { it.trim() }
                .filter { it.isNotBlank() }

            val request = FormCreationRequest(
                themeNom    = theme.nom,
                competences = competences
            )

            Log.i("FormCreate", "Calling Apps Script for theme: ${theme.nom}")

            val response = googleScriptApi.createForm(request)

            Log.i("FormCreate", "Response code: ${response.code()}")
            Log.i("FormCreate", "Response body: ${response.body()}")
            Log.i("FormCreate", "Error body: ${response.errorBody()?.string()}")

            if (!response.isSuccessful) {
                Log.e("FormCreate", "Apps Script call failed: ${response.code()}")
                return
            }

            val body = response.body()
            if (body == null) {
                Log.e("FormCreate", "Response body is null — likely Gson parsing failed")
                return
            }

            if (body.status != "success") {
                Log.e("FormCreate", "Apps Script returned error: ${body.status}")
                return
            }

            Log.i("FormCreate", "Form created: ${body.formUrl}")
            Log.i("FormCreate", "EntryIds: ${body.entryIds}")

            val form = Forms(
                themeId  = theme.id,
                formUrl  = body.formUrl,
                entryIds = body.entryIds
            )

            val roomId = formDao.insert(form)
            Log.i("FormCreate", "Form saved to Room with id: $roomId")

            firebase.UploadForm(roomId, form)
            Log.i("FormCreate", "Form uploaded to Firebase")

        } catch (e: Exception) {
            Log.e("FormCreate", "createFormForTheme failed: ${e.message}", e)
        }
    }

    // ── Send Invitation ────────────────────────────────────────────────────────
    suspend fun sendEvaluationFormToFlm(
        collaborateur: CollaborateurEntity,
        formation: FormationEntity,
        theme: ThemeEntity?,
        flmEmail: String,
        flmNom: String
    ): Result<InvitationFlmEntity> {
        return try {

            // Get the form for this theme
            val form = theme?.let { formDao.getByThemeId(it.id) }

            // Build pre-filled URL if form exists, otherwise use base URL
            val formUrl = if (form != null) {
                buildPreFilledUrl(form, formation, collaborateur, theme)
            } else {
                "https://forms.google.com/" // fallback
            }

            val invitation = InvitationFlmEntity(
                formationId             = formation.id,
                datesFormation          = "${formation.debut} - ${formation.fin}",
                formateur               = formation.Formateur,
                matriculeCollaborateur  = collaborateur.matricule,
                nomCompletCollaborateur = "${collaborateur.prenom} ${collaborateur.nom}",
                service                 = collaborateur.service,
                themeNom                = theme?.nom ?: "—",
                themeObjectives         = theme?.objectifPedagogique?.split(",")?.map { it.trim() }?.filter { it.isNotBlank() } ?: emptyList(),
                emailFlm                = flmEmail,
                nomFlm                  = flmNom,
                statut                  = InvitationStatus.EN_ATTENTE,
                dateEnvoi               = System.currentTimeMillis()
            )

            val id = invitationDao.insert(invitation)
            val saved = invitation.copy(id = id)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    firebase.saveInvitation(saved)

                    val subject = "Évaluation Formation — ${theme?.nom ?: ""}"
                    val body    = EmailHelper.buildInvitationBody(
                        flmNom        = flmNom,
                        themeNom      = theme?.nom ?: "—",
                        collaborateur = "${collaborateur.prenom} ${collaborateur.nom}",
                        formUrl       = formUrl
                    )
                    EmailHelper.sendEmail(to = flmEmail, subject = subject, body = body)

                } catch (e: Exception) {
                    Log.e("Repo", "invitation background error", e)
                }
            }

            Result.success(saved)
        } catch (e: Exception) { Result.failure(e) }
    }

    // ── Build Pre-filled URL ───────────────────────────────────────────────────
    fun buildPreFilledUrl(
        form: Forms,
        formation: FormationEntity,
        collaborateur: CollaborateurEntity,
        theme: ThemeEntity?
    ): String {
        fun encode(v: String) = java.net.URLEncoder.encode(v, "UTF-8")
        return "${form.formUrl}?usp=pp_url" +
            "&entry.${form.entryIds.formationId}=${formation.id}" +
            "&entry.${form.entryIds.intituleAction}=${encode(theme?.nom ?: "")}" +
            "&entry.${form.entryIds.nomPrenom}=${encode("${collaborateur.prenom} ${collaborateur.nom}")}" +
            "&entry.${form.entryIds.service}=${encode(collaborateur.service)}" +
            "&entry.${form.entryIds.dates}=${encode("${formation.debut} - ${formation.fin}")}" +
            "&entry.${form.entryIds.formateur}=${encode(formation.Formateur)}" +
            "&entry.${form.entryIds.matricule}=${encode(collaborateur.matricule)}"
    }

    // ── Send All Invitations ───────────────────────────────────────────────────
    suspend fun sendAllPendingInvitations(
        formations: List<FormationEntity>
    ): Result<Int> {
        return try {
            var count = 0
            formations.forEach { formation ->
                // Skip if already has an active invitation
                val existing = invitationDao.getByFormationId(formation.id)
                if (existing != null && existing.statut == InvitationStatus.EN_ATTENTE) return@forEach
                if (existing != null && existing.statut == InvitationStatus.REPONDUE) return@forEach

                val collab = collaborateurDao.getByMatricule(formation.collaborateurMatricule) ?: return@forEach
                val flm    = collab.flmMatricule?.let { flmDao.getByMatricule(it) } ?: return@forEach
                val theme  = themeDao.getById(formation.themeId)

                val result = sendEvaluationFormToFlm(
                    collaborateur = collab,
                    formation     = formation,
                    theme         = theme,
                    flmEmail      = flm.email,
                    flmNom        = "${flm.prenom} ${flm.nom}"
                )
                if (result.isSuccess) count++
            }
            Result.success(count)
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
        } catch (e: Exception) { Log.e("Repo", "syncPending error", e) }
    }

    // ── Delete all ─────────────────────────────────────────────────────────────
    suspend fun deleteAllData() {
        formationDao.deleteAll()
        collaborateurDao.deleteAll()
        invitationDao.deleteAll()
    }
}
