package com.ocp.evalformation.ui.rh.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
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

        viewModel.pendingCount.observe(viewLifecycleOwner) { count ->
            binding.tvPendingCount.text = "$count invitation(s) en attente de réponse"
        }

        val invAdapter = InvitationsAdapter()
        binding.rvInvitations.layoutManager = LinearLayoutManager(requireContext())
        binding.rvInvitations.adapter = invAdapter

        viewModel.pendingInvitations.observe(viewLifecycleOwner) { invitations ->
            invAdapter.submitList(invitations)
        }

        binding.btnEnvoyerInvitation.setOnClickListener {
            val matricule = binding.etMatriculeCollab.text.toString().trim()
            val flmEmail  = binding.etFlmEmail.text.toString().trim()
            val flmNom    = binding.etFlmNom.text.toString().trim()

            if (matricule.isBlank() || flmEmail.isBlank() || flmNom.isBlank()) {
                Toast.makeText(requireContext(), "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.verifierMatricule(matricule) { collab ->
                if (collab == null) {
                    Toast.makeText(requireContext(), "❌ Matricule $matricule non trouvé.", Toast.LENGTH_SHORT).show()
                    return@verifierMatricule
                }
                viewModel.getFormationByMatricule(matricule) { formation ->
                    if (formation == null) {
                        Toast.makeText(requireContext(), "❌ Aucune formation pour $matricule.", Toast.LENGTH_SHORT).show()
                        return@getFormationByMatricule
                    }
                    viewModel.sendFormToFlm(collab, formation, flmEmail, flmNom)
                }
            }
        }

        lifecycleScope.launch {
            viewModel.invitationState.collect { state ->
                when (state) {
                    is RhViewModel.InvitationState.Idle    -> binding.progressInvitation.visibility = View.GONE
                    is RhViewModel.InvitationState.Sending -> binding.progressInvitation.visibility = View.VISIBLE
                    is RhViewModel.InvitationState.Sent -> {
                        binding.progressInvitation.visibility = View.GONE
                        Toast.makeText(requireContext(), "✅ Invitation envoyée à ${state.invitation.emailFlm}", Toast.LENGTH_LONG).show()
                        clearForm()
                    }
                    is RhViewModel.InvitationState.Error -> {
                        binding.progressInvitation.visibility = View.GONE
                        Toast.makeText(requireContext(), "❌ ${state.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun clearForm() {
        binding.etMatriculeCollab.text?.clear()
        binding.etFlmEmail.text?.clear()
        binding.etFlmNom.text?.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
