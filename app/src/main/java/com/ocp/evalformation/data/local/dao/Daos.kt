package com.ocp.evalformation.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ocp.evalformation.data.local.entity.*
import kotlinx.coroutines.flow.Flow


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

    @Query("SELECT * FROM themes WHERE id = :id LIMIT 1")
    fun getByIdLive(id: Long): LiveData<ThemeEntity?>

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

    @Transaction
    suspend fun upsertThemes(themes: List<ThemeEntity>): List<Long> {
        val affectedIds = mutableListOf<Long>()
        for (t in themes) {
            val existing = getByName(t.nom)
            if (existing == null) {
                val newId = insert(t)
                if (newId != -1L) affectedIds.add(newId)
                if (newId == -1L) { val re = getByName(t.nom); if (re != null) affectedIds.add(re.id) }
                continue
            }
            val identical = existing.nom == t.nom &&
                    (existing.objectifPedagogique ?: "") == (t.objectifPedagogique ?: "")
            if (identical) continue
            val updated = existing.copy(objectifPedagogique = t.objectifPedagogique)
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

    @Transaction
    suspend fun upsertFlms(flms: List<FlmEntity>): List<String> {
        val affected = mutableListOf<String>()
        for (f in flms) {
            val existing = getByMatricule(f.matricule)
            if (existing == null) { insert(f); affected.add(f.matricule); continue }
            val identical = existing.nom == f.nom && existing.prenom == f.prenom &&
                    (existing.email ?: "") == (f.email ?: "") &&
                    (existing.service ?: "") == (f.service ?: "")
            if (identical) continue
            update(existing.copy(nom = f.nom, prenom = f.prenom, email = f.email, service = f.service))
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

    @Transaction
    suspend fun upsertCollaborateurs(collabs: List<CollaborateurEntity>): List<String> {
        val affected = mutableListOf<String>()
        for (c in collabs) {
            val existing = getByMatricule(c.matricule)
            if (existing == null) { insert(c); affected.add(c.matricule); continue }
            val identical = existing.nom == c.nom && existing.prenom == c.prenom &&
                    (existing.service ?: "") == (c.service ?: "") &&
                    (existing.flmMatricule ?: "") == (c.flmMatricule ?: "")
            if (identical) continue
            update(existing.copy(nom = c.nom, prenom = c.prenom, service = c.service, flmMatricule = c.flmMatricule))
            affected.add(existing.matricule)
        }
        return affected
    }

    // CollaborateurDao
    @Query("SELECT COUNT(*) FROM collaborateurs")
    fun countLive(): LiveData<Int>
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

    // ── Dashboard queries (filtered by current year via dateAppreciation) ──────

    // Distinct collaborateurs with at least one formation in current year
    @Query("SELECT COUNT(DISTINCT collaborateurMatricule) FROM formations WHERE CAST(dateAppreciation AS INTEGER) BETWEEN :start AND :end")
    fun countCollaborateursWithFormationByYear(start: Int, end: Int): LiveData<Int>

    // Distinct themes in formations in current year
    @Query("SELECT COUNT(DISTINCT themeId) FROM formations WHERE CAST(dateAppreciation AS INTEGER) BETWEEN :start AND :end")
    fun countDistinctThemesByYear(start: Int, end: Int): LiveData<Int>

    // Sum of JSP where presence is true in current year
    @Query("SELECT SUM(CAST(jsp AS REAL)) FROM formations WHERE (presence = 'true' OR presence = '1' OR presence = 'Présent' OR presence = 'présent') AND CAST(dateAppreciation AS INTEGER) BETWEEN :start AND :end")
    fun sumJspByYear(start: Int, end: Int): LiveData<Double?>

    // Most recurrent theme id in current year
    @Query("""
        SELECT themeId FROM formations 
        WHERE CAST(dateAppreciation AS INTEGER) BETWEEN :start AND :end
        GROUP BY themeId 
        ORDER BY COUNT(*) DESC 
        LIMIT 1
    """)
    fun getMostRecurrentThemeIdByYear(start: Int, end: Int): LiveData<Long?>
}

/* ======================================================
   INVITATION FLM DAO
   ====================================================== */
@Dao
interface InvitationFlmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(invitation: InvitationFlmEntity): Long

    @Update
    suspend fun update(invitation: InvitationFlmEntity)

    @Query("SELECT * FROM invitations_flm ORDER BY dateEnvoi DESC")
    fun getAllLive(): LiveData<List<InvitationFlmEntity>>

    @Query("SELECT * FROM invitations_flm WHERE statut = 'EN_ATTENTE' ORDER BY dateEnvoi DESC")
    fun getPendingLive(): LiveData<List<InvitationFlmEntity>>

    @Query("SELECT COUNT(*) FROM invitations_flm WHERE statut = 'EN_ATTENTE'")
    fun countPendingLive(): LiveData<Int>

    @Query("SELECT * FROM invitations_flm WHERE formationId = :formationId LIMIT 1")
    suspend fun getByFormationId(formationId: Long): InvitationFlmEntity?

    @Query("UPDATE invitations_flm SET statut = :statut WHERE id = :id")
    suspend fun updateStatut(id: Long, statut: InvitationStatus)

    @Query("UPDATE invitations_flm SET statut = 'REPONDUE' WHERE formationId = :formationId")
    suspend fun markAsReplied(formationId: Long)

    @Query("DELETE FROM invitations_flm")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM invitations_flm")
    suspend fun count(): Int

    // InvitationFlmDao — already exists as countPendingLive(), just add alias
    @Query("SELECT COUNT(*) FROM invitations_flm WHERE statut = 'EN_ATTENTE'")
    fun countEnAttenteLive(): LiveData<Int>
}

/* ======================================================
   FORM DAO
   ====================================================== */
@Dao
interface FormDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(form: Forms): Long

    @Query("SELECT * FROM forms")
    fun getAllLive(): LiveData<List<Forms>>

    @Query("SELECT * FROM forms WHERE themeId = :themeId LIMIT 1")
    suspend fun getByThemeId(themeId: Long): Forms?

    @Query("DELETE FROM forms WHERE themeId = :themeId")
    suspend fun deleteByThemeId(themeId: Long)

    @Query("SELECT COUNT(*) FROM FORMS")
    suspend fun count(): Int

}

/* ======================================================
   EVALUATION DAO — stub (entity commented out for now)
   ====================================================== */
@Dao
interface EvaluationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(evaluation: EvaluationEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(evaluations: List<EvaluationEntity>)

    @Update
    suspend fun update(evaluation: EvaluationEntity)

    @Delete
    suspend fun delete(evaluation: EvaluationEntity)

    @Query("DELETE FROM evaluations")
    suspend fun deleteAll()

    @Query("SELECT * FROM evaluations")
    fun getAll(): Flow<List<EvaluationEntity>>

    @Query("SELECT * FROM evaluations WHERE formationId = :formationId")
    suspend fun getByFormationId(formationId: Long): EvaluationEntity?

    @Query("SELECT * FROM evaluations WHERE id = :id")
    suspend fun getById(id: Long): EvaluationEntity?

    @Query("SELECT COUNT(*) FROM evaluations")
    suspend fun count(): Int

    // EvaluationDao
    @Query("SELECT COUNT(*) FROM evaluations")
    fun countLive(): LiveData<Int>
}