package com.ocp.evalformation.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.ocp.evalformation.data.local.dao.*
import com.ocp.evalformation.data.local.entity.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter fun fromStringList(value: List<String>?): String =
        gson.toJson(value ?: emptyList<String>())

    @TypeConverter fun toStringList(value: String): List<String> =
        gson.fromJson(value, object : TypeToken<List<String>>() {}.type) ?: emptyList()

    @TypeConverter fun fromStatus(value: InvitationStatus): String = value.name
    @TypeConverter fun toStatus(value: String): InvitationStatus =
        try { InvitationStatus.valueOf(value) } catch (e: Exception) { InvitationStatus.NON_EXPEDIEE }
}

@Database(
    entities = [
        ThemeEntity::class,
        FlmEntity::class,
        CollaborateurEntity::class,
        FormationEntity::class,
        InvitationFlmEntity::class,
        Forms::class,
        EvaluationEntity::class
    ],
    version = 18,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class OcpDatabase : RoomDatabase() {

    abstract fun themeDao(): ThemeDao
    abstract fun flmDao(): FlmDao
    abstract fun collaborateurDao(): CollaborateurDao
    abstract fun formationDao(): FormationDao
    abstract fun evaluationDao(): EvaluationDao
    abstract fun invitationFlmDao(): InvitationFlmDao
    abstract fun formDao(): FormDao

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
