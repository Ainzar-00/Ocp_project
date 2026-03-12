package com.ocp.evalformation.data.remote

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ocp.evalformation.data.local.dao.FormationDao
import com.ocp.evalformation.data.local.dao.ThemeDao
import com.ocp.evalformation.data.local.entity.*
import com.ocp.evalformation.model.UserRole
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    // DAOs injected so we can insert locally first and obtain auto-generated IDs
    private val themeDao: ThemeDao,
    private val formationDao: FormationDao
) {

    // ───────────────── AUTH ─────────────────

    suspend fun login(email: String, password: String): Result<UserRole> {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password).await()
            val uid = result.user?.uid ?: return Result.failure(Exception("UID null"))

            val doc = firestore.collection("users").document(uid).get().await()
            val role = doc.getString("role") ?: "FLM"

            Result.success(UserRole.valueOf(role))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() = auth.signOut()
    fun currentUserEmail() = auth.currentUser?.email ?: ""
    fun isLoggedIn() = auth.currentUser != null


    // ───────────────── RESET AUTOINCREMENT (sqlite_sequence) ─────────────────
    //
    // Use these methods if you want the next Room auto-generated id to start from 1.
    // They delete the table content then remove the sqlite_sequence entry.
    //
    // WARNING: This will permanently delete local data. Use only for testing or
    // when you are sure you want to wipe local tables and restart IDs.

    suspend fun resetThemesAutoincrement(): Result<Unit> {
        return try {
            withContext(Dispatchers.IO) {
                // delete all rows then reset sqlite_sequence for 'themes'
                themeDao.deleteAll()
                themeDao.resetIds()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun resetFormationsAutoincrement(): Result<Unit> {
        return try {
            withContext(Dispatchers.IO) {
                formationDao.deleteAll()
                formationDao.resetIds()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Convenience: reset both themes and formations.
     */
    suspend fun resetAllAutoincrements(): Result<Unit> {
        return try {
            withContext(Dispatchers.IO) {
                themeDao.deleteAll()
                themeDao.resetIds()

                formationDao.deleteAll()
                formationDao.resetIds()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    // ───────────────── THEMES (ROOM-first -> FIRESTORE) ─────────────────

    /**
     * Insert theme into Room first (so Room will generate the auto-increment id),
     * then read it back and upload to Firestore using that id as the "id" field
     * and as the Firestore document ID.
     *
     * Assumes ThemeDao.insert(theme): Long and ThemeDao.getById(id): ThemeEntity?
     */
    suspend fun addTheme(theme: ThemeEntity): Result<Long> {

        return try {
            // insert into Room (returns generated id)
            val roomId = withContext(Dispatchers.IO) {
                themeDao.insert(theme)
            }

            // read back saved entity (so we get exact persisted data)
            val saved = withContext(Dispatchers.IO) { themeDao.getById(roomId) }
                ?: return Result.failure(Exception("Theme not found after insert (id=$roomId)"))

            // prepare map and ensure id field is the Room id
            val data = saved.toMap().toMutableMap()
            data["id"] = roomId

            // upload to Firestore under document id = roomId
            val ref = firestore.collection("themes").document(roomId.toString())
            ref.set(data).await()

            Result.success(roomId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Upload a list of themes to Firestore only (Room insert is the caller's responsibility).
     * Themes must already have their Room-generated IDs set.
     */
    suspend fun uploadThemes(list: List<ThemeEntity>): Result<Unit> {
        return try {
            // don't reset or delete local Room data here.
            // upload using the theme.id field (caller is responsible for ensuring ids are set)
            list.chunked(400).forEach { chunk ->
                val batch = firestore.batch()
                chunk.forEach { theme ->
                    val ref = firestore.collection("themes").document(theme.id.toString())
                    batch.set(ref, theme.toMap())
                }
                batch.commit().await()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    // ───────────────── FLM ─────────────────
    // unchanged behavior (same as your original file)

    suspend fun uploadFlms(list: List<FlmEntity>): Result<Unit> {
        return try {
            list.chunked(400).forEach { chunk ->
                val batch = firestore.batch()
                chunk.forEach { flm ->
                    val ref = firestore.collection("flms").document(flm.matricule)
                    batch.set(ref, flm.toMap())
                }
                batch.commit().await()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    // ───────────────── COLLABORATEURS ─────────────────
    // unchanged behavior

    suspend fun uploadCollaborateurs(list: List<CollaborateurEntity>): Result<Unit> {
        return try {
            list.chunked(400).forEach { chunk ->
                val batch = firestore.batch()
                chunk.forEach { collab ->
                    val ref = firestore.collection("collaborateurs").document(collab.matricule)
                    batch.set(ref, collab.toMap())
                }
                batch.commit().await()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    // ───────────────── FORMATIONS (ROOM-first -> FIRESTORE) ─────────────────

    /**
     * Insert formation into Room first (so Room will generate the auto-increment id),
     * then read it back and upload to Firestore using that id as the "id" field
     * and as the Firestore document ID.
     *
     * Assumes FormationDao.insert(formation): Long and FormationDao.getById(id): FormationEntity?
     */
    suspend fun addFormationLocalThenUpload(formation: FormationEntity): Result<Long> {
        return try {
            // insert into Room -> get generated id
            val roomId = withContext(Dispatchers.IO) {
                formationDao.insert(formation)
            }

            // read back the saved formation
            val saved = withContext(Dispatchers.IO) { formationDao.getById(roomId) }
                ?: return Result.failure(Exception("Formation not found after insert (id=$roomId)"))

            // prepare data and ensure id field is the Room id
            val data = saved.toMap().toMutableMap()
            data["id"] = roomId

            // upload to Firestore under document id = roomId
            val ref = firestore.collection("formations").document(roomId.toString())
            ref.set(data).await()

            Result.success(roomId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * uploadFormations expects the formations to have their id field already set (Room-generated ids).
     * The caller should ensure formation.id values are correct before calling this method.
     */
    suspend fun uploadFormations(list: List<FormationEntity>): Result<Unit> {
        return try {
            list.chunked(400).forEach { chunk ->
                val batch = firestore.batch()
                chunk.forEach { formation ->
                    val data = formation.toMap().toMutableMap()
                    data["id"] = formation.id
                    val ref = firestore.collection("formations").document(formation.id.toString())
                    batch.set(ref, data)
                }
                batch.commit().await()
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    // ───────────────── EVALUATIONS ─────────────────
    // unchanged behavior

    suspend fun uploadEvaluation(eval: EvaluationEntity): Result<String> {
        return try {
            val ref = firestore.collection("evaluations").document()
            ref.set(eval.toMap()).await()
            Result.success(ref.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun uploadEvaluationsBatch(evals: List<EvaluationEntity>): Result<Unit> {
        return try {
            val batch = firestore.batch()

            evals.forEach { eval ->
                val ref = firestore.collection("evaluations").document()
                batch.set(ref, eval.toMap())
            }

            batch.commit().await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    // ───────────────── INVITATIONS ─────────────────
    // unchanged

    suspend fun saveInvitation(inv: InvitationFlmEntity): Result<String> {
        return try {
            val ref = firestore.collection("invitations_flm").document()
            ref.set(inv.toMap(ref.id)).await()
            Result.success(ref.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    // ───────────────── SYNC: FIREBASE → ROOM ─────────────────
    // unchanged fetchers (left as in your file)

    suspend fun fetchThemes(): List<ThemeEntity> {
        return try {
            firestore.collection("themes").get().await()
                .documents.mapNotNull { doc ->
                    ThemeEntity(
                        id = doc.getLong("id") ?: 0L,
                        nom = doc.getString("nom") ?: "",
                        objectifPedagogique = doc.getString("objectifPedagogique") ?: ""
                    )
                }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun fetchFlms(): List<FlmEntity> {
        return try {
            firestore.collection("flms").get().await()
                .documents.mapNotNull { doc ->
                    FlmEntity(
                        matricule = doc.getString("matricule") ?: return@mapNotNull null,
                        nom = doc.getString("nom") ?: "",
                        prenom = doc.getString("prenom") ?: "",
                        email = doc.getString("email") ?: "",
                        service = doc.getString("service") ?: ""
                    )
                }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun fetchCollaborateurs(): List<CollaborateurEntity> {
        return try {
            firestore.collection("collaborateurs").get().await()
                .documents.mapNotNull { doc ->
                    CollaborateurEntity(
                        matricule = doc.getString("matricule") ?: return@mapNotNull null,
                        nom = doc.getString("nom") ?: "",
                        prenom = doc.getString("prenom") ?: "",
                        service = doc.getString("service") ?: "",
                        flmMatricule = doc.getString("flmMatricule") ?: ""
                    )
                }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun fetchFormations(): List<FormationEntity> {
        return try {
            val querySnap = firestore.collection("formations").get().await()
            val list = mutableListOf<FormationEntity>()

            for (doc in querySnap.documents) {
                // raw values (could be Long, Double, String, or missing)
                val idRaw = doc.get("id")
                val collabRaw = doc.get("collaborateurMatricule")
                val themeRaw = doc.get("themeId")
                val debut = doc.getString("debut") ?: ""
                val fin = doc.getString("fin") ?: ""

                // helper to coerce numeric-like values to Long
                fun anyToLong(v: Any?): Long? {
                    return when (v) {
                        is Long -> v
                        is Int -> v.toLong()
                        is Double -> if (v == kotlin.math.floor(v)) v.toLong() else v.toLong()
                        is String -> v.toLongOrNull()
                        else -> null
                    }
                }

                val collab = when (collabRaw) {
                    is String -> collabRaw.takeIf { it.isNotBlank() }
                    else -> null
                }

                val themeId = anyToLong(themeRaw)
                val id = anyToLong(idRaw) ?: 0L

                if (collab == null) {
                    Log.w("SYNC", "Skipping formation doc=${doc.id} -> missing/invalid 'collaborateurMatricule' (raw=$collabRaw)")
                    continue
                }
                if (themeId == null) {
                    Log.w("SYNC", "Skipping formation doc=${doc.id} -> missing/invalid 'themeId' (raw=$themeRaw)")
                    continue
                }

                list.add(
                    FormationEntity(
                        id = id,
                        collaborateurMatricule = collab,
                        themeId = themeId,
                        debut = debut,
                        fin = fin
                    )
                )
            }

            Log.d("SYNC", "fetchFormations: total documents=${querySnap.size()}, accepted=${list.size}")
            list
        } catch (e: Exception) {
            Log.e("SYNC", "fetchFormations error", e)
            emptyList()
        }
    }


}



// ───────────────── toMap EXTENSIONS ─────────────────

fun ThemeEntity.toMap() = mapOf(
    "id" to id,
    "nom" to nom,
    "objectifPedagogique" to objectifPedagogique
)

fun FlmEntity.toMap() = mapOf(
    "matricule" to matricule,
    "nom" to nom,
    "prenom" to prenom,
    "email" to email,
    "service" to service
)

fun CollaborateurEntity.toMap() = mapOf(
    "matricule" to matricule,
    "nom" to nom,
    "prenom" to prenom,
    "service" to service,
    "flmMatricule" to flmMatricule
)

fun FormationEntity.toMap() = mapOf(
    "id" to id,
    "collaborateurMatricule" to collaborateurMatricule,
    "themeId" to themeId,
    "debut" to debut,
    "fin" to fin
)

fun EvaluationEntity.toMap() = mapOf(
    "formationId" to formationId,
    "dateEvaluation" to dateEvaluation,
    "flmMatricule" to flmMatricule,
    "flmNom" to flmNom,
    "organisationContenu" to organisationContenu,
    "qualitePedagogique" to qualitePedagogique,
    "adaptationPublic" to adaptationPublic,
    "maitriseSujet" to maitriseSujet,
    "disponibiliteFormateur" to disponibiliteFormateur,
    "qualiteSupports" to qualiteSupports,
    "atteinteObjectifs" to atteinteObjectifs,
    "applicationTerrain" to applicationTerrain,
    "propositionsAmelioration" to propositionsAmelioration,
    "commentaireGeneral" to commentaireGeneral,
    "competencesAcquises" to competencesAcquises,
    "statut" to statut,
    "createdAt" to createdAt,
    "googleFormResponseId" to googleFormResponseId
)

fun InvitationFlmEntity.toMap(firebaseId: String) = mapOf(
    "firebaseId" to firebaseId,
    "formationId" to formationId,
    "matriculeCollaborateur" to matriculeCollaborateur,
    "nomCompletCollaborateur" to nomCompletCollaborateur,
    "themeNom" to themeNom,
    "emailFlm" to emailFlm,
    "nomFlm" to nomFlm,
    "googleFormUrl" to googleFormUrl,
    "statut" to statut,
    "dateEnvoi" to dateEnvoi
)