package com.ocp.evalformation.ui.rh.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ocp.evalformation.databinding.FragmentDashboardBinding
import com.ocp.evalformation.ui.rh.RhViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RhViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.allCollaborateurs.observe(viewLifecycleOwner) {
            binding.tvTotalCollabs.text = it.size.toString()
        }
        viewModel.allEvaluations.observe(viewLifecycleOwner) { evals ->
            binding.tvTotalEvals.text = evals.size.toString()
            binding.tvMoyenneSatisfaction.text = "—"
            binding.tvPendingInvitations.text = "—"
        }
        viewModel.allThemes.observe(viewLifecycleOwner) { themes ->
            binding.tvTotalThemes.text = themes.size.toString()
            binding.tvBestTheme.text = themes.firstOrNull()?.nom ?: "—"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
