package com.ocp.evalformation.data.repository

import android.content.Context
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.edit
import com.ocp.evalformation.com.ocp.evalformation.data.GoogleScriptApiService.RetrofitInstance
import com.ocp.evalformation.data.local.OcpDatabase
import com.ocp.evalformation.data.local.entity.*
import com.ocp.evalformation.data.remote.FirebaseRepository
import com.ocp.evalformation.data.local.dao.FormDao
import com.ocp.evalformation.utils.EmailHelper
import com.ocp.evalformation.utils.dateHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    @ApplicationContext private val context: Context,
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
        val tag = "FormCreate"
        val googleScriptApi = RetrofitInstance.api
        try {
            Log.d(tag, "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
            Log.d(tag, "START createFormForTheme: '${theme.nom}' (id=${theme.id})")

            // ── Step 1: Check if form already exists ──────────────
            val existing = formDao.getByThemeId(theme.id)
            if (existing != null) {
                Log.i(tag, "SKIP — form already exists for themeId=${theme.id}, formUrl=${existing.formUrl}")
                return
            }
            Log.d(tag, "STEP 1 ✅ — no existing form, proceeding")

            // ── Step 2: Build request ──────────────────────────────
            val competences = theme.objectifPedagogique
                .split("•")
                .map { it.trim() }
                .filter { it.isNotBlank() }

            val request = FormCreationRequest(
                themeNom    = theme.nom,
                competences = competences
            )
            Log.d(tag, "STEP 2 ✅ — themeNom='${theme.nom}', competences(${competences.size})=$competences")

            // ── Step 3: Call Apps Script ───────────────────────────
            Log.d(tag, "STEP 3 — calling Apps Script...")
            val response = googleScriptApi.createForm(request)
            Log.d(tag, "STEP 3 — response code: ${response.code()}, isSuccessful: ${response.isSuccessful}")

            if (!response.isSuccessful) {
                Log.e(tag, "STEP 3 ❌ — failed: code=${response.code()}, error=${response.errorBody()?.string()}")
                return
            }

            // ── Step 4: Parse body ─────────────────────────────────
            val body = response.body()
            Log.d(tag, "STEP 4 — raw body: $body")

            if (body == null) {
                Log.e(tag, "STEP 4 ❌ — body is null (Gson parsing failed)")
                return
            }

            if (body.status != "success") {
                Log.e(tag, "STEP 4 ❌ — Apps Script error: ${body.status} | message: ${body.message} | stack: ${body.stack}")
                return
            }

            Log.d(tag, "STEP 4 ✅ — status: ${body.status}")
            Log.d(tag, "STEP 4 ✅ — formUrl: ${body.formUrl}")
            Log.d(tag, "STEP 4 ✅ — formId: ${body.formId}")
            Log.d(tag, "STEP 4 ✅ — entryIds.formationId    : ${body.entryIds?.formationId}")
            Log.d(tag, "STEP 4 ✅ — entryIds.intituleAction : ${body.entryIds?.intituleAction}")
            Log.d(tag, "STEP 4 ✅ — entryIds.nomPrenom      : ${body.entryIds?.nomPrenom}")
            Log.d(tag, "STEP 4 ✅ — entryIds.matricule      : ${body.entryIds?.matricule}")
            Log.d(tag, "STEP 4 ✅ — entryIds.service        : ${body.entryIds?.service}")
            Log.d(tag, "STEP 4 ✅ — entryIds.formateur      : ${body.entryIds?.formateur}")
            Log.d(tag, "STEP 4 ✅ — entryIds.dates          : ${body.entryIds?.dates}")

            if (body.entryIds == null) {
                Log.e(tag, "STEP 4 ❌ — entryIds is null (Gson parsing issue)")
                return
            }

            // ── Step 5: Build Forms entity ─────────────────────────
            val form = Forms(
                themeId  = theme.id,
                formUrl  = body.formUrl!!,
                entryIds = body.entryIds
            )
            Log.d(tag, "STEP 5 ✅ — Forms entity built: $form")

            // ── Step 6: Save to Room ───────────────────────────────
            val roomId = formDao.insert(form)
            Log.d(tag, "STEP 6 — Room insert returned id=$roomId")

            if (roomId == -1L) {
                Log.e(tag, "STEP 6 ❌ — Room insert returned -1 (conflict or schema mismatch)")
                return
            }
            Log.d(tag, "STEP 6 ✅ — saved to Room with id=$roomId")

            // ── Step 7: Upload to Firebase ─────────────────────────
            Log.d(tag, "STEP 7 — uploading to Firebase...")
            try {
                firebase.UploadForm(roomId, form.copy(id = roomId))
                Log.d(tag, "STEP 7 ✅ — uploaded to Firebase successfully")
            } catch (e: Exception) {
                Log.e(tag, "STEP 7 ❌ — Firebase upload failed: ${e.message}", e)
            }

            Log.d(tag, "DONE ✅ — form created for '${theme.nom}'")
            Log.d(tag, "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")

        } catch (e: Exception) {
            Log.e(tag, "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
            Log.e(tag, "FATAL ❌ — crashed for '${theme.nom}': ${e.message}", e)
            Log.e(tag, "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
        }
    }

    // ── Send Invitation ────────────────────────────────────────────────────────
    suspend fun sendEvaluationFormToFlm(
        collaborateur: CollaborateurEntity,
        formation    : FormationEntity,
        theme        : ThemeEntity?,
        flmEmail     : String,
        flmNom       : String
    ): Result<InvitationFlmEntity> {

        Log.d("Repo", "━━━━━━━━━━ START sendEvaluationFormToFlm ━━━━━━━━━━")

        return try {
            Log.d("Repo", "📌 Collaborateur: ${collaborateur.matricule} | ${collaborateur.prenom} ${collaborateur.nom}")
            Log.d("Repo", "📌 Formation ID: ${formation.id}")
            Log.d("Repo", "📌 Theme ID: ${theme?.id}")
            Log.d("Repo", "📌 FLM: $flmNom | $flmEmail")

            // ── Get form for this theme ────────────────────────────
            val form = theme?.let { formDao.getByThemeId(it.id) }
            if (form == null) Log.e("Repo", "❌ No form found for themeId=${theme?.id}")
            else Log.d("Repo", "✅ Form found: ${form.formUrl}")

            // ── Build invitation object (no firebaseId yet) ────────
            val invitation = InvitationFlmEntity(
                formationId             = formation.id,
                datesFormation          = "${formation.debut} - ${formation.fin}",
                formateur               = formation.Formateur,
                matriculeCollaborateur  = collaborateur.matricule,
                nomCompletCollaborateur = "${collaborateur.prenom} ${collaborateur.nom}",
                service                 = collaborateur.service,
                themeNom                = theme?.nom ?: "—",
                themeObjectives         = theme?.objectifPedagogique
                    ?.split("•")
                    ?.map { it.trim() }
                    ?.filter { it.isNotBlank() } ?: emptyList(),
                emailFlm                = flmEmail,
                nomFlm                  = flmNom,
                statut                  = InvitationStatus.EN_ATTENTE,
                dateEnvoi               = System.currentTimeMillis()
            )

            // ── Save to Room ───────────────────────────────────────
            val roomId = invitationDao.insert(invitation)
            Log.d("Repo", "💾 Inserted into Room with ID: $roomId")
            val saved = invitation.copy(id = roomId)

            // ── Save to Firebase — get firebaseId back ─────────────
            Log.d("Repo", "☁️ Sending to Firebase...")
            val firebaseId = firebase.saveInvitation(saved).getOrElse {
                Log.e("Repo", "❌ Firebase save failed: ${it.message}")
                ""
            }
            Log.d("Repo", "✅ Firebase ID: $firebaseId")

            // ── Update Room entity with firebaseId ─────────────────
            if (firebaseId.isNotBlank()) {
                invitationDao.updateFirebaseId(roomId, firebaseId)
                Log.d("Repo", "💾 Room updated with firebaseId: $firebaseId")
            } else {
                Log.w("Repo", "⚠️ firebaseId is blank — Room not updated")
            }

            // ── Build prefilled URL using firebaseId ───────────────
            val formUrl = if (form != null && firebaseId.isNotBlank()) {
                val url = buildPreFilledUrl(form, formation, collaborateur, theme, firebaseId)
                Log.d("Repo", "🔗 Generated prefilled URL: $url")
                url
            } else {
                Log.w("Repo", "⚠️ Missing form or firebaseId — using fallback URL")
                "https://forms.google.com/"
            }

            // ── Send email in background ───────────────────────────
            CoroutineScope(Dispatchers.IO).launch {
                Log.d("Repo", "🚀 Background email task START")
                try {
                    val subject = "Évaluation Formation — ${theme?.nom ?: ""}"
                    val body    = EmailHelper.buildInvitationBody(
                        flmNom        = flmNom,
                        themeNom      = theme?.nom ?: "—",
                        collaborateur = "${collaborateur.prenom} ${collaborateur.nom}",
                        formUrl       = formUrl
                    )
                    Log.d("Repo", "📧 Sending email to: $flmEmail")
                    Log.d("Repo", "📧 URL inside email: $formUrl")
                    EmailHelper.sendEmail(to = flmEmail, subject = subject, body = body)
                    Log.d("Repo", "✅ Email sent SUCCESS")
                } catch (e: Exception) {
                    Log.e("Repo", "🔥 Email error: ${e.message}", e)
                }
                Log.d("Repo", "🚀 Background email task END")
            }

            Log.d("Repo", "━━━━━━━━━━ SUCCESS sendEvaluationFormToFlm ━━━━━━━━━━")

            val prefs= context.getSharedPreferences("worker_prefs", Context.MODE_PRIVATE)

            val idsString = prefs.getString("pending_formation_ids", "") ?: ""

            val idsList = idsString
                .split(",")
                .filter { it.isNotBlank() }
                .toMutableList()

            idsList.remove(formation.id.toString())

            val updated = idsList.joinToString(",")

            prefs.edit()
                .putString("pending_formation_ids", updated)
                .apply()

            Result.success(saved.copy(firebaseId = firebaseId))



        } catch (e: Exception) {
            Log.e("Repo", "🔥 MAIN ERROR: ${e.message}", e)
            Result.failure(e)
        }
    }




    // ── Build Pre-filled URL ───────────────────────────────────────────────────
    fun buildPreFilledUrl(
        form         : Forms,
        formation    : FormationEntity,
        collaborateur: CollaborateurEntity,
        theme        : ThemeEntity?,
        firebaseId   : String  // ← add this
    ): String {
        fun encode(v: String) = java.net.URLEncoder.encode(v, "UTF-8")

        val url = "${form.formUrl}?usp=pp_url" +
                "&entry.${form.entryIds.formationId}=${encode(firebaseId)}" + // ← was formation.id
                "&entry.${form.entryIds.intituleAction}=${encode(theme?.nom ?: "")}" +
                "&entry.${form.entryIds.nomPrenom}=${encode("${collaborateur.prenom} ${collaborateur.nom}")}" +
                "&entry.${form.entryIds.matricule}=${encode(collaborateur.matricule)}" +
                "&entry.${form.entryIds.service}=${encode(collaborateur.service)}" +
                "&entry.${form.entryIds.formateur}=${encode(formation.Formateur)}" +
                "&entry.${form.entryIds.dates}=${encode("${dateHelper.excelDateToString(formation.debut.toDouble())} - ${dateHelper.excelDateToString(formation.fin.toDouble())}")}"

        return url
    }


    // ── Send All Invitations ───────────────────────────────────────────────────
    suspend fun sendAllPendingInvitations(
        formations: List<FormationEntity>
    ): Result<Int> {
        return try {
            var count = 0

            val groupedByCollab = formations.groupBy { it.collaborateurMatricule }

            groupedByCollab.forEach { (matricule, collabFormations) ->

                // ── Skip already sent ──────────────────────────────
                val toSend = collabFormations.filter { formation ->
                    val existing = invitationDao.getByFormationId(formation.id)
                    existing == null || existing.statut == InvitationStatus.NON_EXPEDIEE
                }

                if (toSend.isEmpty()) return@forEach

                val collab = collaborateurDao.getByMatricule(matricule) ?: return@forEach
                val flm    = collab.flmMatricule?.let { flmDao.getByMatricule(it) } ?: return@forEach

                // ── Save each invitation and collect (theme, url) pairs ──
                val formationLinks = mutableListOf<Pair<String, String>>()

                toSend.forEach { formation ->
                    val theme = themeDao.getById(formation.themeId)
                    val form  = theme?.let { formDao.getByThemeId(it.id) }

                    // ── Save to Room ───────────────────────────────
                    val invitation = InvitationFlmEntity(
                        formationId             = formation.id,
                        datesFormation          = "${formation.debut} - ${formation.fin}",
                        formateur               = formation.Formateur,
                        matriculeCollaborateur  = collab.matricule,
                        nomCompletCollaborateur = "${collab.prenom} ${collab.nom}",
                        service                 = collab.service,
                        themeNom                = theme?.nom ?: "—",
                        themeObjectives         = theme?.objectifPedagogique
                            ?.split("•")?.map { it.trim() }?.filter { it.isNotBlank() } ?: emptyList(),
                        emailFlm                = flm.email,
                        nomFlm                  = "${flm.prenom} ${flm.nom}",
                        statut                  = InvitationStatus.EN_ATTENTE,
                        dateEnvoi               = System.currentTimeMillis()
                    )
                    val roomId = invitationDao.insert(invitation)
                    Log.d("Repo", "💾 Room insert: roomId=$roomId formation=${formation.id}")

                    // ── Save to Firebase — await firebaseId ────────
                    val firebaseId = firebase.saveInvitation(invitation.copy(id = roomId)).getOrElse {
                        Log.e("Repo", "❌ Firebase save failed for formation=${formation.id}: ${it.message}")
                        ""
                    }
                    Log.d("Repo", "✅ Firebase ID: $firebaseId for formation=${formation.id}")

                    // ── Update Room with firebaseId ────────────────
                    if (firebaseId.isNotBlank()) {
                        invitationDao.updateFirebaseId(roomId, firebaseId)
                    }

                    // ── Build prefilled URL using firebaseId ───────
                    if (form != null && theme != null && firebaseId.isNotBlank()) {
                        val url = buildPreFilledUrl(form, formation, collab, theme, firebaseId)
                        Log.d("Repo", "🔗 URL for '${theme.nom}': $url")
                        formationLinks.add(Pair(theme.nom, url))
                    } else {
                        Log.w("Repo", "⚠️ Skipping URL for formation=${formation.id} — missing form/theme/firebaseId")
                    }
                }

                if (formationLinks.isEmpty()) {
                    Log.w("Repo", "⚠️ No valid links for ${collab.prenom} ${collab.nom} — skipping email")
                    return@forEach
                }

                // ── Send ONE grouped email with all links ──────────
                val subject = "Évaluation Formation — ${collab.prenom} ${collab.nom}"
                val body    = EmailHelper.buildGroupedInvitationBody(
                    flmNom        = "${flm.prenom} ${flm.nom}",
                    collaborateur = "${collab.prenom} ${collab.nom}",
                    formations    = formationLinks
                )
                EmailHelper.sendEmail(to = flm.email, subject = subject, body = body)
                Log.d("Repo", "📧 Email sent to ${flm.email} with ${formationLinks.size} link(s)")

                count += toSend.size
            }

            Result.success(count)
        } catch (e: Exception) {
            Log.e("Repo", "🔥 sendAllPendingInvitations ERROR: ${e.message}", e)
            Result.failure(e)
        }
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
