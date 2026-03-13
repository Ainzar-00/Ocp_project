package com.ocp.evalformation.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ocp.evalformation.data.local.entity.*

/**
 * DAO implementations with bulk upsert helpers used by syncRepository.
 *
 * NOTE: these implementations assume your entity data classes have the fields used below:
 * - ThemeEntity(id: Long, nom: String, objectifPedagogique: String, syncedToFirebase: Boolean)
 * - FlmEntity(matricule: String, nom: String, prenom: String, email: String, service: String, syncedToFirebase: Boolean, id: Long = 0)
 * - CollaborateurEntity(matricule: String, nom: String, prenom: String, service: String, flmMatricule: String?, syncedToFirebase: Boolean, id: Long = 0)
 * - FormationEntity(id: Long, collaborateurMatricule: String, themeId: Long, debut: String, fin: String, syncedToFirebase: Boolean)
 *
 * Adjust the field comparisons if your real entity definitions differ.
 */

/* ======================================================
   THEME DAO
   ====================================================== */
@Dao
interface ThemeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(themes: List<ThemeEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(theme: ThemeEntity): Long

    @Update
    suspend fun update(theme: ThemeEntity)

    @Update
    suspend fun updateAll(themes: List<ThemeEntity>)

    @Query("SELECT COUNT(*) FROM themes")
    suspend fun count(): Int

    @Query("SELECT * FROM themes ORDER BY nom ASC")
    fun getAllLive(): LiveData<List<ThemeEntity>>

    @Query("SELECT * FROM themes ORDER BY nom ASC")
    suspend fun getAll(): List<ThemeEntity>

    @Query("SELECT * FROM themes WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): ThemeEntity?

    @Query("SELECT * FROM themes WHERE nom = :nom LIMIT 1")
    suspend fun getByName(nom: String): ThemeEntity?

    @Query("SELECT * FROM themes WHERE syncedToFirebase = 0")
    suspend fun getUnsynced(): List<ThemeEntity>

    @Query("UPDATE themes SET syncedToFirebase = 1 WHERE id IN (:ids)")
    suspend fun markSynced(ids: List<Long>)

    @Query("DELETE FROM themes")
    suspend fun deleteAll()

    @Query("DELETE FROM sqlite_sequence WHERE name='themes'")
    suspend fun resetIds()

    /**
     * Bulk upsert according to rules:
     * - identical line -> ignore
     * - same nom but different attributes -> update (preserve id)
     * - new -> insert
     *
     * Returns the list of affected ids (inserted or updated) so caller can mark them synced.
     */
    @Transaction
    suspend fun upsertThemes(themes: List<ThemeEntity>): List<Long> {
        val affectedIds = mutableListOf<Long>()

        for (t in themes) {
            val existing = getByName(t.nom)
            if (existing == null) {
                val newId = insert(t)
                if (newId != -1L) affectedIds.add(newId)
                // if insert was ignored (-1) it's probably a conflict; try to fetch again
                if (newId == -1L) {
                    val re = getByName(t.nom)
                    if (re != null) affectedIds.add(re.id)
                }
                continue
            }

            // consider rows identical if all relevant fields match (ignoring id & synced flag)
            val identical = existing.nom == t.nom &&
                    (existing.objectifPedagogique ?: "") == (t.objectifPedagogique ?: "")

            if (identical) {
                // nothing to do
                continue
            }

            // same nom but different attributes -> update existing (preserve id)
            val updated = existing.copy(
                objectifPedagogique = t.objectifPedagogique,
                // preserve syncedToFirebase as-is (we will mark after upload)
                // if you want to copy synced flag from incoming replace here
            )
            update(updated)
            affectedIds.add(existing.id)
        }

        return affectedIds
    }
}


/* ======================================================
   FLM DAO
   ====================================================== */

@Dao
interface FlmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flm: FlmEntity): Long

    // your earlier DAO used REPLACE for insertAll
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(flms: List<FlmEntity>)

    @Update
    suspend fun update(flm: FlmEntity)

    @Update
    suspend fun updateAll(flms: List<FlmEntity>)

    @Query("SELECT * FROM flms ORDER BY nom ASC")
    fun getAllLive(): LiveData<List<FlmEntity>>

    @Query("SELECT * FROM flms ORDER BY nom ASC")
    suspend fun getAll(): List<FlmEntity>

    @Query("SELECT COUNT(*) FROM flms")
    suspend fun count(): Int

    @Query("SELECT * FROM flms WHERE matricule = :matricule LIMIT 1")
    suspend fun getByMatricule(matricule: String): FlmEntity?

    @Query("SELECT * FROM flms WHERE syncedToFirebase = 0")
    suspend fun getUnsynced(): List<FlmEntity>

    @Query("UPDATE flms SET syncedToFirebase = 1 WHERE matricule IN (:matricules)")
    suspend fun markSynced(matricules: List<String>)

    @Query("DELETE FROM flms")
    suspend fun deleteAll()

    /**
     * Bulk upsert for FLMs:
     * - identical -> ignore
     * - same matricule -> update other fields (preserve matricule)
     * - new -> insert
     *
     * Returns list of matricules that were inserted/updated (so caller can mark them synced).
     */
    @Transaction
    suspend fun upsertFlms(flms: List<FlmEntity>): List<String> {
        val affected = mutableListOf<String>()

        for (f in flms) {
            val existing = getByMatricule(f.matricule)
            if (existing == null) {
                insert(f)
                affected.add(f.matricule)
                continue
            }

            // compare relevant fields (ignore matricule and synced flag)
            val identical = existing.nom == f.nom &&
                    existing.prenom == f.prenom &&
                    (existing.email ?: "") == (f.email ?: "") &&
                    (existing.service ?: "") == (f.service ?: "")

            if (identical) continue

            // update preserving matricule
            val updated = existing.copy(
                nom = f.nom,
                prenom = f.prenom,
                email = f.email,
                service = f.service
                // keep existing.syncedToFirebase (we'll mark after upload if needed)
            )
            update(updated)
            affected.add(existing.matricule)
        }

        return affected
    }
}


/* ======================================================
   COLLABORATEUR DAO
   ====================================================== */

@Dao
interface CollaborateurDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(collaborateur: CollaborateurEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(collaborateurs: List<CollaborateurEntity>)

    @Update
    suspend fun update(collaborateur: CollaborateurEntity)

    @Update
    suspend fun updateAll(collaborateurs: List<CollaborateurEntity>)

    @Query("SELECT * FROM collaborateurs ORDER BY nom ASC")
    fun getAllLive(): LiveData<List<CollaborateurEntity>>

    @Query("SELECT * FROM collaborateurs ORDER BY nom ASC")
    suspend fun getAll(): List<CollaborateurEntity>

    @Query("SELECT matricule FROM collaborateurs")
    suspend fun getAllMatricules(): List<String>

    @Query("SELECT COUNT(*) FROM collaborateurs")
    suspend fun count(): Int

    @Query("SELECT * FROM collaborateurs WHERE matricule = :matricule LIMIT 1")
    suspend fun getByMatricule(matricule: String): CollaborateurEntity?

    @Query("SELECT * FROM collaborateurs WHERE syncedToFirebase = 0")
    suspend fun getUnsynced(): List<CollaborateurEntity>

    @Query("UPDATE collaborateurs SET syncedToFirebase = 1 WHERE matricule IN (:matricules)")
    suspend fun markSynced(matricules: List<String>)

    @Query("DELETE FROM collaborateurs")
    suspend fun deleteAll()

    /**
     * Bulk upsert for Collaborateurs:
     * - identical -> ignore
     * - same matricule -> update other fields (preserve matricule)
     * - new -> insert
     *
     * Returns matricules that were inserted/updated (so caller can mark them synced).
     */
    @Transaction
    suspend fun upsertCollaborateurs(collabs: List<CollaborateurEntity>): List<String> {
        val affected = mutableListOf<String>()

        for (c in collabs) {
            val existing = getByMatricule(c.matricule)
            if (existing == null) {
                insert(c)
                affected.add(c.matricule)
                continue
            }

            // compare relevant fields (ignore matricule and synced flag)
            val identical = existing.nom == c.nom &&
                    existing.prenom == c.prenom &&
                    (existing.service ?: "") == (c.service ?: "") &&
                    (existing.flmMatricule ?: "") == (c.flmMatricule ?: "")

            if (identical) continue

            val updated = existing.copy(
                nom = c.nom,
                prenom = c.prenom,
                service = c.service,
                flmMatricule = c.flmMatricule
            )
            update(updated)
            affected.add(existing.matricule)
        }

        return affected
    }
}


/* ======================================================
   FORMATION DAO
   ====================================================== */

@Dao
interface FormationDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(formation: FormationEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(formations: List<FormationEntity>): List<Long>

    @Update
    suspend fun update(formation: FormationEntity)

    @Query("SELECT * FROM formations ORDER BY debut DESC")
    fun getAllLive(): LiveData<List<FormationEntity>>

    @Query("SELECT * FROM formations ORDER BY debut DESC")
    suspend fun getAll(): List<FormationEntity>

    @Query("SELECT * FROM formations WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): FormationEntity?

    @Query("SELECT * FROM formations WHERE collaborateurMatricule = :matricule")
    suspend fun getByCollaborateur(matricule: String): List<FormationEntity>

    @Query("SELECT * FROM formations WHERE themeId = :themeId")
    suspend fun getByTheme(themeId: Long): List<FormationEntity>

    @Query("SELECT COUNT(*) FROM formations")
    suspend fun count(): Int

    @Query("SELECT * FROM formations WHERE syncedToFirebase = 0")
    suspend fun getUnsynced(): List<FormationEntity>

    @Query("UPDATE formations SET syncedToFirebase = 1 WHERE id IN (:ids)")
    suspend fun markSynced(ids: List<Long>)

    @Query("DELETE FROM formations")
    suspend fun deleteAll()

    @Query("DELETE FROM sqlite_sequence WHERE name='formations'")
    suspend fun resetIds()
}


/* ======================================================
   EVALUATION DAO (kept unchanged)
   ====================================================== */

@Dao
interface EvaluationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(evaluation: EvaluationEntity): Long

    @Update
    suspend fun update(evaluation: EvaluationEntity)

    @Query("SELECT * FROM evaluations ORDER BY createdAt DESC")
    fun getAllLive(): LiveData<List<EvaluationEntity>>

    @Query("SELECT * FROM evaluations ORDER BY createdAt DESC")
    suspend fun getAll(): List<EvaluationEntity>

    @Query("SELECT * FROM evaluations WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): EvaluationEntity?

    @Query("SELECT * FROM evaluations WHERE formationId = :formationId")
    suspend fun getByFormationId(formationId: Long): List<EvaluationEntity>

    @Query("SELECT COUNT(*) FROM evaluations")
    suspend fun count(): Int

    @Query("SELECT * FROM evaluations WHERE syncedToFirebase = 0")
    suspend fun getUnsynced(): List<EvaluationEntity>

    @Query("UPDATE evaluations SET syncedToFirebase = 1 WHERE id IN (:ids)")
    suspend fun markSynced(ids: List<Long>)

    @Query("DELETE FROM evaluations")
    suspend fun deleteAll()
}


/* ======================================================
   INVITATION FLM DAO (kept unchanged)
   ====================================================== */

@Dao
interface InvitationFlmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(invitation: InvitationFlmEntity): Long

    @Update
    suspend fun update(invitation: InvitationFlmEntity)

    @Query("SELECT * FROM invitations_flm ORDER BY dateEnvoi DESC")
    fun getAllLive(): LiveData<List<InvitationFlmEntity>>

    @Query("SELECT * FROM invitations_flm WHERE statut = 'ENVOYE'")
    fun getPendingLive(): LiveData<List<InvitationFlmEntity>>

    @Query("SELECT COUNT(*) FROM invitations_flm WHERE statut = 'ENVOYE'")
    fun countPendingLive(): LiveData<Int>

    @Query("UPDATE invitations_flm SET statut = 'REPONDU', dateReponse = :date, evaluationId = :evalId WHERE id = :id")
    suspend fun markReplied(id: Long, date: Long, evalId: Long)
}