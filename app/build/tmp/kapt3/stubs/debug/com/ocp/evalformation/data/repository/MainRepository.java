package com.ocp.evalformation.data.repository;

import android.content.Context;
import android.util.Log;
import com.ocp.evalformation.com.ocp.evalformation.data.GoogleScriptApiService.RetrofitInstance;
import com.ocp.evalformation.data.local.OcpDatabase;
import com.ocp.evalformation.data.local.entity.*;
import com.ocp.evalformation.data.remote.FirebaseRepository;
import com.ocp.evalformation.data.local.dao.FormDao;
import com.ocp.evalformation.utils.EmailHelper;
import com.ocp.evalformation.utils.dateHelper;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.Dispatchers;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00aa\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ$\u0010\'\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010*\u001a\u00020+H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b,\u0010-J$\u0010.\u001a\b\u0012\u0004\u0012\u00020)0(2\u0006\u0010/\u001a\u000200H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b1\u00102J$\u00103\u001a\b\u0012\u0004\u0012\u0002040(2\u0006\u00105\u001a\u000206H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b7\u00108J,\u00109\u001a\b\u0012\u0004\u0012\u00020:0(2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b>\u0010?J0\u0010@\u001a\u00020<2\u0006\u0010A\u001a\u00020B2\u0006\u00105\u001a\u0002062\u0006\u0010C\u001a\u00020+2\b\u0010D\u001a\u0004\u0018\u00010:2\u0006\u0010E\u001a\u00020<J\u0016\u0010F\u001a\u00020)2\u0006\u0010D\u001a\u00020:H\u0086@\u00a2\u0006\u0002\u0010GJ\u000e\u0010H\u001a\u00020)H\u0086@\u00a2\u0006\u0002\u0010IJ*\u0010J\u001a\b\u0012\u0004\u0012\u00020K0(2\f\u0010L\u001a\b\u0012\u0004\u0012\u0002060MH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bN\u0010OJF\u0010P\u001a\b\u0012\u0004\u0012\u00020Q0(2\u0006\u0010C\u001a\u00020+2\u0006\u00105\u001a\u0002062\b\u0010D\u001a\u0004\u0018\u00010:2\u0006\u0010R\u001a\u00020<2\u0006\u0010S\u001a\u00020<H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bT\u0010UJ\u000e\u0010V\u001a\u00020)H\u0086@\u00a2\u0006\u0002\u0010IR\u0011\u0010\t\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00148F\u00a2\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00188F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u001c8F\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020 8F\u00a2\u0006\u0006\u001a\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020$8F\u00a2\u0006\u0006\u001a\u0004\b%\u0010&\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006W"}, d2 = {"Lcom/ocp/evalformation/data/repository/MainRepository;", "", "context", "Landroid/content/Context;", "db", "Lcom/ocp/evalformation/data/local/OcpDatabase;", "firebase", "Lcom/ocp/evalformation/data/remote/FirebaseRepository;", "(Landroid/content/Context;Lcom/ocp/evalformation/data/local/OcpDatabase;Lcom/ocp/evalformation/data/remote/FirebaseRepository;)V", "collaborateurDao", "Lcom/ocp/evalformation/data/local/dao/CollaborateurDao;", "getCollaborateurDao", "()Lcom/ocp/evalformation/data/local/dao/CollaborateurDao;", "evaluationDao", "Lcom/ocp/evalformation/data/local/dao/EvaluationDao;", "getEvaluationDao", "()Lcom/ocp/evalformation/data/local/dao/EvaluationDao;", "getFirebase", "()Lcom/ocp/evalformation/data/remote/FirebaseRepository;", "flmDao", "Lcom/ocp/evalformation/data/local/dao/FlmDao;", "getFlmDao", "()Lcom/ocp/evalformation/data/local/dao/FlmDao;", "formDao", "Lcom/ocp/evalformation/data/local/dao/FormDao;", "getFormDao", "()Lcom/ocp/evalformation/data/local/dao/FormDao;", "formationDao", "Lcom/ocp/evalformation/data/local/dao/FormationDao;", "getFormationDao", "()Lcom/ocp/evalformation/data/local/dao/FormationDao;", "invitationDao", "Lcom/ocp/evalformation/data/local/dao/InvitationFlmDao;", "getInvitationDao", "()Lcom/ocp/evalformation/data/local/dao/InvitationFlmDao;", "themeDao", "Lcom/ocp/evalformation/data/local/dao/ThemeDao;", "getThemeDao", "()Lcom/ocp/evalformation/data/local/dao/ThemeDao;", "addCollaborateur", "Lkotlin/Result;", "", "collab", "Lcom/ocp/evalformation/data/local/entity/CollaborateurEntity;", "addCollaborateur-gIAlu-s", "(Lcom/ocp/evalformation/data/local/entity/CollaborateurEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addFlm", "flm", "Lcom/ocp/evalformation/data/local/entity/FlmEntity;", "addFlm-gIAlu-s", "(Lcom/ocp/evalformation/data/local/entity/FlmEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addFormation", "", "formation", "Lcom/ocp/evalformation/data/local/entity/FormationEntity;", "addFormation-gIAlu-s", "(Lcom/ocp/evalformation/data/local/entity/FormationEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addTheme", "Lcom/ocp/evalformation/data/local/entity/ThemeEntity;", "nom", "", "objectifPedagogique", "addTheme-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildPreFilledUrl", "form", "Lcom/ocp/evalformation/data/local/entity/Forms;", "collaborateur", "theme", "firebaseId", "createFormForTheme", "(Lcom/ocp/evalformation/data/local/entity/ThemeEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendAllPendingInvitations", "", "formations", "", "sendAllPendingInvitations-gIAlu-s", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendEvaluationFormToFlm", "Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "flmEmail", "flmNom", "sendEvaluationFormToFlm-hUnOzRk", "(Lcom/ocp/evalformation/data/local/entity/CollaborateurEntity;Lcom/ocp/evalformation/data/local/entity/FormationEntity;Lcom/ocp/evalformation/data/local/entity/ThemeEntity;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncPendingToFirebase", "app_debug"})
public final class MainRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.local.OcpDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.remote.FirebaseRepository firebase = null;
    
    @javax.inject.Inject()
    public MainRepository(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.OcpDatabase db, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.remote.FirebaseRepository firebase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.remote.FirebaseRepository getFirebase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.dao.ThemeDao getThemeDao() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.dao.FlmDao getFlmDao() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.dao.CollaborateurDao getCollaborateurDao() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.dao.FormationDao getFormationDao() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.dao.EvaluationDao getEvaluationDao() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.dao.InvitationFlmDao getInvitationDao() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.dao.FormDao getFormDao() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object createFormForTheme(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.ThemeEntity theme, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String buildPreFilledUrl(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.Forms form, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.FormationEntity formation, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.CollaborateurEntity collaborateur, @org.jetbrains.annotations.Nullable()
    com.ocp.evalformation.data.local.entity.ThemeEntity theme, @org.jetbrains.annotations.NotNull()
    java.lang.String firebaseId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncPendingToFirebase(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteAllData(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}