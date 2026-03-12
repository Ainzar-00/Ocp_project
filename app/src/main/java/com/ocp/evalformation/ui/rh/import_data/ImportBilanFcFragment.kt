package com.ocp.evalformation.ui.rh.import_data

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ocp.evalformation.data.repository.syncRepository
import com.ocp.evalformation.databinding.FragmentImportBilanFcBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImportBilanFcFragment : Fragment() {


    private var _binding: FragmentImportBilanFcBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ImportViewModel by viewModels()

    private var pendingUri: android.net.Uri? = null

    private val pickExcel = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                pendingUri = uri
                val name = uri.lastPathSegment ?: "fichier sélectionné"
                binding.tvBilanSelectedFile.text = "📄 $name"
                binding.tvBilanSelectedFile.visibility = View.VISIBLE
                binding.btnChargerBilan.isEnabled = true
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentImportBilanFcBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Live counts
        viewModel.allCollaborateurs.observe(viewLifecycleOwner) {
            Log.d("Formations colla",it.toString())
            binding.tvBilanCollabCount.text = it.size.toString()
        }

        viewModel.allFormations.observe(viewLifecycleOwner)     {
            Log.d("Formations Form",it.toString())
            binding.tvBilanFormationCount.text = it.size.toString()
        }

        viewModel.allThemes.observe(viewLifecycleOwner){
            binding.tvBilanThemeCount.text = it.size.toString()
        }

        binding.btnChoisirBilan.setOnClickListener {
            pickExcel.launch(Intent(Intent.ACTION_GET_CONTENT).apply { type = "*/*" })
        }

        binding.btnChargerBilan.setOnClickListener {
            pendingUri?.let { uri ->
                requireContext().contentResolver.openInputStream(uri)?.let {
                    viewModel.importBilanFC(it)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.bilanState.collect { state ->
                when (state) {
                    is ImportState.Loading -> {
                        binding.progressBilan.visibility = View.VISIBLE
                        binding.tvBilanResult.visibility = View.GONE
                        binding.btnChargerBilan.isEnabled = false
                    }
                    is ImportState.Success -> {
                        binding.progressBilan.visibility = View.GONE
                        binding.tvBilanResult.visibility = View.VISIBLE
                        binding.tvBilanResult.setTextColor(0xFF00693E.toInt())
                        binding.tvBilanResult.text = state.message
                        binding.btnChargerBilan.isEnabled = false
                        pendingUri = null
                        viewModel.resetBilanState()
                    }
                    is ImportState.Error -> {
                        binding.progressBilan.visibility = View.GONE
                        binding.tvBilanResult.visibility = View.VISIBLE
                        binding.tvBilanResult.setTextColor(0xFFD32F2F.toInt())
                        binding.tvBilanResult.text = state.message
                        binding.btnChargerBilan.isEnabled = pendingUri != null
                        viewModel.resetBilanState()
                    }
                    is ImportState.Idle -> binding.progressBilan.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}