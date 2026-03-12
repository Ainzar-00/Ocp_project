package com.ocp.evalformation.ui.rh.dashboard

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ocp.evalformation.data.local.entity.InvitationFlmEntity
import com.ocp.evalformation.databinding.ItemInvitationBinding
import java.text.SimpleDateFormat
import java.util.*

class InvitationsAdapter : ListAdapter<InvitationFlmEntity, InvitationsAdapter.VH>(DIFF) {

    inner class VH(private val b: ItemInvitationBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(inv: InvitationFlmEntity) {
            b.tvCollabNom.text  = inv.nomCompletCollaborateur
            b.tvTheme.text      = "📚 ${inv.themeNom}"
            b.tvFlmEmail.text   = "📧 ${inv.emailFlm} — ${inv.nomFlm}"
            b.tvDateEnvoi.text  = "Envoyé : ${formatDate(inv.dateEnvoi)}"
            b.tvStatut.text = when (inv.statut) {
                "ENVOYE"  -> "⏳ En attente"
                "REPONDU" -> "✅ Répondu"
                "EXPIRE"  -> "⌛ Expiré"
                else      -> inv.statut
            }
            b.tvStatut.setTextColor(
                when (inv.statut) {
                    "REPONDU" -> Color.parseColor("#00A86B")
                    "EXPIRE"  -> Color.parseColor("#F44336")
                    else      -> Color.parseColor("#FF9800")
                }
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemInvitationBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    private fun formatDate(millis: Long) =
        SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRANCE).format(Date(millis))

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<InvitationFlmEntity>() {
            override fun areItemsTheSame(a: InvitationFlmEntity, b: InvitationFlmEntity) = a.id == b.id
            override fun areContentsTheSame(a: InvitationFlmEntity, b: InvitationFlmEntity) = a == b
        }
    }
}
