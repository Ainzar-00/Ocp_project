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
    val firebaseId: String = "",
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


// ─────────────────────────────────────────────
// Évaluation — complète avec tous les critères
// ─────────────────────────────────────────────

@Parcelize
@Entity(
    tableName = "evaluations",
    foreignKeys = [ForeignKey(
        entity = FormationEntity::class,
        parentColumns = ["id"],
        childColumns = ["formationId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("formationId")]
)

data class EvaluationEntity(
    @PrimaryKey(autoGenerate = true)

    val id: Long = 0,
    val formationId: Long,
    val intituleAction: String,
    val maticuleCollaborateur: String,
    val datesFormation: String,
    val dateEvaluation: String,
    val moyensAppreciation: List<String>,
    @Embedded
    val critieres: CritieresEvaluation,
    val raisonsInsatisfaction: List<String>,
    val competencesAcquises: List<String> = emptyList(),
    // Commentaires
    val Suggestions: String = ""

) : Parcelable

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
    val formationId    : String = "",
    val intituleAction : String = "",
    val nomPrenom      : String = "",
    val matricule      : String = "",
    val service        : String = "",
    val formateur      : String = "",
    val dates          : String = ""
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

// Combines all data needed for list and detail display
data class EvaluationWithContext(
    val evaluation: EvaluationEntity,
    val formation : FormationEntity?,
    val flm       : FlmEntity?
) {
    val entite  : String get() = formation?.entite ?: ""
    val themeNom: String get() = evaluation.intituleAction
}

// Radar chart — average score per criterion (1-4)
data class CriteriaAverages(
    val satisfactionBesoin      : Float,
    val impactPerformance       : Float,
    val applicationConnaissances: Float,
    val satisfactionGlobale     : Float
)

// Pie chart — satisfaction vs insatisfaction
data class SatisfactionRate(
    val positiveCount  : Int,   // score 3 or 4
    val negativeCount  : Int,   // score 1 or 2
    val total          : Int,
    val positivePercent: Float,
    val negativePercent: Float
)