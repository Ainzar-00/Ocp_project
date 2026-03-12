package com.ocp.evalformation.data.local.entity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b2\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u00c5\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\n\u0012\b\b\u0002\u0010\u000e\u001a\u00020\n\u0012\b\b\u0002\u0010\u000f\u001a\u00020\n\u0012\b\b\u0002\u0010\u0010\u001a\u00020\n\u0012\b\b\u0002\u0010\u0011\u001a\u00020\n\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u001aJ\t\u00103\u001a\u00020\u0003H\u00c6\u0003J\t\u00104\u001a\u00020\nH\u00c6\u0003J\t\u00105\u001a\u00020\nH\u00c6\u0003J\t\u00106\u001a\u00020\nH\u00c6\u0003J\t\u00107\u001a\u00020\nH\u00c6\u0003J\t\u00108\u001a\u00020\u0006H\u00c6\u0003J\t\u00109\u001a\u00020\u0006H\u00c6\u0003J\t\u0010:\u001a\u00020\u0006H\u00c6\u0003J\t\u0010;\u001a\u00020\u0006H\u00c6\u0003J\t\u0010<\u001a\u00020\u0006H\u00c6\u0003J\t\u0010=\u001a\u00020\u0018H\u00c6\u0003J\t\u0010>\u001a\u00020\u0003H\u00c6\u0003J\t\u0010?\u001a\u00020\u0003H\u00c6\u0003J\t\u0010@\u001a\u00020\u0006H\u00c6\u0003J\t\u0010A\u001a\u00020\u0006H\u00c6\u0003J\t\u0010B\u001a\u00020\u0006H\u00c6\u0003J\t\u0010C\u001a\u00020\nH\u00c6\u0003J\t\u0010D\u001a\u00020\nH\u00c6\u0003J\t\u0010E\u001a\u00020\nH\u00c6\u0003J\t\u0010F\u001a\u00020\nH\u00c6\u0003J\u00d1\u0001\u0010G\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\n2\b\b\u0002\u0010\u000f\u001a\u00020\n2\b\b\u0002\u0010\u0010\u001a\u00020\n2\b\b\u0002\u0010\u0011\u001a\u00020\n2\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\u00062\b\b\u0002\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u0003H\u00c6\u0001J\t\u0010H\u001a\u00020\nH\u00d6\u0001J\u0013\u0010I\u001a\u00020\u00182\b\u0010J\u001a\u0004\u0018\u00010KH\u00d6\u0003J\t\u0010L\u001a\u00020\nH\u00d6\u0001J\u0006\u0010M\u001a\u00020NJ\u0006\u0010O\u001a\u00020NJ\t\u0010P\u001a\u00020\u0006H\u00d6\u0001J\u0019\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020\nH\u00d6\u0001R\u0011\u0010\f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0011\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\u0010\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0011\u0010\u0013\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0014\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0011\u0010\u0019\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010 R\u0011\u0010\u000e\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010 R\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010 R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010#R\u0011\u0010\u0016\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010 R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010#R\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001cR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001cR\u0011\u0010\u0012\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010 R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001cR\u0011\u0010\u000f\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001cR\u0011\u0010\u0015\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010 R\u0011\u0010\u0017\u001a\u00020\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u00102\u00a8\u0006V"}, d2 = {"Lcom/ocp/evalformation/data/local/entity/EvaluationEntity;", "Landroid/os/Parcelable;", "id", "", "formationId", "dateEvaluation", "", "flmMatricule", "flmNom", "organisationContenu", "", "qualitePedagogique", "adaptationPublic", "maitriseSujet", "disponibiliteFormateur", "qualiteSupports", "atteinteObjectifs", "applicationTerrain", "propositionsAmelioration", "commentaireGeneral", "competencesAcquises", "statut", "googleFormResponseId", "syncedToFirebase", "", "createdAt", "(JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;IIIIIIIILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZJ)V", "getAdaptationPublic", "()I", "getApplicationTerrain", "getAtteinteObjectifs", "getCommentaireGeneral", "()Ljava/lang/String;", "getCompetencesAcquises", "getCreatedAt", "()J", "getDateEvaluation", "getDisponibiliteFormateur", "getFlmMatricule", "getFlmNom", "getFormationId", "getGoogleFormResponseId", "getId", "getMaitriseSujet", "getOrganisationContenu", "getPropositionsAmelioration", "getQualitePedagogique", "getQualiteSupports", "getStatut", "getSyncedToFirebase", "()Z", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "other", "", "hashCode", "moyenneSatisfaction", "", "tauxSatisfactionPct", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_debug"})
@kotlinx.parcelize.Parcelize()
@androidx.room.Entity(tableName = "evaluations", foreignKeys = {@androidx.room.ForeignKey(entity = com.ocp.evalformation.data.local.entity.FormationEntity.class, parentColumns = {"id"}, childColumns = {"formationId"}, onDelete = 5)}, indices = {@androidx.room.Index(value = {"formationId"})})
public final class EvaluationEntity implements android.os.Parcelable {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    private final long formationId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String dateEvaluation = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String flmMatricule = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String flmNom = null;
    private final int organisationContenu = 0;
    private final int qualitePedagogique = 0;
    private final int adaptationPublic = 0;
    private final int maitriseSujet = 0;
    private final int disponibiliteFormateur = 0;
    private final int qualiteSupports = 0;
    private final int atteinteObjectifs = 0;
    private final int applicationTerrain = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String propositionsAmelioration = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String commentaireGeneral = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String competencesAcquises = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String statut = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String googleFormResponseId = null;
    private final boolean syncedToFirebase = false;
    private final long createdAt = 0L;
    
    public EvaluationEntity(long id, long formationId, @org.jetbrains.annotations.NotNull()
    java.lang.String dateEvaluation, @org.jetbrains.annotations.NotNull()
    java.lang.String flmMatricule, @org.jetbrains.annotations.NotNull()
    java.lang.String flmNom, int organisationContenu, int qualitePedagogique, int adaptationPublic, int maitriseSujet, int disponibiliteFormateur, int qualiteSupports, int atteinteObjectifs, int applicationTerrain, @org.jetbrains.annotations.NotNull()
    java.lang.String propositionsAmelioration, @org.jetbrains.annotations.NotNull()
    java.lang.String commentaireGeneral, @org.jetbrains.annotations.NotNull()
    java.lang.String competencesAcquises, @org.jetbrains.annotations.NotNull()
    java.lang.String statut, @org.jetbrains.annotations.NotNull()
    java.lang.String googleFormResponseId, boolean syncedToFirebase, long createdAt) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final long getFormationId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDateEvaluation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFlmMatricule() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFlmNom() {
        return null;
    }
    
    public final int getOrganisationContenu() {
        return 0;
    }
    
    public final int getQualitePedagogique() {
        return 0;
    }
    
    public final int getAdaptationPublic() {
        return 0;
    }
    
    public final int getMaitriseSujet() {
        return 0;
    }
    
    public final int getDisponibiliteFormateur() {
        return 0;
    }
    
    public final int getQualiteSupports() {
        return 0;
    }
    
    public final int getAtteinteObjectifs() {
        return 0;
    }
    
    public final int getApplicationTerrain() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPropositionsAmelioration() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCommentaireGeneral() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCompetencesAcquises() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getStatut() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getGoogleFormResponseId() {
        return null;
    }
    
    public final boolean getSyncedToFirebase() {
        return false;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final double moyenneSatisfaction() {
        return 0.0;
    }
    
    public final double tauxSatisfactionPct() {
        return 0.0;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final int component10() {
        return 0;
    }
    
    public final int component11() {
        return 0;
    }
    
    public final int component12() {
        return 0;
    }
    
    public final int component13() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component15() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component18() {
        return null;
    }
    
    public final boolean component19() {
        return false;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final long component20() {
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
    
    public final int component6() {
        return 0;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final int component9() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.entity.EvaluationEntity copy(long id, long formationId, @org.jetbrains.annotations.NotNull()
    java.lang.String dateEvaluation, @org.jetbrains.annotations.NotNull()
    java.lang.String flmMatricule, @org.jetbrains.annotations.NotNull()
    java.lang.String flmNom, int organisationContenu, int qualitePedagogique, int adaptationPublic, int maitriseSujet, int disponibiliteFormateur, int qualiteSupports, int atteinteObjectifs, int applicationTerrain, @org.jetbrains.annotations.NotNull()
    java.lang.String propositionsAmelioration, @org.jetbrains.annotations.NotNull()
    java.lang.String commentaireGeneral, @org.jetbrains.annotations.NotNull()
    java.lang.String competencesAcquises, @org.jetbrains.annotations.NotNull()
    java.lang.String statut, @org.jetbrains.annotations.NotNull()
    java.lang.String googleFormResponseId, boolean syncedToFirebase, long createdAt) {
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