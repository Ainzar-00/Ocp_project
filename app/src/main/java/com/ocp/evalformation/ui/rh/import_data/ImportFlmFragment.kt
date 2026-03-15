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
import com.ocp.evalformation.databinding.FragmentImportFlmBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImportFlmFragment : Fragment() {

    private var _binding: FragmentImportFlmBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ImportViewModel by viewModels()

    private val pickExcel = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                requireContext().contentResolver.openInputStream(uri)?.let {
                    viewModel.importFlmsFromExcel(it)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentImportFlmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allFlms.observe(viewLifecycleOwner) {
            binding.tvFlmCount.text = it.size.toString()
        }

        binding.btnAddFlm.setOnClickListener {
            viewModel.addFlm(
                matricule = binding.etFlmMatricule.text.toString(),
                nom       = binding.etFlmNom.text.toString(),
                prenom    = binding.etFlmPrenom.text.toString(),
                email     = binding.etFlmEmail.text.toString(),
                service   = binding.etFlmService.text.toString()
            )
        }

        binding.btnImportFlmExcel.setOnClickListener {
            pickExcel.launch(Intent(Intent.ACTION_GET_CONTENT).apply { type = "*/*" })
        }

        lifecycleScope.launch {
            viewModel.flmState.collect { state ->
                when (state) {
                    is ImportState.Loading -> {
                        binding.progressFlmImport.visibility = View.VISIBLE
                        binding.tvFlmImportResult.visibility = View.GONE
                    }
                    is ImportState.Success -> {
                        binding.progressFlmImport.visibility = View.GONE
                        binding.tvFlmImportResult.visibility = View.VISIBLE
                        binding.tvFlmImportResult.setTextColor(0xFF00693E.toInt())
                        binding.tvFlmImportResult.text = state.message
                        clearForm()
                        viewModel.resetFlmState()
                    }
                    is ImportState.Error -> {
                        binding.progressFlmImport.visibility = View.GONE
                        binding.tvFlmImportResult.visibility = View.VISIBLE
                        binding.tvFlmImportResult.setTextColor(0xFFD32F2F.toInt())
                        binding.tvFlmImportResult.text = state.message
                        viewModel.resetFlmState()
                    }
                    is ImportState.Idle -> binding.progressFlmImport.visibility = View.GONE
                }
            }
        }
    }

    private fun clearForm() {
        binding.etFlmMatricule.text?.clear()
        binding.etFlmNom.text?.clear()
        binding.etFlmPrenom.text?.clear()
        binding.etFlmEmail.text?.clear()
        binding.etFlmService.text?.clear()
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
