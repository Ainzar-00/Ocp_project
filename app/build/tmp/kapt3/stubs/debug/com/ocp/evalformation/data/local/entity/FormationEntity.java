package com.ocp.evalformation.data.local.entity;

import android.annotation.SuppressLint;
import android.os.Parcel;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;
import androidx.room.Index;
import android.os.Parcelable;
import androidx.room.Embedded;
import kotlinx.parcelize.Parcelize;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b1\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0091\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0005\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0013\u001a\u00020\u0005\u0012\u0006\u0010\u0014\u001a\u00020\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0016J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\u0005H\u00c6\u0003J\t\u0010-\u001a\u00020\u0005H\u00c6\u0003J\t\u0010.\u001a\u00020\u0005H\u00c6\u0003J\t\u0010/\u001a\u00020\u0005H\u00c6\u0003J\t\u00100\u001a\u00020\u0005H\u00c6\u0003J\t\u00101\u001a\u00020\u0005H\u00c6\u0003J\t\u00102\u001a\u00020\u0005H\u00c6\u0003J\t\u00103\u001a\u00020\u0005H\u00c6\u0003J\t\u00104\u001a\u00020\u0005H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\u0005H\u00c6\u0003J\t\u00107\u001a\u00020\u0005H\u00c6\u0003J\t\u00108\u001a\u00020\u0005H\u00c6\u0003J\t\u00109\u001a\u00020\u0005H\u00c6\u0003J\t\u0010:\u001a\u00020\fH\u00c6\u0003J\t\u0010;\u001a\u00020\u0005H\u00c6\u0003J\u00b3\u0001\u0010<\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u00052\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u0005H\u00c6\u0001J\t\u0010=\u001a\u00020>H\u00d6\u0001J\u0013\u0010?\u001a\u00020\f2\b\u0010@\u001a\u0004\u0018\u00010AH\u00d6\u0003J\t\u0010B\u001a\u00020>H\u00d6\u0001J\t\u0010C\u001a\u00020\u0005H\u00d6\u0001J\u0019\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020>H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000e\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0011\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0011\u0010\u0015\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0011\u0010\r\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0013\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0011\u0010\u0011\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0011\u0010\u0012\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0018R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010#R\u0011\u0010\u0014\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0018\u00a8\u0006I"}, d2 = {"Lcom/ocp/evalformation/data/local/entity/FormationEntity;", "Landroid/os/Parcelable;", "id", "", "collaborateurMatricule", "", "themeId", "debut", "fin", "Formateur", "dateAppreciation", "syncedToFirebase", "", "entite", "categorie", "division", "convocation", "presence", "session", "jsp", "type", "domaine", "(JLjava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getFormateur", "()Ljava/lang/String;", "getCategorie", "getCollaborateurMatricule", "getConvocation", "getDateAppreciation", "getDebut", "getDivision", "getDomaine", "getEntite", "getFin", "getId", "()J", "getJsp", "getPresence", "getSession", "getSyncedToFirebase", "()Z", "getThemeId", "getType", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_debug"})
@kotlinx.parcelize.Parcelize()
@androidx.room.Entity(tableName = "formations", foreignKeys = {@androidx.room.ForeignKey(entity = com.ocp.evalformation.data.local.entity.CollaborateurEntity.class, parentColumns = {"matricule"}, childColumns = {"collaborateurMatricule"}, onDelete = 5), @androidx.room.ForeignKey(entity = com.ocp.evalformation.data.local.entity.ThemeEntity.class, parentColumns = {"id"}, childColumns = {"themeId"}, onDelete = 5)}, indices = {@androidx.room.Index(value = {"collaborateurMatricule"}), @androidx.room.Index(value = {"themeId"})})
public final class FormationEntity implements android.os.Parcelable {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String collaborateurMatricule = null;
    private final long themeId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String debut = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String fin = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String Formateur = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String dateAppreciation = null;
    private final boolean syncedToFirebase = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String entite = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String categorie = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String division = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String convocation = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String presence = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String session = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String jsp = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String type = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String domaine = null;
    
    public FormationEntity(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String collaborateurMatricule, long themeId, @org.jetbrains.annotations.NotNull()
    java.lang.String debut, @org.jetbrains.annotations.NotNull()
    java.lang.String fin, @org.jetbrains.annotations.NotNull()
    java.lang.String Formateur, @org.jetbrains.annotations.NotNull()
    java.lang.String dateAppreciation, boolean syncedToFirebase, @org.jetbrains.annotations.NotNull()
    java.lang.String entite, @org.jetbrains.annotations.NotNull()
    java.lang.String categorie, @org.jetbrains.annotations.NotNull()
    java.lang.String division, @org.jetbrains.annotations.NotNull()
    java.lang.String convocation, @org.jetbrains.annotations.NotNull()
    java.lang.String presence, @org.jetbrains.annotations.NotNull()
    java.lang.String session, @org.jetbrains.annotations.NotNull()
    java.lang.String jsp, @org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    java.lang.String domaine) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCollaborateurMatricule() {
        return null;
    }
    
    public final long getThemeId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDebut() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFin() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFormateur() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDateAppreciation() {
        return null;
    }
    
    public final boolean getSyncedToFirebase() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getEntite() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCategorie() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDivision() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getConvocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPresence() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSession() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJsp() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDomaine() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
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
    public final java.lang.String component13() {
        return null;
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
    
    public final boolean component8() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.local.entity.FormationEntity copy(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String collaborateurMatricule, long themeId, @org.jetbrains.annotations.NotNull()
    java.lang.String debut, @org.jetbrains.annotations.NotNull()
    java.lang.String fin, @org.jetbrains.annotations.NotNull()
    java.lang.String Formateur, @org.jetbrains.annotations.NotNull()
    java.lang.String dateAppreciation, boolean syncedToFirebase, @org.jetbrains.annotations.NotNull()
    java.lang.String entite, @org.jetbrains.annotations.NotNull()
    java.lang.String categorie, @org.jetbrains.annotations.NotNull()
    java.lang.String division, @org.jetbrains.annotations.NotNull()
    java.lang.String convocation, @org.jetbrains.annotations.NotNull()
    java.lang.String presence, @org.jetbrains.annotations.NotNull()
    java.lang.String session, @org.jetbrains.annotations.NotNull()
    java.lang.String jsp, @org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    java.lang.String domaine) {
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