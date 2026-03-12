package com.ocp.evalformation.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J$\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010$\u001a\u00020%H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b&\u0010\'J$\u0010(\u001a\b\u0012\u0004\u0012\u00020#0\"2\u0006\u0010)\u001a\u00020*H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b+\u0010,J$\u0010-\u001a\b\u0012\u0004\u0012\u00020.0\"2\u0006\u0010/\u001a\u000200H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b1\u00102J,\u00103\u001a\b\u0012\u0004\u0012\u0002040\"2\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u000206H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b8\u00109J\u000e\u0010:\u001a\u00020#H\u0086@\u00a2\u0006\u0002\u0010;J$\u0010<\u001a\b\u0012\u0004\u0012\u00020.0\"2\u0006\u0010=\u001a\u00020>H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b?\u0010@JF\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\"2\u0006\u0010C\u001a\u00020%2\u0006\u0010/\u001a\u0002002\b\u0010D\u001a\u0004\u0018\u0001042\u0006\u0010E\u001a\u0002062\u0006\u0010F\u001a\u000206H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bG\u0010HJ\u000e\u0010I\u001a\u00020#H\u0086@\u00a2\u0006\u0002\u0010;R\u0011\u0010\u0007\u001a\u00020\b8F\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u00168F\u00a2\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u001a8F\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e8F\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010 \u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006J"}, d2 = {"Lcom/ocp/evalformation/data/repository/MainRepository;", "", "db", "Lcom/ocp/evalformation/data/local/OcpDatabase;", "firebase", "Lcom/ocp/evalformation/data/remote/FirebaseRepository;", "(Lcom/ocp/evalformation/data/local/OcpDatabase;Lcom/ocp/evalformation/data/remote/FirebaseRepository;)V", "collaborateurDao", "Lcom/ocp/evalformation/data/local/dao/CollaborateurDao;", "getCollaborateurDao", "()Lcom/ocp/evalformation/data/local/dao/CollaborateurDao;", "evaluationDao", "Lcom/ocp/evalformation/data/local/dao/EvaluationDao;", "getEvaluationDao", "()Lcom/ocp/evalformation/data/local/dao/EvaluationDao;", "getFirebase", "()Lcom/ocp/evalformation/data/remote/FirebaseRepository;", "flmDao", "Lcom/ocp/evalformation/data/local/dao/FlmDao;", "getFlmDao", "()Lcom/ocp/evalformation/data/local/dao/FlmDao;", "formationDao", "Lcom/ocp/evalformation/data/local/dao/FormationDao;", "getFormationDao", "()Lcom/ocp/evalformation/data/local/dao/FormationDao;", "invitationDao", "Lcom/ocp/evalformation/data/local/dao/InvitationFlmDao;", "getInvitationDao", "()Lcom/ocp/evalformation/data/local/dao/InvitationFlmDao;", "themeDao", "Lcom/ocp/evalformation/data/local/dao/ThemeDao;", "getThemeDao", "()Lcom/ocp/evalformation/data/local/dao/ThemeDao;", "addCollaborateur", "Lkotlin/Result;", "", "collab", "Lcom/ocp/evalformation/data/local/entity/CollaborateurEntity;", "addCollaborateur-gIAlu-s", "(Lcom/ocp/evalformation/data/local/entity/CollaborateurEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addFlm", "flm", "Lcom/ocp/evalformation/data/local/entity/FlmEntity;", "addFlm-gIAlu-s", "(Lcom/ocp/evalformation/data/local/entity/FlmEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addFormation", "", "formation", "Lcom/ocp/evalformation/data/local/entity/FormationEntity;", "addFormation-gIAlu-s", "(Lcom/ocp/evalformation/data/local/entity/FormationEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addTheme", "Lcom/ocp/evalformation/data/local/entity/ThemeEntity;", "nom", "", "objectifPedagogique", "addTheme-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveEvaluation", "eval", "Lcom/ocp/evalformation/data/local/entity/EvaluationEntity;", "saveEvaluation-gIAlu-s", "(Lcom/ocp/evalformation/data/local/entity/EvaluationEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendEvaluationFormToFlm", "Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "collaborateur", "theme", "flmEmail", "flmNom", "sendEvaluationFormToFlm-hUnOzRk", "(Lcom/ocp/evalformation/data/local/entity/CollaborateurEntity;Lcom/ocp/evalformation/data/local/entity/FormationEntity;Lcom/ocp/evalformation/data/local/entity/ThemeEntity;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncPendingToFirebase", "app_debug"})
public final class MainRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.local.OcpDatabase db = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.remote.FirebaseRepository firebase = null;
    
    @javax.inject.Inject()
    public MainRepository(@org.jetbrains.annotations.NotNull()
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