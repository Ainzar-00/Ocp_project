package com.ocp.evalformation.ui.rh.evaluations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ocp.evalformation.R
import com.ocp.evalformation.databinding.FragmentEvaluationsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class EvaluationsFragment : Fragment() {

    private val viewModel: EvaluationViewModel by navGraphViewModels(R.id.nav_graph_rh) {
        defaultViewModelProviderFactory
    }

    private var _binding: FragmentEvaluationsBinding? = null
    private val binding get() = _binding!!


    private lateinit var adapter: EvaluationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEvaluationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupDropdowns()
        setupSearch()
        observeData()
    }

    private fun setupRecyclerView() {
        adapter = EvaluationAdapter { item ->
            viewModel.select(item)
            findNavController().navigate(
                R.id.action_evaluationsFragment_to_evaluationDetailFragment
            )
        }
        adapter.formatDateFn = viewModel::formatDate
        binding.rvEvaluations.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEvaluations.adapter = adapter
    }

    private fun setupDropdowns() {
        val moisList = listOf(
            "Tous", "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
            "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"
        )
        binding.autocompleteMois.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, moisList)
        )

        val currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
        val anneeList   = listOf("Toutes") + (currentYear downTo currentYear - 4).map { it.toString() }
        binding.autocompleteAnnee.setAdapter(
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, anneeList)
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.evaluations.collect { list ->
                val entites = listOf("Toutes") + list.map { it.entite }
                    .filter { it.isNotBlank() }.distinct().sorted()
                binding.autocompleteEntite.setAdapter(
                    ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, entites)
                )
                val themes = listOf("Tous") + list.map { it.themeNom }.distinct().sorted()
                binding.autocompleteTheme.setAdapter(
                    ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, themes)
                )
            }
        }
    }

    private fun setupSearch() {
        binding.btnAppliquerFiltres.setOnClickListener {
            val matricule = binding.etMatricule.text?.toString()?.trim() ?: ""
            val entite    = binding.autocompleteEntite.text?.toString()?.trim()
                .takeIf { it != "Toutes" } ?: ""
            val theme     = binding.autocompleteTheme.text?.toString()?.trim()
                .takeIf { it != "Tous" } ?: ""
            val moisStr   = binding.autocompleteMois.text?.toString()?.trim()
            val anneeStr  = binding.autocompleteAnnee.text?.toString()?.trim()

            val moisIndex = listOf(
                "Tous", "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
                "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"
            ).indexOf(moisStr).takeIf { it > 0 }

            val annee = anneeStr?.toIntOrNull()
            viewModel.search(matricule, entite, theme, moisIndex, annee)
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.filtered.collect { list ->
                adapter.submitList(list)
                binding.tvCountEval.text = "${list.size} résultats trouvés"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}