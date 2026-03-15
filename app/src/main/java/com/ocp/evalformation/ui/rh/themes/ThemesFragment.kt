package com.ocp.evalformation.ui.rh.themes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.ocp.evalformation.databinding.FragmentThemesBinding
import com.ocp.evalformation.ui.rh.RhViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ThemesFragment : Fragment() {

    private var _binding: FragmentThemesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RhViewModel by activityViewModels()
    private lateinit var adapter: ThemesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThemesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ThemesAdapter()
        binding.recyclerThemes.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerThemes.adapter = adapter

        // Observe themes list
        viewModel.allThemes.observe(viewLifecycleOwner) { themes ->
            adapter.submitList(themes)
            binding.tvEmptyThemes.visibility = if (themes.isEmpty()) View.VISIBLE else View.GONE
            binding.recyclerThemes.visibility = if (themes.isEmpty()) View.GONE else View.VISIBLE
        }

        // Observe add state
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.themeState.collectLatest { state ->
                when (state) {
                    is RhViewModel.ThemeState.Loading -> {
                        binding.btnAddTheme.isEnabled = false
                        binding.progressTheme.visibility = View.VISIBLE
                    }
                    is RhViewModel.ThemeState.Success -> {
                        binding.btnAddTheme.isEnabled = true
                        binding.progressTheme.visibility = View.GONE
                        Toast.makeText(requireContext(),
                            "✅ Thème \"${state.theme.nom}\" ajouté avec succès !",
                            Toast.LENGTH_SHORT).show()
                        viewModel.resetThemeState()
                    }
                    is RhViewModel.ThemeState.Error -> {
                        binding.btnAddTheme.isEnabled = true
                        binding.progressTheme.visibility = View.GONE
                        Toast.makeText(requireContext(), "❌ ${state.message}", Toast.LENGTH_LONG).show()
                        viewModel.resetThemeState()
                    }
                    is RhViewModel.ThemeState.Idle -> {
                        binding.btnAddTheme.isEnabled = true
                        binding.progressTheme.visibility = View.GONE
                    }
                }
            }
        }

        // FAB / Button: show dialog to add theme
        binding.btnAddTheme.setOnClickListener {
            showAddThemeDialog()
        }
    }

    private fun showAddThemeDialog() {
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(com.ocp.evalformation.R.layout.dialog_add_theme, null)

        val etNom = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(
            com.ocp.evalformation.R.id.et_theme_nom)
        val etObjectif = dialogView.findViewById<com.google.android.material.textfield.TextInputEditText>(
            com.ocp.evalformation.R.id.et_theme_objectif)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("➕ Nouveau Thème")
            .setView(dialogView)
            .setPositiveButton("Ajouter") { _, _ ->
                val nom = etNom?.text?.toString() ?: ""
                val objectif = etObjectif?.text?.toString() ?: ""
                viewModel.addTheme(nom, objectif)
            }
            .setNegativeButton("Annuler", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
