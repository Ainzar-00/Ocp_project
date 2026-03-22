package com.ocp.evalformation.ui.rh.evaluations

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.ocp.evalformation.R
import com.ocp.evalformation.data.local.entity.EvaluationEntity
import com.ocp.evalformation.data.local.entity.FlmEntity
import com.ocp.evalformation.data.local.entity.FormationEntity
import com.ocp.evalformation.databinding.FragmentEvaluationDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EvaluationDetailFragment : Fragment() {



    private val viewModel: EvaluationViewModel by navGraphViewModels(R.id.nav_graph_rh) {
        defaultViewModelProviderFactory
    }

    private var _binding: FragmentEvaluationDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEvaluationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            combine(
                viewModel.selected,
                viewModel.formation,
                viewModel.flm
            ) { eval, formation, flm ->
                Triple(eval, formation, flm)
            }.collect { (eval, formation, flm) ->
                if (eval != null) bindData(eval, formation, flm)
            }
        }
    }

    private fun bindData(
        eval     : EvaluationEntity,
        formation: FormationEntity?,
        flm      : FlmEntity?
    ) {
        // ── General Info ──────────────────────────────────────────
        binding.tvMatricule.text      = eval.maticuleCollaborateur
        binding.tvIntituleAction.text = eval.intituleAction

        // ── Performance Scores ────────────────────────────────────
        val besoin = eval.critieres.satisfactionBesoin
        val impact = eval.critieres.impactPerformance
        val appli  = eval.critieres.applicationConnaissances
        val global = eval.critieres.satisfactionGlobale

        binding.tvScoreBesoin.text     = "$besoin/4"
        binding.pbScoreBesoin.max      = 4
        binding.pbScoreBesoin.progress = besoin

        binding.tvScoreImpact.text     = "$impact/4"
        binding.pbScoreImpact.max      = 4
        binding.pbScoreImpact.progress = impact

        binding.tvScoreAppli.text     = "$appli/4"
        binding.pbScoreAppli.max      = 4
        binding.pbScoreAppli.progress = appli

        binding.tvScoreGlobal.text     = "$global/4"
        binding.pbScoreGlobal.max      = 4
        binding.pbScoreGlobal.progress = global

        // ── Methodology ───────────────────────────────────────────
        bindDynamicList(
            container = binding.layoutMethodologie,
            items     = eval.moyensAppreciation,
            prefix    = "▪  ",
            textColor = "#212121",
            emptyText = "Non renseigné"
        )

        // ── Competences Acquises ──────────────────────────────────
        bindDynamicList(
            container = binding.layoutCompetences,
            items     = eval.competencesAcquises,
            prefix    = "✓  ",
            textColor = "#2E7D32",
            emptyText = "Aucune compétence renseignée"
        )

        // ── Raisons Insatisfaction ────────────────────────────────
        bindDynamicList(
            container = binding.layoutRaisons,
            items     = eval.raisonsInsatisfaction,
            prefix    = "—  ",
            textColor = "#B71C1C",
            emptyText = "Aucun axe d'amélioration renseigné"
        )

        // ── Observations du Manager ───────────────────────────────
        binding.tvSuggestions.text = eval.Suggestions.ifBlank { "Aucune observation." }
    }

    private fun bindDynamicList(
        container : LinearLayout,
        items     : List<String>,
        prefix    : String,
        textColor : String,
        emptyText : String
    ) {
        container.removeAllViews()
        val displayItems = items.filter { it.isNotBlank() }.ifEmpty { listOf(emptyText) }

        displayItems.forEachIndexed { index, text ->
            val tv = TextView(requireContext()).apply {
                this.text = "$prefix$text"
                textSize  = 14f
                setPadding(dpToPx(12), dpToPx(12), dpToPx(12), dpToPx(12))
                setTextColor(Color.parseColor(textColor))
            }
            container.addView(tv)

            if (index < displayItems.size - 1) {
                val divider = View(requireContext()).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, dpToPx(1)
                    )
                    setBackgroundColor(Color.parseColor("#EEEEEE"))
                }
                container.addView(divider)
            }
        }
    }

    private fun dpToPx(dp: Int): Int =
        (dp * resources.displayMetrics.density).toInt()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}