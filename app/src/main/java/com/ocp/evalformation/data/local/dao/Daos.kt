package com.ocp.evalformation.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ocp.evalformation.data.local.entity.*

@Dao
interface ThemeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(theme: ThemeEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(themes: List<ThemeEntity>): List<Long>

    @Query("SELECT COUNT(*) FROM themes")
    suspend fun count(): Int

    @Query("SELECT * FROM themes ORDER BY nom ASC")
    fun getAllLive(): LiveData<List<ThemeEntity>>

    @Query("SELECT * FROM themes ORDER BY nom ASC")
    suspend fun getAll(): List<ThemeEntity>

    @Query("SELECT * FROM themes WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): ThemeEntity?

    @Query("SELECT * FROM themes WHERE syncedToFirebase = 0")
    suspend fun getUnsynced(): List<ThemeEntity>

    @Query("UPDATE themes SET syncedToFirebase = 1 WHERE id IN (:ids)")
    suspend fun markSynced(ids: List<Long>)

    @Query("DELETE FROM themes")
    suspend fun deleteAll()

    @Query("DELETE FROM sqlite_sequence WHERE name='themes'")
    suspend fun resetIds()
}

@Dao
interface FlmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(flm: FlmEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(flms: List<FlmEntity>)

    @Query("SELECT * FROM flms ORDER BY nom ASC")
    fun getAllLive(): LiveData<List<FlmEntity>>

    @Query("SELECT COUNT(*) FROM flms")
    suspend fun count(): Int

    @Query("SELECT * FROM flms ORDER BY nom ASC")
    suspend fun getAll(): List<FlmEntity>

    @Query("SELECT * FROM flms WHERE matricule = :matricule LIMIT 1")
    suspend fun getByMatricule(matricule: String): FlmEntity?

    @Query("SELECT * FROM flms WHERE syncedToFirebase = 0")
    suspend fun getUnsynced(): List<FlmEntity>

    @Query("UPDATE flms SET syncedToFirebase = 1 WHERE matricule IN (:matricules)")
    suspend fun markSynced(matricules: List<String>)

    @Query("DELETE FROM flms")
    suspend fun deleteAll()
}

@Dao
interface CollaborateurDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(collaborateur: CollaborateurEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(collaborateurs: List<CollaborateurEntity>)

    @Query("SELECT * FROM collaborateurs ORDER BY nom ASC")
    fun getAllLive(): LiveData<List<CollaborateurEntity>>

    @Query("SELECT * FROM collaborateurs ORDER BY nom ASC")
    suspend fun getAll(): List<CollaborateurEntity>

    @Query("SELECT * FROM collaborateurs WHERE matricule = :matricule LIMIT 1")
    suspend fun getByMatricule(matricule: String): CollaborateurEntity?

    @Query("SELECT matricule FROM collaborateurs")
    suspend fun getAllMatricules(): List<String>

    @Query("SELECT COUNT(*) FROM collaborateurs")
    suspend fun count(): Int

    @Query("SELECT * FROM collaborateurs WHERE syncedToFirebase = 0")
    suspend fun getUnsynced(): List<CollaborateurEntity>

    @Query("UPDATE collaborateurs SET syncedToFirebase = 1 WHERE matricule IN (:matricules)")
    suspend fun markSynced(matricules: List<String>)

    @Query("DELETE FROM collaborateurs")
    suspend fun deleteAll()
}

@Dao
interface FormationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(formations: List<FormationEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(formation: FormationEntity): Long

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