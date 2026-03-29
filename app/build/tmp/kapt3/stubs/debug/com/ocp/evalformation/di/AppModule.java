package com.ocp.evalformation.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J8\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\b\u0010\u0018\u001a\u00020\u0019H\u0007J(\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0007J\b\u0010 \u001a\u00020\u000fH\u0007J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\"\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010#\u001a\u00020$2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010%\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\"\u0010&\u001a\u00020\'2\b\b\u0001\u0010(\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u001bH\u0007J\u0010\u0010*\u001a\u00020\u001f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006+"}, d2 = {"Lcom/ocp/evalformation/di/AppModule;", "", "()V", "provideCollaborateurDao", "Lcom/ocp/evalformation/data/local/dao/CollaborateurDao;", "db", "Lcom/ocp/evalformation/data/local/OcpDatabase;", "provideDatabase", "ctx", "Landroid/content/Context;", "provideEvaluationDao", "Lcom/ocp/evalformation/data/local/dao/EvaluationDao;", "provideEvaluationRepository", "Lcom/ocp/evalformation/data/repository/EvaluationRepository;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "evaluationDao", "invitationDao", "Lcom/ocp/evalformation/data/local/dao/InvitationFlmDao;", "formationDao", "Lcom/ocp/evalformation/data/local/dao/FormationDao;", "collaborateurDao", "flmDao", "Lcom/ocp/evalformation/data/local/dao/FlmDao;", "provideFirebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "provideFirebaseRepo", "Lcom/ocp/evalformation/data/remote/FirebaseRepository;", "auth", "fs", "themeDao", "Lcom/ocp/evalformation/data/local/dao/ThemeDao;", "provideFirestore", "provideFlmDao", "provideFormationDao", "provideForms", "Lcom/ocp/evalformation/data/local/dao/FormDao;", "provideInvitationFlmDao", "provideMainRepo", "Lcom/ocp/evalformation/data/repository/MainRepository;", "context", "firebase", "provideThemeDao", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class AppModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.ocp.evalformation.di.AppModule INSTANCE = null;
    
    private AppModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.auth.FirebaseAuth provideFirebaseAuth() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.google.firebase.firestore.FirebaseFirestore provideFirestore() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.OcpDatabase provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context ctx) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.remote.FirebaseRepository provideFirebaseRepo(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth auth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore fs, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.dao.ThemeDao themeDao, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.dao.FormationDao formationDao) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.repository.MainRepository provideMainRepo(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.OcpDatabase db, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.remote.FirebaseRepository firebase) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.repository.EvaluationRepository provideEvaluationRepository(@org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.dao.EvaluationDao evaluationDao, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.dao.InvitationFlmDao invitationDao, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.dao.FormationDao formationDao, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.dao.CollaborateurDao collaborateurDao, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.dao.FlmDao flmDao) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.dao.ThemeDao provideThemeDao(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.OcpDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.dao.FlmDao provideFlmDao(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.OcpDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.dao.CollaborateurDao provideCollaborateurDao(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.OcpDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.dao.FormationDao provideFormationDao(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.OcpDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.dao.InvitationFlmDao provideInvitationFlmDao(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.OcpDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.dao.FormDao provideForms(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.OcpDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.dao.EvaluationDao provideEvaluationDao(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.OcpDatabase db) {
        return null;
    }
}