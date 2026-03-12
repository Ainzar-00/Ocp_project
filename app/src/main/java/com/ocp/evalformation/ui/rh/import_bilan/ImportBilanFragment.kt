package com.ocp.evalformation.ui.rh.import_bilan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ocp.evalformation.databinding.FragmentImportBilanBinding
import com.ocp.evalformation.ui.rh.RhViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImportBilanFragment : Fragment() {

    private var _binding: FragmentImportBilanBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RhViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImportBilanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Import will be reimplemented in next phase
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
