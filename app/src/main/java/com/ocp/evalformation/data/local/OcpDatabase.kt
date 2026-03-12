package com.ocp.evalformation.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ocp.evalformation.data.local.dao.*
import com.ocp.evalformation.data.local.entity.*

@Database(
    entities = [
        ThemeEntity::class,
        FlmEntity::class,
        CollaborateurEntity::class,
        FormationEntity::class,
        EvaluationEntity::class,
        InvitationFlmEntity::class
    ],
    version = 4,
    exportSchema = false
)
abstract class OcpDatabase : RoomDatabase() {

    abstract fun themeDao(): ThemeDao
    abstract fun flmDao(): FlmDao
    abstract fun collaborateurDao(): CollaborateurDao
    abstract fun formationDao(): FormationDao
    abstract fun evaluationDao(): EvaluationDao
    abstract fun invitationFlmDao(): InvitationFlmDao

    companion object {
        @Volatile private var INSTANCE: OcpDatabase? = null

        fun getInstance(context: Context): OcpDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    OcpDatabase::class.java,
                    "ocp_evaluation.db"
                ).fallbackToDestructiveMigration().build().also { INSTANCE = it }
            }
        }
    }
}
