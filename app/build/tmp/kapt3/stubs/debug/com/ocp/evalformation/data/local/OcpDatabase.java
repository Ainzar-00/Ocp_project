package com.ocp.evalformation.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&\u00a8\u0006\u0010"}, d2 = {"Lcom/ocp/evalformation/data/local/OcpDatabase;", "Landroidx/room/RoomDatabase;", "()V", "collaborateurDao", "Lcom/ocp/evalformation/data/local/dao/CollaborateurDao;", "evaluationDao", "Lcom/ocp/evalformation/data/local/dao/EvaluationDao;", "flmDao", "Lcom/ocp/evalformation/data/local/dao/FlmDao;", "formationDao", "Lcom/ocp/evalformation/data/local/dao/FormationDao;", "invitationFlmDao", "Lcom/ocp/evalformation/data/local/dao/InvitationFlmDao;", "themeDao", "Lcom/ocp/evalformation/data/local/dao/ThemeDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.ocp.evalformation.data.local.entity.ThemeEntity.class, com.ocp.evalformation.data.local.entity.FlmEntity.class, com.ocp.evalformation.data.local.entity.CollaborateurEntity.class, com.ocp.evalformation.data.local.entity.FormationEntity.class, com.ocp.evalformation.data.local.entity.EvaluationEntity.class, com.ocp.evalformation.data.local.entity.InvitationFlmEntity.class}, version = 4, exportSchema = false)
public abstract class OcpDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.ocp.evalformation.data.local.OcpDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.ocp.evalformation.data.local.OcpDatabase.Companion Companion = null;
    
    public OcpDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.ocp.evalformation.data.local.dao.ThemeDao themeDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.ocp.evalformation.data.local.dao.FlmDao flmDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.ocp.evalformation.data.local.dao.CollaborateurDao collaborateurDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.ocp.evalformation.data.local.dao.FormationDao formationDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.ocp.evalformation.data.local.dao.EvaluationDao evaluationDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.ocp.evalformation.data.local.dao.InvitationFlmDao invitationFlmDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/ocp/evalformation/data/local/OcpDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/ocp/evalformation/data/local/OcpDatabase;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.ocp.evalformation.data.local.OcpDatabase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}