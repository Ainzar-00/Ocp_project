package com.ocp.evalformation.ui.rh.evaluations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ocp.evalformation.R
import com.ocp.evalformation.databinding.FragmentEvaluationsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EvaluationsFragment : Fragment() {

    private val viewModel: EvaluationViewModel by navGraphViewModels(R.id.nav_graph_rh) {
        defaultViewModelProviderFactory
    }

    private var _binding: FragmentEvaluationsBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: EvaluationAdapter

    private val monthMap = mapOf(
        "Janvier" to 1,
        "Février" to 2,
        "Mars" to 3,
        "Avril" to 4,
        "Mai" to 5,
        "Juin" to 6,
        "Juillet" to 7,
        "Août" to 8,
        "Septembre" to 9,
        "Octobre" to 10,
        "Novembre" to 11,
        "Décembre" to 12
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEvaluationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupDropdowns()
        observeData()
        setupSearch()

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

        binding.autocompleteMois.isSaveEnabled = false
        binding.autocompleteAnnee.isSaveEnabled = false
        binding.autocompleteEntite.isSaveEnabled = false
        binding.autocompleteTheme.isSaveEnabled = false
        binding.autocompleteService.isSaveEnabled=false

        val moisList = listOf(
            "Tous", "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
            "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"
        )

        binding.autocompleteMois.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                moisList
            )
        )

        val currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
        val anneeList = listOf("Toutes") + (currentYear downTo currentYear - 4).map { it.toString() }

        binding.autocompleteAnnee.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                anneeList
            )
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.evaluations.collect { list ->

                    val services=listOf("Toutes")+
                                 list.map { it.services }
                                 .filter { it.isNotBlank() }
                                 .distinct()
                                 .sorted()

                    val entites = listOf("Toutes") +
                            list.map { it.entite }
                                .filter { it.isNotBlank() }
                                .distinct()
                                .sorted()

                    val themes = listOf("Tous") +
                            list.map { it.themeNom }
                                .filter { it.isNotBlank() }
                                .distinct()
                                .sorted()

                    binding.autocompleteService.setAdapter(
                               ArrayAdapter(
                               requireContext(),
                                        android.R.layout.simple_dropdown_item_1line,
                                        services
                                        ))

                    binding.autocompleteEntite.setAdapter(
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_dropdown_item_1line,
                            entites
                        )
                    )

                    binding.autocompleteTheme.setAdapter(
                        ArrayAdapter(
                            requireContext(),
                            android.R.layout.simple_dropdown_item_1line,
                            themes
                        )
                    )
                }
            }
        }
    }

    private fun setupSearch() {
        binding.btnAppliquerFiltres.setOnClickListener {
            val matricule = binding.etMatricule.text?.toString()?.trim().orEmpty()

            val entite = binding.autocompleteEntite.text?.toString()?.trim()
                .takeIf { !it.isNullOrBlank() && it != "Toutes" }
                .orEmpty()

            val service = binding.autocompleteService.text?.toString()?.trim()
                                        .takeIf { !it.isNullOrBlank() && it != "Toutes" }
                                        .orEmpty()

            val theme = binding.autocompleteTheme.text?.toString()?.trim()
                .takeIf { !it.isNullOrBlank() && it != "Tous" }
                .orEmpty()

            val moisText = binding.autocompleteMois.text?.toString()?.trim().orEmpty()
            val mois = monthMap[moisText]

            val anneeText = binding.autocompleteAnnee.text?.toString()?.trim().orEmpty()
            val annee = anneeText.toIntOrNull()?.takeIf { it > 1900 }

            viewModel.search(
                matricule = matricule,
                service = service,
                entite = entite,
                theme = theme,
                mois = mois,
                annee = annee
            )

            binding.rvEvaluations.post {
                val startY = binding.nestedScrollView.scrollY
                val endY = binding.rvEvaluations.top

                android.animation.ValueAnimator.ofInt(startY, endY).apply {
                    duration = 500 // ms — tweak this (300 = snappy, 500 = smooth)
                    interpolator = android.view.animation.DecelerateInterpolator()
                    addUpdateListener {
                        binding.nestedScrollView.scrollTo(0, it.animatedValue as Int)
                    }
                    start()
                }
            }

        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.filtered.collect { list ->
                    adapter.submitList(list)
                    binding.tvCountEval.text = "${list.size} résultats trouvés"
                }
            }
        }
    }

    private fun resetFiltersUI() {
        binding.etMatricule.setText("")

        binding.autocompleteEntite.setText("Toutes", false)
        binding.autocompleteTheme.setText("Tous", false)
        binding.autocompleteMois.setText("Tous", false)
        binding.autocompleteAnnee.setText("Toutes", false)
        binding.autocompleteService.setText("Toutes", false)

        binding.autocompleteEntite.clearFocus()
        binding.autocompleteTheme.clearFocus()
        binding.autocompleteMois.clearFocus()
        binding.autocompleteAnnee.clearFocus()
        binding.tilService.clearFocus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        resetFiltersUI()
    }

}