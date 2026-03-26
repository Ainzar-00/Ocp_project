package com.ocp.evalformation.data.local.entity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0083\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0014J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u00c6\u0003J\t\u0010)\u001a\u00020\u0005H\u00c6\u0003J\t\u0010*\u001a\u00020\u0005H\u00c6\u0003J\t\u0010+\u001a\u00020\u0012H\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0005H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0005H\u00c6\u0003J\t\u00100\u001a\u00020\u0005H\u00c6\u0003J\t\u00101\u001a\u00020\u0005H\u00c6\u0003J\t\u00102\u001a\u00020\u0005H\u00c6\u0003J\t\u00103\u001a\u00020\u0005H\u00c6\u0003J\t\u00104\u001a\u00020\u0005H\u00c6\u0003J\u009b\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0003H\u00c6\u0001J\t\u00106\u001a\u000207H\u00d6\u0001J\u0013\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010;H\u00d6\u0003J\t\u0010<\u001a\u000207H\u00d6\u0001J\t\u0010=\u001a\u00020\u0005H\u00d6\u0001J\u0019\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u000207H\u00d6\u0001R\u0011\u0010\u0013\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0011\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010&\u00a8\u0006C"}, d2 = {"Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "Landroid/os/Parcelable;", "id", "", "firebaseId", "", "formationId", "datesFormation", "formateur", "matriculeCollaborateur", "nomCompletCollaborateur", "service", "themeNom", "themeObjectives", "", "emailFlm", "nomFlm", "statut", "Lcom/ocp/evalformation/data/local/entity/InvitationStatus;", "dateEnvoi", "(JLjava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lcom/ocp/evalformation/data/local/entity/InvitationStatus;J)V", "getDateEnvoi", "()J", "getDatesFormation", "()Ljava/lang/String;", "getEmailFlm", "getFirebaseId", "getFormateur", "getFormationId", "getId", "getMatriculeCollaborateur", "getNomCompletCollaborateur", "getNomFlm", "getService", "getStatut", "()Lcom/ocp/evalformation/data/local/entity/InvitationStatus;", "getThemeNom", "getThemeObjectives", "()Ljava/util/List;", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_debug"})
@kotlinx.parcelize.Parcelize()
@androidx.room.Entity(tableName = "invitations_flm")
public final class InvitationFlmEntity implements android.os.Parcelable {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String firebaseId = null;
    private final long formationId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String datesFormation = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String formateur = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String matriculeCollaborateur = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String nomCompletCollaborateur = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String service = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String themeNom = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> themeObjectives = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String emailFlm = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String nomFlm = null;
    @org.jetbrains.annotations.NotNull()
    private final com.ocp.evalformation.data.local.entity.InvitationStatus statut = null;
    private final long dateEnvoi = 0L;
    
    public InvitationFlmEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String firebaseId, long formationId, @org.jetbrains.annotations.NotNull()
    java.lang.String datesFormation, @org.jetbrains.annotations.NotNull()
    java.lang.String formateur, @org.jetbrains.annotations.NotNull()
    java.lang.String matriculeCollaborateur, @org.jetbrains.annotations.NotNull()
    java.lang.String nomCompletCollaborateur, @org.jetbrains.annotations.NotNull()
    java.lang.String service, @org.jetbrains.annotations.NotNull()
    java.lang.String themeNom, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> themeObjectives, @org.jetbrains.annotations.NotNull()
    java.lang.String emailFlm, @org.jetbrains.annotations.NotNull()
    java.lang.String nomFlm, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.InvitationStatus statut, long dateEnvoi) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFirebaseId() {
        return null;
    }
    
    public final long getFormationId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDatesFormation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormateur() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMatriculeCollaborateur() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNomCompletCollaborateur() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getThemeNom() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getThemeObjectives() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEmailFlm() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNomFlm() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.entity.InvitationStatus getStatut() {
        return null;
    }
    
    public final long getDateEnvoi() {
        return 0L;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.entity.InvitationStatus component13() {
        return null;
    }
    
    public final long component14() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.entity.InvitationFlmEntity copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String firebaseId, long formationId, @org.jetbrains.annotations.NotNull()
    java.lang.String datesFormation, @org.jetbrains.annotations.NotNull()
    java.lang.String formateur, @org.jetbrains.annotations.NotNull()
    java.lang.String matriculeCollaborateur, @org.jetbrains.annotations.NotNull()
    java.lang.String nomCompletCollaborateur, @org.jetbrains.annotations.NotNull()
    java.lang.String service, @org.jetbrains.annotations.NotNull()
    java.lang.String themeNom, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> themeObjectives, @org.jetbrains.annotations.NotNull()
    java.lang.String emailFlm, @org.jetbrains.annotations.NotNull()
    java.lang.String nomFlm, @org.jetbrains.annotations.NotNull()
    com.ocp.evalformation.data.local.entity.InvitationStatus statut, long dateEnvoi) {
        return null;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
}