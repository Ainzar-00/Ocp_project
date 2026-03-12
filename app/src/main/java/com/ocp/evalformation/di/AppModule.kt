package com.ocp.evalformation.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.firestoreSettings
import com.ocp.evalformation.data.local.OcpDatabase
import com.ocp.evalformation.data.local.dao.FormationDao
import com.ocp.evalformation.data.local.dao.ThemeDao
import com.ocp.evalformation.data.remote.FirebaseRepository
import com.ocp.evalformation.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides @Singleton
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance().also { db ->
            db.firestoreSettings = FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
                .build()
        }
    }

    @Provides @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): OcpDatabase =
        OcpDatabase.getInstance(ctx)

    @Provides
    @Singleton
    fun provideFirebaseRepo(
        auth: FirebaseAuth,
        fs: FirebaseFirestore,
        themeDao: ThemeDao,
        formationDao: FormationDao
    ): FirebaseRepository {
        return FirebaseRepository(
            auth,
            fs,
            themeDao,
            formationDao
        )
    }

    @Provides @Singleton
    fun provideMainRepo(
        db: OcpDatabase,
        firebase: FirebaseRepository
    ) = MainRepository(db, firebase)

    // ── DAOs ──────────────────────────────────────

    @Provides
    fun provideThemeDao(db: OcpDatabase) = db.themeDao()

    @Provides
    fun provideFlmDao(db: OcpDatabase) = db.flmDao()

    @Provides
    fun provideCollaborateurDao(db: OcpDatabase) = db.collaborateurDao()

    @Provides
    fun provideFormationDao(db: OcpDatabase) = db.formationDao()
}