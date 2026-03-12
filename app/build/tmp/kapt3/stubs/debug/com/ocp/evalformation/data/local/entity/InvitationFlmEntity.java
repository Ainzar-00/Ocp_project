package com.ocp.evalformation.data.local.entity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b)\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bs\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\f\u001a\u00020\u0006\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0010J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0014J\u0010\u0010$\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0014J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0006H\u00c6\u0003J\t\u0010(\u001a\u00020\u0006H\u00c6\u0003J\t\u0010)\u001a\u00020\u0006H\u00c6\u0003J\t\u0010*\u001a\u00020\u0006H\u00c6\u0003J\t\u0010+\u001a\u00020\u0006H\u00c6\u0003J\t\u0010,\u001a\u00020\u0006H\u00c6\u0003J\u008a\u0001\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010.J\t\u0010/\u001a\u000200H\u00d6\u0001J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u000104H\u00d6\u0003J\t\u00105\u001a\u000200H\u00d6\u0001J\t\u00106\u001a\u00020\u0006H\u00d6\u0001J\u0019\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u000200H\u00d6\u0001R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\n\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0011\u0010\f\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017\u00a8\u0006<"}, d2 = {"Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "Landroid/os/Parcelable;", "id", "", "formationId", "matriculeCollaborateur", "", "nomCompletCollaborateur", "themeNom", "emailFlm", "nomFlm", "googleFormUrl", "statut", "dateEnvoi", "dateReponse", "evaluationId", "(JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Long;Ljava/lang/Long;)V", "getDateEnvoi", "()J", "getDateReponse", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getEmailFlm", "()Ljava/lang/String;", "getEvaluationId", "getFormationId", "getGoogleFormUrl", "getId", "getMatriculeCollaborateur", "getNomCompletCollaborateur", "getNomFlm", "getStatut", "getThemeNom", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/Long;Ljava/lang/Long;)Lcom/ocp/evalformation/data/local/entity/InvitationFlmEntity;", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_debug"})
@kotlinx.parcelize.Parcelize()
@androidx.room.Entity(tableName = "invitations_flm")
public final class InvitationFlmEntity implements android.os.Parcelable {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    private final long formationId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String matriculeCollaborateur = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String nomCompletCollaborateur = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String themeNom = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String emailFlm = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String nomFlm = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String googleFormUrl = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String statut = null;
    private final long dateEnvoi = 0L;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long dateReponse = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long evaluationId = null;
    
    public InvitationFlmEntity(long id, long formationId, @org.jetbrains.annotations.NotNull()
    java.lang.String matriculeCollaborateur, @org.jetbrains.annotations.NotNull()
    java.lang.String nomCompletCollaborateur, @org.jetbrains.annotations.NotNull()
    java.lang.String themeNom, @org.jetbrains.annotations.NotNull()
    java.lang.String emailFlm, @org.jetbrains.annotations.NotNull()
    java.lang.String nomFlm, @org.jetbrains.annotations.NotNull()
    java.lang.String googleFormUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String statut, long dateEnvoi, @org.jetbrains.annotations.Nullable()
    java.lang.Long dateReponse, @org.jetbrains.annotations.Nullable()
    java.lang.Long evaluationId) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final long getFormationId() {
        return 0L;
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
    public final java.lang.String getThemeNom() {
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
    public final java.lang.String getGoogleFormUrl() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatut() {
        return null;
    }
    
    public final long getDateEnvoi() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getDateReponse() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getEvaluationId() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long component10() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component12() {
        return null;
    }
    
    public final long component2() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
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
    public final com.ocp.evalformation.data.local.entity.InvitationFlmEntity copy(long id, long formationId, @org.jetbrains.annotations.NotNull()
    java.lang.String matriculeCollaborateur, @org.jetbrains.annotations.NotNull()
    java.lang.String nomCompletCollaborateur, @org.jetbrains.annotations.NotNull()
    java.lang.String themeNom, @org.jetbrains.annotations.NotNull()
    java.lang.String emailFlm, @org.jetbrains.annotations.NotNull()
    java.lang.String nomFlm, @org.jetbrains.annotations.NotNull()
    java.lang.String googleFormUrl, @org.jetbrains.annotations.NotNull()
    java.lang.String statut, long dateEnvoi, @org.jetbrains.annotations.Nullable()
    java.lang.Long dateReponse, @org.jetbrains.annotations.Nullable()
    java.lang.Long evaluationId) {
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