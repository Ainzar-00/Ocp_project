package com.ocp.evalformation.ui.rh.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.lifecycleScope
import com.ocp.evalformation.databinding.FragmentDashboardBinding
import com.ocp.evalformation.ui.rh.RhViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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

        // Debug — log all values
        viewModel.totalCollaborateurs.observe(viewLifecycleOwner) {
            android.util.Log.d("Dashboard", "totalCollaborateurs=$it")
        }
        viewModel.collaborateursWithFormation.observe(viewLifecycleOwner) {
            android.util.Log.d("Dashboard", "collaborateursWithFormation=$it")
        }
        viewModel.distinctThemesCount.observe(viewLifecycleOwner) {
            android.util.Log.d("Dashboard", "distinctThemesCount=$it")
        }
        viewModel.totalEvaluations.observe(viewLifecycleOwner) {
            android.util.Log.d("Dashboard", "totalEvaluations=$it")
        }
        viewModel.totalJsp.observe(viewLifecycleOwner) {
            android.util.Log.d("Dashboard", "totalJsp=$it")
        }
        viewModel.mostRecurrentTheme.observe(viewLifecycleOwner) {
            android.util.Log.d("Dashboard", "mostRecurrentTheme=$it")
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val count = viewModel.repo.formationDao.count()
            android.util.Log.d("Dashboard", "formations in Room: $count")
        }

        viewLifecycleOwner.lifecycleScope.launch {
            val formations = viewModel.repo.formationDao.getAll()
            formations.forEach { f ->
                android.util.Log.d("Dashboard", "dateAppreciation='${f.dateAppreciation.trim()}'")
            }
        }


        // ── Taux de couverture ────────────────────────────────────
        val tauxMediator = MediatorLiveData<Pair<Int, Int>>()
        tauxMediator.addSource(viewModel.totalCollaborateurs) { total ->
            val withFormation = viewModel.collaborateursWithFormation.value ?: 0
            tauxMediator.value = Pair(total, withFormation)
        }

        tauxMediator.addSource(viewModel.collaborateursWithFormation) { withFormation ->
            val total = viewModel.totalCollaborateurs.value ?: 0
            tauxMediator.value = Pair(total, withFormation)
        }
        tauxMediator.observe(viewLifecycleOwner) { (total, withFormation) ->
            val pct = if (total > 0) (withFormation * 100) / total else 0
            binding.tvTauxCouverturePct.text   = "$pct%"
            binding.tvTauxCouvertureRatio.text = "$withFormation/$total"
        }

        // ── Thèmes réalisés ───────────────────────────────────────
        viewModel.distinctThemesCount.observe(viewLifecycleOwner) { count ->
            binding.tvThemesRealises.text = count?.toString() ?: "—"
        }

        // ── Évaluations ───────────────────────────────────────────
        viewModel.totalEvaluations.observe(viewLifecycleOwner) { count ->
            binding.tvTotalEvals.text = count?.toString() ?: "—"
        }

        // ── En attente FLM ────────────────────────────────────────
        viewModel.enAttenteCount.observe(viewLifecycleOwner) { count ->
            binding.tvPendingInvitations.text = count?.toString() ?: "—"
        }

        // ── Thème le plus récurrent ───────────────────────────────
        viewModel.mostRecurrentTheme.observe(viewLifecycleOwner) { themeName ->
            binding.tvThemePlusRecent.text = themeName ?: "—"
        }

        // ── JSP ───────────────────────────────────────────────────
        viewModel.totalJsp.observe(viewLifecycleOwner) { jsp ->
            binding.tvJsp.text = jsp?.let {
                if (it % 1.0 == 0.0) it.toInt().toString()
                else String.format("%.1f", it)
            } ?: "—"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}