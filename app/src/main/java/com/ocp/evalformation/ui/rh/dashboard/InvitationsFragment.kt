package com.ocp.evalformation.ui.rh.dashboard

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ocp.evalformation.com.ocp.evalformation.BackgroundWork.AppreciationDateWorker
import com.ocp.evalformation.data.local.entity.InvitationStatus
import com.ocp.evalformation.databinding.FragmentInvitationsBinding
import com.ocp.evalformation.ui.rh.RhViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InvitationsFragment : Fragment() {

    private lateinit var prefs: SharedPreferences

    private var _binding: FragmentInvitationsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RhViewModel by activityViewModels()

    private val prefsListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPrefs, key ->
        if (key == "pending_formation_ids") {
            viewModel.refreshPendingIds(sharedPrefs)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInvitationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.checkAndUpdateInvitationStatuses()

        prefs=context?.getSharedPreferences("worker_prefs", Context.MODE_PRIVATE)!!

        viewModel.refreshPendingIds(prefs)

        viewModel.pendingIds.observe(viewLifecycleOwner) { pendingIds ->
            if (pendingIds.isNotEmpty()) {
                binding.cardAppreciationDate.visibility = View.VISIBLE
                binding.tvAppreciationInfo.text =
                    "📋 ${pendingIds.size} formation(s) arrivent à date d'appréciation aujourd'hui."

                binding.btnSendAllAppreciation.setOnClickListener {
                    viewModel.sendAllByFormationIds(pendingIds)
                    NotificationManagerCompat.from(requireContext())
                        .cancel(AppreciationDateWorker.NOTIFICATION_ID)
                    prefs.edit().remove("pending_formation_ids").apply()
                }
            } else {
                binding.cardAppreciationDate.visibility = View.GONE
            }
        }


        // ── Adapter ────────────────────────────────────────────────────────────
        val invAdapter = InvitationsAdapter(
            onEnvoyer  = { item -> viewModel.sendFormToFlm(item) },
            onRenvoyer = { item -> viewModel.sendFormToFlm(item) }
        )
        binding.rvInvitations.layoutManager = LinearLayoutManager(requireContext())
        binding.rvInvitations.adapter = invAdapter

        // ── Observe themes for adapter ─────────────────────────────────────────
        viewModel.allThemes.observe(viewLifecycleOwner) { themes ->
            invAdapter.submitThemes(themes.associate { it.id to it.nom })
        }



        // ── Observe filtered formations ────────────────────────────────────────
        viewModel.filteredFormations.observe(viewLifecycleOwner) { list ->
            invAdapter.submitList(list)
            val pendingCount = list.count { it.status == InvitationStatus.EN_ATTENTE }
            binding.tvPendingCount.text = "$pendingCount en attente • ${list.size} affichée(s)"
            binding.tvEmptyState.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
        }

        // ── Search ─────────────────────────────────────────────────────────────
        binding.etSearchMatricule.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setSearch(s?.toString() ?: "")
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // ── Filter chips ───────────────────────────────────────────────────────
        binding.chipFilterStatut.setOnClickListener  { showStatutDialog()  }
        binding.chipFilterTheme.setOnClickListener   { showThemeDialog()   }
        binding.chipFilterService.setOnClickListener { showServiceDialog() }
        binding.chipClearFilters.setOnClickListener  {
            viewModel.clearFilters()
            resetChips()
        }

        // ── Observe filter state to update chip labels ─────────────────────────
        viewModel.filterStatut.observe(viewLifecycleOwner) { statut ->
            binding.chipFilterStatut.text = when (statut) {
                InvitationStatus.NON_EXPEDIEE -> "Statut: Non expédiée"
                InvitationStatus.EN_ATTENTE   -> "Statut: En attente"
                InvitationStatus.REPONDUE     -> "Statut: Répondue"
                null                          -> "Statut"
            }
            binding.chipFilterStatut.isChecked = statut != null
        }

        viewModel.filterTheme.observe(viewLifecycleOwner) { theme ->
            binding.chipFilterTheme.text = if (theme != null) "Thème: $theme" else "Thème"
            binding.chipFilterTheme.isChecked = theme != null
        }

        viewModel.filterService.observe(viewLifecycleOwner) { service ->
            binding.chipFilterService.text = if (service != null) "Service: $service" else "Service"
            binding.chipFilterService.isChecked = service != null
        }



        // ── Invitation state ───────────────────────────────────────────────────
        lifecycleScope.launch {
            viewModel.invitationState.collect { state ->
                when (state) {
                    is RhViewModel.InvitationState.Idle    -> binding.progressInvitation.visibility = View.GONE
                    is RhViewModel.InvitationState.Sending -> binding.progressInvitation.visibility = View.VISIBLE
                    is RhViewModel.InvitationState.Sent -> {
                        binding.progressInvitation.visibility = View.GONE
                        Toast.makeText(requireContext(),
                            "✅ Invitation envoyée à ${state.invitation.emailFlm}",
                            Toast.LENGTH_LONG).show()
                    }
                    is RhViewModel.InvitationState.SentAll -> {
                        binding.progressInvitation.visibility = View.GONE
                        Toast.makeText(requireContext(),
                            "✅ ${state.count} invitation(s) envoyée(s).",
                            Toast.LENGTH_LONG).show()
                    }
                    is RhViewModel.InvitationState.Error -> {
                        binding.progressInvitation.visibility = View.GONE
                        Toast.makeText(requireContext(),
                            "❌ ${state.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    // ── Statut dialog ──────────────────────────────────────────────────────────
    private fun showStatutDialog() {
        val options = arrayOf("Non expédiée", "En attente", "Répondue")
        val statuts = arrayOf(
            InvitationStatus.NON_EXPEDIEE,
            InvitationStatus.EN_ATTENTE,
            InvitationStatus.REPONDUE
        )
        AlertDialog.Builder(requireContext())
            .setTitle("Filtrer par statut")
            .setItems(options) { _, which ->
                viewModel.setFilterStatut(statuts[which])
            }
            .setNegativeButton("Annuler", null)
            .show()
    }

    private fun updatePendingFormationCard(prefs: SharedPreferences) {
        val pendingIds = prefs.getString("pending_formation_ids", "")
            ?.split(",")
            ?.mapNotNull { it.toLongOrNull() }
            ?: emptyList()

        if (pendingIds.isNotEmpty()) {
            binding.cardAppreciationDate.visibility = View.VISIBLE
            binding.tvAppreciationInfo.text =
                "📋 ${pendingIds.size} formation(s) arrivent à date d'appréciation aujourd'hui."

            binding.btnSendAllAppreciation.setOnClickListener {
                viewModel.sendAllByFormationIds(pendingIds)

                NotificationManagerCompat.from(requireContext())
                    .cancel(AppreciationDateWorker.NOTIFICATION_ID)

                prefs.edit().remove("pending_formation_ids").apply()
            }
        } else {
            binding.cardAppreciationDate.visibility = View.GONE
        }
    }

    // ── Theme dialog ───────────────────────────────────────────────────────────
    private fun showThemeDialog() {
        val themes = viewModel.allThemes.value
            ?.map { it.nom }
            ?.sorted()
            ?.toTypedArray()
            ?: arrayOf()

        if (themes.isEmpty()) {
            Toast.makeText(requireContext(), "Aucun thème disponible.", Toast.LENGTH_SHORT).show()
            return
        }

        AlertDialog.Builder(requireContext())
            .setTitle("Filtrer par thème")
            .setItems(themes) { _, which ->
                viewModel.setFilterTheme(themes[which])
            }
            .setNegativeButton("Annuler", null)
            .show()
    }

    override fun onStart() {
        super.onStart()
        prefs.registerOnSharedPreferenceChangeListener(prefsListener)
    }

    override fun onStop() {
        super.onStop()
        prefs.unregisterOnSharedPreferenceChangeListener(prefsListener)
    }

    // ── Service dialog ─────────────────────────────────────────────────────────
    private fun showServiceDialog() {
        Log.d("Collab Service0",viewModel.allCollaborateurs.value.toString())
        viewModel.allCollaborateurs.observe(viewLifecycleOwner){collaborateurEntities->
            val services = collaborateurEntities
                ?.mapNotNull { it.service }
                ?.distinct()
                ?.sorted()
                ?.toTypedArray()
                ?: arrayOf()

            Log.d("Collab Service1",viewModel.allCollaborateurs.value.toString())

            if (services.isEmpty()) {
                Toast.makeText(requireContext(), "Aucun service disponible.", Toast.LENGTH_SHORT).show()
                return@observe
            }

            AlertDialog.Builder(requireContext())
                .setTitle("Filtrer par service")
                .setItems(services) { _, which ->
                    viewModel.setFilterService(services[which])
                }
                .setNegativeButton("Annuler", null)
                .show()
        }





    }

    // ── Reset chips ────────────────────────────────────────────────────────────
    private fun resetChips() {
        binding.chipFilterStatut.text    = "Statut"
        binding.chipFilterTheme.text     = "Thème"
        binding.chipFilterService.text   = "Service"
        binding.chipFilterStatut.isChecked  = false
        binding.chipFilterTheme.isChecked   = false
        binding.chipFilterService.isChecked = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

