package com.ocp.evalformation.ui.rh.import_data

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ocp.evalformation.databinding.FragmentImportThemesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImportThemesFragment : Fragment() {

    private var _binding: FragmentImportThemesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ImportViewModel by viewModels()

    private val pickExcel = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                requireContext().contentResolver.openInputStream(uri)?.let {
                    viewModel.importThemesFromExcel(it)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentImportThemesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allThemes.observe(viewLifecycleOwner) {
            binding.tvThemesCount.text = it.size.toString()
        }

        binding.btnAddTheme.setOnClickListener {
            viewModel.addTheme(
                nom = binding.etThemeNom.text.toString(),
                objectifPedagogique  = binding.etThemeObjectif.text.toString(),
            )
        }

        binding.btnImportThemesExcel.setOnClickListener {
            pickExcel.launch(Intent(Intent.ACTION_GET_CONTENT).apply { type = "*/*" })
        }

        lifecycleScope.launch {
            viewModel.themeState.collect { state ->
                when (state) {
                    is ImportState.Loading -> {
                        binding.progressTheme.visibility = View.VISIBLE
                        binding.progressThemesImport.visibility = View.VISIBLE
                        binding.tvThemesImportResult.visibility = View.GONE
                    }
                    is ImportState.Success -> {
                        binding.progressTheme.visibility = View.GONE
                        binding.progressThemesImport.visibility = View.GONE
                        binding.tvThemesImportResult.visibility = View.VISIBLE
                        binding.tvThemesImportResult.setTextColor(0xFF00693E.toInt())
                        binding.tvThemesImportResult.text = state.message
                        clearForm()
                        viewModel.resetThemeState()
                    }
                    is ImportState.Error -> {
                        binding.progressTheme.visibility = View.GONE
                        binding.progressThemesImport.visibility = View.GONE
                        binding.tvThemesImportResult.visibility = View.VISIBLE
                        binding.tvThemesImportResult.setTextColor(0xFFD32F2F.toInt())
                        binding.tvThemesImportResult.text = state.message
                        viewModel.resetThemeState()
                    }
                    is ImportState.Idle -> {
                        binding.progressTheme.visibility = View.GONE
                        binding.progressThemesImport.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun clearForm() {
        binding.etThemeNom.text?.clear()
        binding.etThemeObjectif.text?.clear()
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
