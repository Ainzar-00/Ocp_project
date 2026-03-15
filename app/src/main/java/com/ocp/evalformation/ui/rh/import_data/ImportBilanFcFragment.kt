package com.ocp.evalformation.ui.rh.import_data

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ocp.evalformation.data.local.entity.FormationEntity
import com.ocp.evalformation.databinding.FragmentImportBilanFcBinding
import com.ocp.evalformation.utils.dateHelper
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

        setupStatsObservers()
        setupExcelImport()
        setupManualImport()
        observeImportState()
    }

    private fun setupStatsObservers() {
        viewModel.allCollaborateurs.observe(viewLifecycleOwner) {
            binding.tvBilanCollabCount.text = it.size.toString()
        }
        viewModel.allFormations.observe(viewLifecycleOwner) {
            binding.tvBilanFormationCount.text = it.size.toString()
        }
        viewModel.allThemes.observe(viewLifecycleOwner) {
            binding.tvBilanThemeCount.text = it.size.toString()
        }
    }

    private fun setupExcelImport() {
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
    }

    private fun setupManualImport() {
        // Toggle Expand/Collapse
        binding.layoutHeaderManualAdd.setOnClickListener {
            val isHidden = binding.layoutManualForm.visibility == View.GONE
            binding.layoutManualForm.visibility = if (isHidden) View.VISIBLE else View.GONE
            binding.ivExpandIcon.animate().rotation(if (isHidden) 180f else 0f).setDuration(200).start()
        }

        // Save Button Logic
        binding.btnSaveManual.setOnClickListener {
            val matricule = binding.etManualMatricule.text.toString().trim()
            val themeIdStr = binding.etManualThemeId.text.toString().trim()
            val debut = binding.etManualDebut.text.toString().trim()
            val fin = binding.etManualFin.text.toString().trim()
            val formateur = binding.etManualFormateur.text.toString().trim()

            // New fields
            val entite = binding.etManualEntite.text.toString().trim()
            val categorie = binding.etManualCategorie.text.toString().trim()
            val division = binding.etManualDivision.text.toString().trim()
            val convocation = binding.etManualConvocation.text.toString().trim()
            val presence = binding.etManualPresence.text.toString().trim()
            val session = binding.etManualSession.text.toString().trim()
            val jsp = binding.etManualJsp.text.toString().trim()
            val type = binding.etManualType.text.toString().trim()
            val domaine = binding.etManualDomaine.text.toString().trim()

            val formattedFin = dateHelper.stringToExcelDate(fin)

            // Basic validation for the most critical fields
            if (matricule.isEmpty() || themeIdStr.isEmpty() || debut.isEmpty() || fin.isEmpty()) {
                Toast.makeText(requireContext(), "⚠️ Veuillez remplir tous les champs obligatoires (Matricule, Thème, Dates)", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val formation = FormationEntity(
                collaborateurMatricule = matricule,
                themeId = themeIdStr.toLongOrNull() ?: 0L,
                debut = debut,
                fin = fin,
                Formateur = formateur,
                dateAppreciation = if(formattedFin != null) dateHelper.getDateAppreciation(formattedFin).toString() else "",
                syncedToFirebase = false,
                entite = entite,
                categorie = categorie,
                division = division,
                convocation = convocation,
                presence = presence,
                session = session,
                jsp = jsp,
                type = type,
                domaine = domaine
            )

            viewModel.addManualFormation(formation)
        }
    }

    private fun observeImportState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.bilanState.collect { state ->
                when (state) {
                    is ImportState.Loading -> {
                        binding.progressBilan.visibility = View.VISIBLE
                        binding.tvBilanResult.visibility = View.GONE
                        binding.btnChargerBilan.isEnabled = false
                        binding.btnSaveManual.isEnabled = false
                    }
                    is ImportState.Success -> {
                        binding.progressBilan.visibility = View.GONE
                        binding.tvBilanResult.apply {
                            visibility = View.VISIBLE
                            setTextColor(0xFF00693E.toInt()) // OCP Dark Green
                            text = state.message
                        }
                        clearManualForm()
                        binding.btnChargerBilan.isEnabled = false
                        binding.btnSaveManual.isEnabled = true
                        pendingUri = null
                        viewModel.resetBilanState()
                    }
                    is ImportState.Error -> {
                        binding.progressBilan.visibility = View.GONE
                        binding.tvBilanResult.apply {
                            visibility = View.VISIBLE
                            setTextColor(0xFFD32F2F.toInt()) // Error Red
                            text = state.message
                        }
                        binding.btnChargerBilan.isEnabled = pendingUri != null
                        binding.btnSaveManual.isEnabled = true
                        viewModel.resetBilanState()
                    }
                    is ImportState.Idle -> {
                        binding.progressBilan.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun clearManualForm() {
        binding.etManualMatricule.text?.clear()
        binding.etManualThemeId.text?.clear()
        binding.etManualFormateur.text?.clear()
        binding.etManualDebut.text?.clear()
        binding.etManualFin.text?.clear()

        // Clear new fields
        binding.etManualEntite.text?.clear()
        binding.etManualCategorie.text?.clear()
        binding.etManualDivision.text?.clear()
        binding.etManualConvocation.text?.clear()
        binding.etManualPresence.text?.clear()
        binding.etManualSession.text?.clear()
        binding.etManualJsp.text?.clear()
        binding.etManualType.text?.clear()
        binding.etManualDomaine.text?.clear()

        // Collapse the form after success
        binding.layoutHeaderManualAdd.performClick()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}