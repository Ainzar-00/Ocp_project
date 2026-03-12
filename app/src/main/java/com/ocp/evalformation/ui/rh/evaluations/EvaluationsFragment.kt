package com.ocp.evalformation.ui.rh.evaluations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ocp.evalformation.databinding.FragmentEvaluationsBinding
import com.ocp.evalformation.ui.rh.EvaluationAvecFormation
import com.ocp.evalformation.ui.rh.RhViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class EvaluationsFragment : Fragment() {

    private var _binding: FragmentEvaluationsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RhViewModel by activityViewModels()
    private lateinit var adapter: EvaluationsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEvaluationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = EvaluationsAdapter { item -> showEvaluationDetail(item) }
        binding.rvEvaluations.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEvaluations.adapter = adapter

        // Themes filter from allThemes
        viewModel.allThemes.observe(viewLifecycleOwner) { themes ->
            val items = listOf("Tous") + themes.map { it.nom }
            binding.spinnerFilterTheme.adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, items)
        }

        // Mois filter
        val months = listOf("Tous", "01", "02", "03", "04", "05", "06",
            "07", "08", "09", "10", "11", "12")
        binding.spinnerFilterMois.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, months)

        // Années filter
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val years = listOf("Tous") + (currentYear downTo currentYear - 5).map { it.toString() }
        binding.spinnerFilterAnnee.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, years)

        binding.btnAppliquerFiltres.setOnClickListener { applyFilters() }

        viewModel.evaluationsAvecFormation.observe(viewLifecycleOwner) { items ->
            adapter.submitList(items)
            binding.tvCountEval.text = "${items.size} évaluation(s)"
        }

        binding.btnExporterDetaille.setOnClickListener {
            Toast.makeText(requireContext(), "Export non encore implémenté", Toast.LENGTH_SHORT).show()
        }

        binding.btnSupprimerTout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Supprimer toutes les données")
                .setMessage("⚠️ Cette action supprimera TOUTES les évaluations, formations et collaborateurs. Irréversible.")
                .setPositiveButton("Supprimer") { _, _ ->
                    viewModel.deleteAllData()
                    Toast.makeText(requireContext(), "Base de données effacée.", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Annuler", null)
                .show()
        }
    }

    private fun applyFilters() {
        val theme = binding.spinnerFilterTheme.selectedItem?.toString()?.let { if (it == "Tous") null else it }
        val mois  = binding.spinnerFilterMois.selectedItem?.toString()?.let { if (it == "Tous") null else it }
        val annee = binding.spinnerFilterAnnee.selectedItem?.toString()?.let { if (it == "Tous") null else it }
        viewModel.setFilter(theme, annee, mois)
    }

    private fun showEvaluationDetail(item: EvaluationAvecFormation) {
        val eval = item.evaluation
        val msg = """
            👤 FLM         : ${eval.flmNom} (${eval.flmMatricule})
            📚 Formation ID : ${eval.formationId}
            📅 Date         : ${eval.dateEvaluation}

            📊 SCORES (sur 4) :
            • Organisation & Contenu    : ${eval.organisationContenu}
            • Qualité Pédagogique       : ${eval.qualitePedagogique}
            • Adaptation au Public      : ${eval.adaptationPublic}
            • Maîtrise du Sujet         : ${eval.maitriseSujet}
            • Disponibilité Formateur   : ${eval.disponibiliteFormateur}
            • Qualité des Supports      : ${eval.qualiteSupports}
            • Atteinte des Objectifs    : ${eval.atteinteObjectifs}
            • Application Terrain       : ${eval.applicationTerrain}

            📈 Taux de satisfaction : ${"%.1f".format(eval.tauxSatisfactionPct())}%

            💡 Propositions : ${eval.propositionsAmelioration.ifBlank { "—" }}
            🎓 Compétences  : ${eval.competencesAcquises.ifBlank { "—" }}
            💬 Commentaire  : ${eval.commentaireGeneral.ifBlank { "—" }}
        """.trimIndent()

        AlertDialog.Builder(requireContext())
            .setTitle("Détail de l'évaluation")
            .setMessage(msg)
            .setPositiveButton("Fermer", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
