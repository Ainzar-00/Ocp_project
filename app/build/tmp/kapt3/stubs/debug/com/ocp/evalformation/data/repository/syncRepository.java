package com.ocp.evalformation.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u000e\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u000fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/ocp/evalformation/data/repository/syncRepository;", "", "firebase", "Lcom/ocp/evalformation/data/remote/FirebaseRepository;", "themeDao", "Lcom/ocp/evalformation/data/local/dao/ThemeDao;", "flmDao", "Lcom/ocp/evalformation/data/local/dao/FlmDao;", "collaborateurDao", "Lcom/ocp/evalformation/data/local/dao/CollaborateurDao;", "formationDao", "Lcom/ocp/evalformation/data/local/dao/FormationDao;", "(Lcom/ocp/evalformation/data/remote/FirebaseRepository;Lcom/ocp/evalformation/data/local/dao/ThemeDao;Lcom/ocp/evalformation/data/local/dao/FlmDao;Lcom/ocp/evalformation/data/local/dao/CollaborateurDao;Lcom/ocp/evalformation/data/local/dao/FormationDao;)V", "getFormations", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logLong", "tag", "", "msg", "syncIfEmpty", "", "app_debug"})
public final class syncRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.remote.FirebaseRepository firebase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.local.dao.ThemeDao themeDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.local.dao.FlmDao flmDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.local.dao.CollaborateurDao collaborateurDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.local.dao.FormationDao formationDao = null;
    
    @javax.inject.Inject()
    public syncRepository(@org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.remote.FirebaseRepository firebase, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.dao.ThemeDao themeDao, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.dao.FlmDao flmDao, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.dao.CollaborateurDao collaborateurDao, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.dao.FormationDao formationDao) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncIfEmpty(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getFormations(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Logs long messages in chunks to avoid Logcat truncation.
     */
    public final void logLong(@org.jetbrains.annotations.NotNull()
    java.lang.String tag, @org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
    }
}