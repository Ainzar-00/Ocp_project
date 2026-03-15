package com.ocp.evalformation.data.remote;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J$\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J$\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0006\u0010\u001d\u001a\u00020\u001eJ\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0086@\u00a2\u0006\u0002\u0010\"J\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020$0 H\u0086@\u00a2\u0006\u0002\u0010\"J\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00150 H\u0086@\u00a2\u0006\u0002\u0010\"J\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001a0 H\u0086@\u00a2\u0006\u0002\u0010\"J\u0006\u0010\'\u001a\u00020(J,\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00132\u0006\u0010+\u001a\u00020\u001e2\u0006\u0010,\u001a\u00020\u001eH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b-\u0010.J\u0006\u0010/\u001a\u00020\fJ\u001c\u00100\u001a\b\u0012\u0004\u0012\u00020\f0\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b1\u0010\"J\u001c\u00102\u001a\b\u0012\u0004\u0012\u00020\f0\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b3\u0010\"J\u001c\u00104\u001a\b\u0012\u0004\u0012\u00020\f0\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b5\u0010\"J$\u00106\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00132\u0006\u00107\u001a\u000208H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b9\u0010:J*\u0010;\u001a\b\u0012\u0004\u0012\u00020\f0\u00132\f\u0010<\u001a\b\u0012\u0004\u0012\u00020!0 H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b=\u0010>J*\u0010?\u001a\b\u0012\u0004\u0012\u00020\f0\u00132\f\u0010<\u001a\b\u0012\u0004\u0012\u00020$0 H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b@\u0010>J*\u0010A\u001a\b\u0012\u0004\u0012\u00020\f0\u00132\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00150 H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bB\u0010>J*\u0010C\u001a\b\u0012\u0004\u0012\u00020\f0\u00132\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u001a0 H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bD\u0010>R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006E"}, d2 = {"Lcom/ocp/evalformation/data/remote/FirebaseRepository;", "", "auth", "Lcom/google/firebase/auth/FirebaseAuth;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "themeDao", "Lcom/ocp/evalformation/data/local/dao/ThemeDao;", "formationDao", "Lcom/ocp/evalformation/data/local/dao/FormationDao;", "(Lcom/google/firebase/auth/FirebaseAuth;Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/ocp/evalformation/data/local/dao/ThemeDao;Lcom/ocp/evalformation/data/local/dao/FormationDao;)V", "UploadForm", "", "roomId", "", "form", "Lcom/ocp/evalformation/data/local/entity/Forms;", "(JLcom/ocp/evalformation/data/local/entity/Forms;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addFormationLocalThenUpload", "Lkotlin/Result;", "formation", "Lcom/ocp/evalformation/data/local/entity/FormationEntity;", "addFormationLocalThenUpload-gIAlu-s", "(Lcom/ocp/evalformation/data/local/entity/FormationEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addTheme", "theme", "Lcom/ocp/evalformation/data/local/entity/ThemeEntity;", "addTheme-gIAlu-s", "(Lcom/ocp/evalformation/data/local/entity/ThemeEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "currentUserEmail", "", "fetchCollaborateurs", "", "Lcom/ocp/evalformation/data/local/entity/CollaborateurEntity;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fetchFlms", "Lcom/ocp/evalformation/data/local/entity/FlmEntity;", "fetchFormations", "fetchThemes", "isLoggedIn", "", "login", "Lcom/ocp/evalformation/data/local/entity/UserRole;", "email", "password", "login-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logout", "resetAllAutoincrements", "resetAllAutoincrements-IoAF18A", "resetFormationsAutoincrement", "resetFormationsAutoincrement-IoAF18A", "resetThemesAutoincrement", "resetThemesAutoincrement-IoAF18A", "saveInvitation", "inv", "Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "saveInvitation-gIAlu-s", "(Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadCollaborateurs", "list", "uploadCollaborateurs-gIAlu-s", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadFlms", "uploadFlms-gIAlu-s", "uploadFormations", "uploadFormations-gIAlu-s", "uploadThemes", "uploadThemes-gIAlu-s", "app_debug"})
public final class FirebaseRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.auth.FirebaseAuth auth = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.local.dao.ThemeDao themeDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.local.dao.FormationDao formationDao = null;
    
    @javax.inject.Inject()
    public FirebaseRepository(@org.jetbrains.annotations.NotNull()
    com.google.firebase.auth.FirebaseAuth auth, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.dao.ThemeDao themeDao, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.dao.FormationDao formationDao) {
        super();
    }
    
    public final void logout() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String currentUserEmail() {
        return null;
    }
    
    public final boolean isLoggedIn() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchThemes(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ocp.evalformation.data.local.entity.ThemeEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchFlms(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ocp.evalformation.data.local.entity.FlmEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchCollaborateurs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ocp.evalformation.data.local.entity.CollaborateurEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object fetchFormations(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.ocp.evalformation.data.local.entity.FormationEntity>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object UploadForm(long roomId, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.Forms form, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}