package com.ocp.evalformation.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index
import android.os.Parcelable
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
    val syncedToFirebase: Boolean = false
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
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val formationId: Long,
    val dateEvaluation: String,
    val flmMatricule: String,
    val flmNom: String,

    // Critères (score 1–4)
    val organisationContenu: Int = 0,
    val qualitePedagogique: Int = 0,
    val adaptationPublic: Int = 0,
    val maitriseSujet: Int = 0,
    val disponibiliteFormateur: Int = 0,
    val qualiteSupports: Int = 0,
    val atteinteObjectifs: Int = 0,
    val applicationTerrain: Int = 0,

    // Commentaires
    val propositionsAmelioration: String = "",
    val commentaireGeneral: String = "",
    val competencesAcquises: String = "",

    val statut: String = "SOUMIS",
    val googleFormResponseId: String = "",
    val syncedToFirebase: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable {
    fun moyenneSatisfaction(): Double {
        val scores = listOf(
            organisationContenu, qualitePedagogique, adaptationPublic,
            maitriseSujet, disponibiliteFormateur, qualiteSupports,
            atteinteObjectifs, applicationTerrain
        )
        return scores.average()
    }
    fun tauxSatisfactionPct(): Double = (moyenneSatisfaction() / 4.0) * 100.0
}

// ─────────────────────────────────────────────
// Invitation FLM
// ─────────────────────────────────────────────
@Parcelize
@Entity(tableName = "invitations_flm")
data class InvitationFlmEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val formationId: Long,
    val matriculeCollaborateur: String,
    val nomCompletCollaborateur: String,
    val themeNom: String,
    val emailFlm: String,
    val nomFlm: String,
    val googleFormUrl: String,
    val statut: String = "ENVOYE",
    val dateEnvoi: Long = System.currentTimeMillis(),
    val dateReponse: Long? = null,
    val evaluationId: Long? = null
) : Parcelable
