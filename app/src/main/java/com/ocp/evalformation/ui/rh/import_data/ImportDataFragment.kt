package com.ocp.evalformation.ui.rh.import_data

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.ocp.evalformation.databinding.FragmentImportDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImportDataFragment : Fragment() {

    private var _binding: FragmentImportDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImportDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabs = listOf(
            "👥 Collaborateurs" to ImportCollaborateursFragment(),
            "🧑‍💼 FLMs"          to ImportFlmFragment(),
            "📚 Thèmes"         to ImportThemesFragment(),
            "📋 Bilan FC"       to ImportBilanFcFragment()
        )

        binding.viewpagerImport.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = tabs.size
            override fun createFragment(position: Int) = tabs[position].second
        }

        TabLayoutMediator(binding.tabLayoutImport, binding.viewpagerImport) { tab, position ->
            tab.text = tabs[position].first
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
