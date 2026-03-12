package com.ocp.evalformation.ui.rh.import_data

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ocp.evalformation.databinding.FragmentImportCollaborateursBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImportCollaborateursFragment : Fragment() {

    private var _binding: FragmentImportCollaborateursBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ImportViewModel by viewModels()

    private lateinit var pickExcel: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pickExcel = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    requireContext().contentResolver.openInputStream(uri)?.let { stream ->
                        viewModel.importCollaborateursFromExcel(stream)
                    }
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImportCollaborateursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allCollaborateurs.observe(viewLifecycleOwner) {
            binding.tvCollabCount.text = it.size.toString()
        }

        binding.btnAddCollab.setOnClickListener {
            viewModel.addCollaborateur(
                matricule = binding.etCollabMatricule.text.toString(),
                nom = binding.etCollabNom.text.toString(),
                prenom = binding.etCollabPrenom.text.toString(),
                service = binding.etCollabService.text.toString(),
                flmMatricule = binding.etCollabFlm.text.toString()
            )
        }

        binding.btnImportCollabExcel.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                type = "*/*"
            }
            pickExcel.launch(intent)
        }

        lifecycleScope.launch {
            viewModel.collabState.collect { state ->
                when (state) {

                    is ImportState.Loading -> {
                        binding.progressCollabImport.visibility = View.VISIBLE
                        binding.tvCollabImportResult.visibility = View.GONE
                    }

                    is ImportState.Success -> {
                        binding.progressCollabImport.visibility = View.GONE
                        binding.tvCollabImportResult.visibility = View.VISIBLE
                        binding.tvCollabImportResult.setTextColor(0xFF00693E.toInt())
                        binding.tvCollabImportResult.text = state.message
                        clearForm()
                        viewModel.resetCollabState()
                    }

                    is ImportState.Error -> {
                        binding.progressCollabImport.visibility = View.GONE
                        binding.tvCollabImportResult.visibility = View.VISIBLE
                        binding.tvCollabImportResult.setTextColor(0xFFD32F2F.toInt())
                        binding.tvCollabImportResult.text = state.message
                        viewModel.resetCollabState()
                    }

                    is ImportState.Idle -> {
                        binding.progressCollabImport.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun clearForm() {
        binding.etCollabMatricule.text?.clear()
        binding.etCollabNom.text?.clear()
        binding.etCollabPrenom.text?.clear()
        binding.etCollabService.text?.clear()
        binding.etCollabFlm.text?.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}