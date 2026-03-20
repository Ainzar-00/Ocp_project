package com.ocp.evalformation.data.local.entity

import android.annotation.SuppressLint
import android.os.Parcel
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index
import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.parcelize.Parcelize

// ─────────────────────────────────────────────
// Theme
// ─────────────────────────────────────────────
@Parcelize
@Entity(tableName = "themes")
data class ThemeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nom: String,
    val objectifPedagogique: String,
    val syncedToFirebase: Boolean = false
) : Parcelable

// ─────────────────────────────────────────────
// FLM
// ─────────────────────────────────────────────
@Parcelize
@Entity(tableName = "flms")
data class FlmEntity(
    @PrimaryKey val matricule: String,
    val nom: String,
    val prenom: String,
    val email: String,
    val service: String,
    val syncedToFirebase: Boolean = false
) : Parcelable

// ─────────────────────────────────────────────
// Collaborateur
// ─────────────────────────────────────────────
@Parcelize
@Entity(
    tableName = "collaborateurs",
    foreignKeys = [ForeignKey(
        entity = FlmEntity::class,
        parentColumns = ["matricule"],
        childColumns = ["flmMatricule"],
        onDelete = ForeignKey.SET_NULL
    )],
    indices = [Index("flmMatricule")]
)
data class CollaborateurEntity(
    @PrimaryKey val matricule: String,
    val nom: String,
    val prenom: String,
    val service: String,
    val flmMatricule: String? = null,
    val syncedToFirebase: Boolean = false
) : Parcelable

// ─────────────────────────────────────────────
// Formation
// ─────────────────────────────────────────────
@Parcelize
@Entity(
    tableName = "formations",
    foreignKeys = [
        ForeignKey(
            entity = CollaborateurEntity::class,
            parentColumns = ["matricule"],
            childColumns = ["collaborateurMatricule"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ThemeEntity::class,
            parentColumns = ["id"],
            childColumns = ["themeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("collaborateurMatricule"), Index("themeId")]
)
data class FormationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val collaborateurMatricule: String,
    val themeId: Long,
    val debut: String,
    val fin: String,
    val Formateur: String,
    val dateAppreciation:String,
    val syncedToFirebase: Boolean = false,


    val entite: String ,
    val categorie: String ,
    val division: String ,
    val convocation: String ,
    val presence: String ,
    val session: String ,
    val jsp: String ,
    val type: String ,
    val domaine: String

) : Parcelable


// ─────────────────────────────────────────────
// Invitation FLM
// ─────────────────────────────────────────────

@Parcelize
@Entity(tableName = "invitations_flm")
data class InvitationFlmEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val formationId: Long,
    val datesFormation:String,
    val formateur:String,
    val matriculeCollaborateur: String,
    val nomCompletCollaborateur: String,
    val service:String,
    val themeNom: String,
    val themeObjectives: List<String>,
    val emailFlm: String,
    val nomFlm: String,
    val statut: InvitationStatus = InvitationStatus.NON_EXPEDIEE,
    val dateEnvoi: Long = System.currentTimeMillis(),
) : Parcelable

//
//// ─────────────────────────────────────────────
//// Évaluation — complète avec tous les critères
//// ─────────────────────────────────────────────
//@Parcelize
//@Entity(
//    tableName = "evaluations",
//    foreignKeys = [ForeignKey(
//        entity = FormationEntity::class,
//        parentColumns = ["id"],
//        childColumns = ["formationId"],
//        onDelete = ForeignKey.CASCADE
//    )],
//    indices = [Index("formationId")]
//)
//data class EvaluationEntity(
//    @PrimaryKey(autoGenerate = true)
//
//    val id: Long = 0,
//    val invitationId: Long,
//    val dateEvaluation: String,
//    val flmMatricule: String,
//    val flmNom: String,
//
//    val moyenAppreciation: List<String>,
//
//    @Embedded
//    val critieres: CritieresEvaluation,
//
//    val raisonsInsatisfaction: List<String>,
//
//    // Commentaires
//    val propositionsAmelioration: String = "",
//    val commentaireGeneral: String = "",
//
//    val competencesAcquises: List<String> = emptyList(),
//
//    val formationSuivante:String="",
//
//    val googleFormResponseId: String = "",
//    val syncedToFirebase: Boolean = false,
//    val createdAt: Long = System.currentTimeMillis()
//
//) : Parcelable {

//}

@Parcelize
data class CritieresEvaluation(
    val satisfactionBesoin: Int = 0,
    val impactPerformance: Int = 0,
    val applicationConnaissances: Int = 0,
    val satisfactionGlobale: Int = 0
) : Parcelable


@Entity(tableName = "forms")
data class Forms(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val themeId: Long,
    val formUrl: String,
    @Embedded
    val entryIds: EntryIds
)

data class EntryIds(
    val formationId    : Long = 0,
    val intituleAction : Long = 0,
    val nomPrenom      : Long = 0,
    val matricule      : Long = 0,
    val service        : Long = 0,
    val formateur      : Long = 0,
    val dates          : Long = 0
)


data class FormCreationRequest(
    val themeNom: String,
    val competences: List<String> // themeObjectives
)

data class FormCreationResponse(
    val status: String?,
    val formUrl: String?,
    val formId: String?,
    val responseSheetId: String?,
    val entryIds: EntryIds?,
    val message: String?,
    val stack: String?
)
