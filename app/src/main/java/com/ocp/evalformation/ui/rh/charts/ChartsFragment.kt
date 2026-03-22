package com.ocp.evalformation.ui.rh.charts

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.snackbar.Snackbar
import com.ocp.evalformation.R
import com.ocp.evalformation.databinding.FragmentChartsBinding
import com.ocp.evalformation.ui.rh.RhViewModel
import com.ocp.evalformation.ui.rh.evaluations.EvaluationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat
import java.util.Calendar

@AndroidEntryPoint
class ChartsFragment : Fragment() {

    private var _binding: FragmentChartsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EvaluationViewModel by navGraphViewModels(R.id.nav_graph_rh) {
        defaultViewModelProviderFactory
    }

    private val rhViewModel: RhViewModel by activityViewModels()

    private val decimalFormat = DecimalFormat("#.#")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChartsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupYearSpinner()
        setupRadarChart()
        setupPieChart()
        observeData()
        setupExportButton()
        viewModel.computeChartData(Calendar.getInstance().get(Calendar.YEAR))
        binding.fabExport.bringToFront()
        binding.fabExport.visibility = View.VISIBLE



    }

    private fun setupYearSpinner() {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val years = listOf("Toutes les années") + (currentYear downTo currentYear - 4).map { it.toString() }
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            years
        ).apply { setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        binding.spinnerYearFilter.adapter = adapter
        binding.spinnerYearFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, v: View?, pos: Int, id: Long) {
                val year = if (pos == 0) null else years[pos].toIntOrNull()
                viewModel.computeChartData(year)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupRadarChart() {
        binding.radarChart.apply {
            description.isEnabled = false
            setBackgroundColor(Color.WHITE)
            webLineWidth          = 1.5f
            webColor              = Color.parseColor("#E0E0E0")
            webLineWidthInner     = 1f
            webColorInner         = Color.parseColor("#E0E0E0")
            webAlpha              = 200
            legend.isEnabled      = false

            xAxis.apply {
                textSize       = 9f
                textColor      = Color.parseColor("#495057")
                xOffset        = 0f
                yOffset        = 0f
                valueFormatter = IndexAxisValueFormatter(listOf(
                    "Besoin",
                    "Impact",
                    "Application",
                    "Global"
                ))
            }

            yAxis.apply {
                axisMinimum = 0f
                axisMaximum = 4f
                setDrawLabels(false)
                setLabelCount(5, true)
            }

            setExtraOffsets(10f, 10f, 10f, 10f)
            minOffset = 0f
        }
    }

    private fun setupPieChart() {
        binding.pieChart.apply {
            description.isEnabled   = false
            isDrawHoleEnabled       = true
            holeRadius              = 45f
            transparentCircleRadius = 50f
            setHoleColor(Color.WHITE)
            setDrawCenterText(false)
            setEntryLabelTextSize(12f)
            setUsePercentValues(true)
            legend.isEnabled        = false
        }
    }

    private fun setupExportButton() {
        binding.fabExport.setOnClickListener {
            android.util.Log.d("Export", "Clicked")
            val formations       = viewModel.evaluations.value.mapNotNull { it.formation }
            val totalCollabs     = rhViewModel.totalCollaborateurs.value ?: 0
            val collabsWithForm  = rhViewModel.collaborateursWithFormation.value ?: 0
            val distinctThemes   = rhViewModel.distinctThemesCount.value ?: 0
            val totalJsp         = rhViewModel.totalJsp.value ?: 0.0
            val criteriaAverages = viewModel.criteriaAverages.value
            val satisfactionRate = viewModel.satisfactionRate.value

            android.util.Log.d("Export", "formations=${formations.size}")

            Thread {
                try {
                    ExcelExporter.export(
                        context          = requireContext(),
                        formations       = formations,
                        totalCollabs     = totalCollabs,
                        collabsWithForm  = collabsWithForm,
                        distinctThemes   = distinctThemes,
                        totalJsp         = totalJsp,
                        criteriaAverages = criteriaAverages,
                        satisfactionRate = satisfactionRate,
                        onSuccess        = { file1, file2 ->
                            android.util.Log.d("Export", "Success: ${file1.absolutePath}")
                            requireActivity().runOnUiThread {
                                Snackbar.make(binding.root, "✅ Exporté dans Téléchargements", Snackbar.LENGTH_LONG).show()
                            }
                        },
                        onError = { error ->
                            android.util.Log.e("Export", "Error: $error")
                            requireActivity().runOnUiThread {
                                Snackbar.make(binding.root, "❌ $error", Snackbar.LENGTH_LONG).show()
                            }
                        }
                    )
                } catch (e: Exception) {
                    android.util.Log.e("Export", "Exception: ${e.message}", e)
                    requireActivity().runOnUiThread {
                        Snackbar.make(binding.root, "❌ ${e.message}", Snackbar.LENGTH_LONG).show()
                    }
                }
            }.start()
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.evaluations.collect {
                val selected = binding.spinnerYearFilter.selectedItemPosition
                val years    = (0 until binding.spinnerYearFilter.adapter.count)
                    .map { binding.spinnerYearFilter.adapter.getItem(it).toString() }
                val year = if (selected == 0) null else years[selected].toIntOrNull()
                viewModel.computeChartData(year)
            }
        }

        // ── Radar chart ───────────────────────────────────────────
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.criteriaAverages.collect { data ->
                if (data == null) {
                    binding.radarChart.clear()
                    binding.radarChart.invalidate()
                    return@collect
                }

                val entries = listOf(
                    RadarEntry(data.satisfactionBesoin),
                    RadarEntry(data.impactPerformance),
                    RadarEntry(data.applicationConnaissances),
                    RadarEntry(data.satisfactionGlobale)
                )

                val dataSet = RadarDataSet(entries, "Moyenne des critères").apply {
                    color          = Color.parseColor("#1B5E20")
                    fillColor      = Color.parseColor("#4CAF50")
                    setDrawFilled(true)
                    fillAlpha      = 120
                    lineWidth      = 2.5f
                    valueTextSize  = 11f
                    valueTextColor = Color.parseColor("#1B5E20")
                    valueFormatter = object : ValueFormatter() {
                        override fun getPointLabel(entry: Entry?) =
                            decimalFormat.format(entry?.y ?: 0f)
                    }
                }

                binding.radarChart.data = RadarData(dataSet)
                binding.radarChart.animateXY(800, 800)
                binding.radarChart.invalidate()
            }
        }

        // ── Pie chart ─────────────────────────────────────────────
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.satisfactionRate.collect { data ->
                if (data == null) {
                    binding.pieChart.clear()
                    binding.pieChart.invalidate()
                    binding.tvLegendSatisfaisant.text   = "Satisfaisant: 0"
                    binding.tvLegendInsatisfaisant.text = "Insatisfaisant: 0"
                    return@collect
                }

                val entries = listOf(
                    PieEntry(data.positivePercent, "Satisfaisant"),
                    PieEntry(data.negativePercent, "Insatisfaisant")
                )

                val dataSet = PieDataSet(entries, "").apply {
                    colors        = listOf(
                        Color.parseColor("#4CAF50"),
                        Color.parseColor("#F44336")
                    )
                    valueTextSize  = 13f
                    valueTextColor = Color.WHITE
                    sliceSpace     = 3f
                    selectionShift = 6f
                    valueFormatter = object : ValueFormatter() {
                        override fun getPieLabel(value: Float, e: PieEntry?) =
                            "${decimalFormat.format(value)}%"
                    }
                }

                binding.pieChart.data = PieData(dataSet)
                binding.pieChart.animateY(1000, Easing.EaseInOutQuad)
                binding.pieChart.invalidate()

                binding.tvLegendSatisfaisant.text =
                    "Satisfaisant: ${data.positiveCount} (${decimalFormat.format(data.positivePercent)}%)"
                binding.tvLegendInsatisfaisant.text =
                    "Insatisfaisant: ${data.negativeCount} (${decimalFormat.format(data.negativePercent)}%)"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}