package com.ocp.evalformation.ui.rh.dashboard

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ocp.evalformation.data.local.entity.InvitationStatus
import com.ocp.evalformation.databinding.ItemInvitationBinding
import com.ocp.evalformation.ui.rh.FormationWithInvitation
import java.text.SimpleDateFormat
import java.util.*

class InvitationsAdapter(
    private val onEnvoyer: (FormationWithInvitation) -> Unit,
    private val onRenvoyer: (FormationWithInvitation) -> Unit
) : ListAdapter<FormationWithInvitation, InvitationsAdapter.VH>(DIFF) {

    private var themesMap: Map<Long, String> = emptyMap()

    fun submitThemes(themes: Map<Long, String>) {
        themesMap = themes
        notifyDataSetChanged()
    }

    inner class VH(private val b: ItemInvitationBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(item: FormationWithInvitation) {
            val formation = item.formation
            val inv       = item.invitation

            val themeName = inv?.themeNom
                ?: themesMap[formation.themeId]
                ?: "Thème #${formation.themeId}"

            b.tvTheme.text     = "Thème: ${themeName ?: formation.themeId}"
//            Log.d("Theme Appear","${inv?.themeNom}")
            b.tvMatricule.text = "Matricule: ${formation.collaborateurMatricule}"
            b.tvService.text   = "Service: ${inv?.service ?: formation.division}"

            when (item.status) {
                InvitationStatus.NON_EXPEDIEE -> {
                    b.tvStatus.text = "Non expédiée"
                    b.tvStatus.setTextColor(Color.parseColor("#757575"))
                    b.btnEnvoyer.visibility      = View.VISIBLE
                    b.btnEnvoyerAutre.visibility = View.GONE
                    b.btnEnvoyer.text = "Envoyer"
                    b.btnEnvoyer.backgroundTintList =
                        ColorStateList.valueOf(Color.parseColor("#2E7D32"))
                    b.btnEnvoyer.setOnClickListener { onEnvoyer(item) }
                }

                InvitationStatus.EN_ATTENTE -> {
                    b.tvStatus.text = "⏳ En attente de réponse"
                    b.tvStatus.setTextColor(Color.parseColor("#FFA000"))
                    b.btnEnvoyer.visibility      = View.GONE
                    b.btnEnvoyerAutre.visibility = View.VISIBLE
                    b.btnEnvoyerAutre.text       = "Renvoyer"
                    b.btnEnvoyerAutre.strokeColor =
                        ColorStateList.valueOf(Color.parseColor("#FFA000"))
                    b.btnEnvoyerAutre.setTextColor(Color.parseColor("#FFA000"))
                    inv?.dateEnvoi?.let { d ->
                        b.tvStatus.text = "⏳ En attente — envoyé le ${formatDate(d)}"
                    }
                    b.btnEnvoyerAutre.setOnClickListener { onRenvoyer(item) }
                }

                InvitationStatus.REPONDUE -> {
                    b.tvStatus.text = "✅ Répondu"
                    b.tvStatus.setTextColor(Color.parseColor("#2E7D32"))
                    b.btnEnvoyer.visibility      = View.GONE
                    b.btnEnvoyerAutre.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemInvitationBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    private fun formatDate(millis: Long) =
        SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE).format(Date(millis))

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<FormationWithInvitation>() {
            override fun areItemsTheSame(a: FormationWithInvitation, b: FormationWithInvitation) =
                a.formation.id == b.formation.id
            override fun areContentsTheSame(a: FormationWithInvitation, b: FormationWithInvitation) =
                a == b
        }
    }
}
