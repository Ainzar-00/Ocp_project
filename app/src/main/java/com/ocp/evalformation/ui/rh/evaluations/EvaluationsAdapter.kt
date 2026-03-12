package com.ocp.evalformation.ui.rh.evaluations

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ocp.evalformation.databinding.ItemEvaluationBinding
import com.ocp.evalformation.ui.rh.EvaluationAvecFormation

class EvaluationsAdapter(
    private val onItemClick: (EvaluationAvecFormation) -> Unit
) : ListAdapter<EvaluationAvecFormation, EvaluationsAdapter.VH>(DIFF) {

    inner class VH(private val binding: ItemEvaluationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EvaluationAvecFormation) {
            binding.tvMatricule.text = item.evaluation.flmMatricule
            binding.tvTheme.text     = item.themeNom
            binding.tvFLM.text       = "FLM : ${item.flmNom}"
            binding.tvDate.text      = item.dateEvaluation

            val taux = item.evaluation.tauxSatisfactionPct()
            binding.tvTaux.text = "%.1f%%".format(taux)
            binding.tvTaux.setTextColor(
                when {
                    taux >= 75 -> Color.parseColor("#00A86B")
                    taux >= 50 -> Color.parseColor("#FF9800")
                    else       -> Color.parseColor("#F44336")
                }
            )
            binding.progressSatisfaction.progress = taux.toInt()
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemEvaluationBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<EvaluationAvecFormation>() {
            override fun areItemsTheSame(a: EvaluationAvecFormation, b: EvaluationAvecFormation) =
                a.evaluation.id == b.evaluation.id
            override fun areContentsTheSame(a: EvaluationAvecFormation, b: EvaluationAvecFormation) =
                a == b
        }
    }
}
