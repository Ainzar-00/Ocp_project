package com.ocp.evalformation.ui.rh.dashboard

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.ocp.evalformation.R
import com.ocp.evalformation.data.local.entity.InvitationStatus
import com.ocp.evalformation.databinding.FragmentInvitationsBinding
import com.ocp.evalformation.ui.rh.RhViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class InvitationsFragment : Fragment() {

    private var _binding: FragmentInvitationsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RhViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInvitationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Update invitation statuses on load
        viewModel.checkAndUpdateInvitationStatuses()

        // ── Adapter ────────────────────────────────────────────────────────────
        val invAdapter = InvitationsAdapter(
            onEnvoyer  = { item -> viewModel.sendFormToFlm(item) },
            onRenvoyer = { item -> viewModel.sendFormToFlm(item) }
        )

        binding.rvInvitations.layoutManager = LinearLayoutManager(requireContext())
        binding.rvInvitations.adapter = invAdapter

        // ── End-of-month banner ────────────────────────────────────────────────
        if (viewModel.isEndOfMonth) {
            binding.cardEndOfMonth.visibility = View.VISIBLE
            binding.tvEndOfMonthInfo.text =
                "⚠️ Fin du mois — toutes les invitations non expédiées sont affichées."
        } else {
            binding.cardEndOfMonth.visibility = View.GONE
        }

        // ── Observe filtered formations ────────────────────────────────────────
        viewModel.filteredFormations.observe(viewLifecycleOwner) { list ->
            invAdapter.submitList(list)

            val pendingCount = list.count { it.status == InvitationStatus.EN_ATTENTE }
            val totalCount   = list.size
            binding.tvPendingCount.text =
                "$pendingCount en attente • $totalCount affichée(s)"

            // Show empty state
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

        // ── Filter by service / statut ─────────────────────────────────────────
        binding.btnFilterService.setOnClickListener {
            showFilterDialog()
        }

        // ── Send All button ────────────────────────────────────────────────────
        binding.btnSendAll.setOnClickListener {
            val pendingCount = viewModel.filteredFormations.value
                ?.count { it.status == InvitationStatus.NON_EXPEDIEE } ?: 0

            if (pendingCount == 0) {
                Toast.makeText(requireContext(),
                    "Aucune invitation à envoyer.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            AlertDialog.Builder(requireContext())
                .setTitle("Envoi global")
                .setMessage("Envoyer $pendingCount invitation(s) non expédiée(s) ?")
                .setPositiveButton("Envoyer") { _, _ -> viewModel.sendAllInvitations() }
                .setNegativeButton("Annuler", null)
                .show()
        }

        // ── Observe invitation state ───────────────────────────────────────────
        lifecycleScope.launch {
            viewModel.invitationState.collect { state ->
                when (state) {
                    is RhViewModel.InvitationState.Idle -> {
                        binding.progressInvitation.visibility = View.GONE
                    }
                    is RhViewModel.InvitationState.Sending -> {
                        binding.progressInvitation.visibility = View.VISIBLE
                    }
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

    // ── Filter dialog ──────────────────────────────────────────────────────────
    private fun showFilterDialog() {
        val services = viewModel.allCollaborateurs.value
            ?.mapNotNull { it.service }
            ?.distinct()
            ?.sorted()
            ?.toMutableList()
            ?: mutableListOf()

        val statuts = listOf(
            "Tous les statuts",
            "Non expédiées",
            "En attente",
            "Répondues"
        )

        val items = arrayOf(
            "── Effacer les filtres ──",
            *statuts.toTypedArray(),
            "── Services ──",
            *services.toTypedArray()
        )

        AlertDialog.Builder(requireContext())
            .setTitle("Filtrer par")
            .setItems(items) { _, which ->
                when {
                    which == 0 -> viewModel.clearFilters()
                    which in 1..4 -> {
                        val statut = when (which) {
                            2    -> InvitationStatus.NON_EXPEDIEE
                            3    -> InvitationStatus.EN_ATTENTE
                            4    -> InvitationStatus.REPONDUE
                            else -> null
                        }
                        viewModel.setFilterStatut(statut)
                    }
                    which >= 6 -> {
                        val serviceIndex = which - 6
                        if (serviceIndex < services.size) {
                            viewModel.setFilterService(services[serviceIndex])
                        }
                    }
                }
            }
            .setNegativeButton("Fermer", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
