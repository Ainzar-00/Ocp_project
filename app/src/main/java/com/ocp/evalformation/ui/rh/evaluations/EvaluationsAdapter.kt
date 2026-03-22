package com.ocp.evalformation.ui.rh.evaluations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ocp.evalformation.data.local.entity.EvaluationWithContext
import com.ocp.evalformation.databinding.ItemEvaluationBinding

class EvaluationAdapter(
    private val onItemClick: (EvaluationWithContext) -> Unit
) : ListAdapter<EvaluationWithContext, EvaluationAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(private val binding: ItemEvaluationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EvaluationWithContext, formatDate: (String) -> String) {
            val eval = item.evaluation
            binding.tvItemTheme.text     = item.themeNom
            binding.tvItemMatricule.text = "Mat: ${eval.maticuleCollaborateur}"
            binding.tvItemDate.text      = formatDate(eval.dateEvaluation)
            binding.tvItemEntite.text    = item.entite
            binding.root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEvaluationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // formatDate passed from fragment via ViewModel
        holder.bind(getItem(position), formatDateFn)
    }

    // Set from fragment
    var formatDateFn: (String) -> String = { it }

    class DiffCallback : DiffUtil.ItemCallback<EvaluationWithContext>() {
        override fun areItemsTheSame(a: EvaluationWithContext, b: EvaluationWithContext) =
            a.evaluation.id == b.evaluation.id
        override fun areContentsTheSame(a: EvaluationWithContext, b: EvaluationWithContext) =
            a == b
    }
}