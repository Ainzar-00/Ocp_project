package com.ocp.evalformation.ui.rh.charts

import android.content.Context
import android.os.Environment
import com.ocp.evalformation.data.local.entity.CriteriaAverages
import com.ocp.evalformation.data.local.entity.FormationEntity
import com.ocp.evalformation.data.local.entity.SatisfactionRate
import com.ocp.evalformation.ui.rh.RhViewModel
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object ExcelExporter {

    private val decimalFormat = DecimalFormat("#.##")
    private val dateFormat    = SimpleDateFormat("dd-MM-yyyy_HH-mm", Locale.FRENCH)

    // ── Main export function ──────────────────────────────────────
    fun export(
        context         : Context,
        formations      : List<FormationEntity>,
        totalCollabs    : Int,
        collabsWithForm : Int,
        distinctThemes  : Int,
        totalJsp        : Double,
        criteriaAverages: CriteriaAverages?,
        satisfactionRate: SatisfactionRate?,
        onSuccess       : (File, File) -> Unit,
        onError         : (String) -> Unit
    ) {
        try {
            val timestamp = dateFormat.format(Date())

            val file1 = createAnalyticsFile(
                context, timestamp, formations, totalCollabs, collabsWithForm,
                distinctThemes, totalJsp, criteriaAverages, satisfactionRate
            )

            val file2 = createSessionsFile(context, timestamp, formations)

            onSuccess(file1, file2)
        } catch (e: Exception) {
            onError(e.message ?: "Erreur lors de l'export")
        }
    }

    // ── File 1: Analytics ─────────────────────────────────────────
    private fun createAnalyticsFile(
        context         : Context,
        timestamp       : String,
        formations      : List<FormationEntity>,
        totalCollabs    : Int,
        collabsWithForm : Int,
        distinctThemes  : Int,
        totalJsp        : Double,
        criteriaAverages: CriteriaAverages?,
        satisfactionRate: SatisfactionRate?
    ): File {
        val workbook = XSSFWorkbook()
        val sheet    = workbook.createSheet("Rapport Analytique")

        // ── Styles ────────────────────────────────────────────────
        val headerStyle = workbook.createCellStyle().apply {
            fillForegroundColor = IndexedColors.DARK_GREEN.index
            fillPattern         = FillPatternType.SOLID_FOREGROUND
            alignment           = HorizontalAlignment.CENTER
            val font = workbook.createFont().apply {
                bold      = true
                color     = IndexedColors.WHITE.index
                fontHeightInPoints = 12
            }
            setFont(font)
        }

        val titleStyle = workbook.createCellStyle().apply {
            fillForegroundColor = IndexedColors.LIGHT_GREEN.index
            fillPattern         = FillPatternType.SOLID_FOREGROUND
            val font = workbook.createFont().apply {
                bold = true
                fontHeightInPoints = 11
            }
            setFont(font)
        }

        val valueStyle = workbook.createCellStyle().apply {
            alignment = HorizontalAlignment.CENTER
        }

        var rowIndex = 0

        // ── Section 1: KPIs généraux ──────────────────────────────
        rowIndex = addSectionHeader(sheet, rowIndex, "Indicateurs Généraux", headerStyle)

        val tauxCouverture = if (totalCollabs > 0)
            (collabsWithForm * 100.0 / totalCollabs) else 0.0

        rowIndex = addRow(sheet, rowIndex, titleStyle, valueStyle,
            "Taux de Couverture", "${decimalFormat.format(tauxCouverture)}% ($collabsWithForm/$totalCollabs)")
        rowIndex = addRow(sheet, rowIndex, titleStyle, valueStyle,
            "JSP Total (jours)", decimalFormat.format(totalJsp))
        rowIndex = addRow(sheet, rowIndex, titleStyle, valueStyle,
            "Nombre de Thèmes Réalisés", distinctThemes.toString())

        rowIndex++ // empty row

        // ── Section 2: Thèmes par domaine ─────────────────────────
        rowIndex = addSectionHeader(sheet, rowIndex, "Nombre de Thèmes par Domaine", headerStyle)

        val themesByDomain = formations.groupBy { it.domaine }
            .mapValues { (_, list) -> list.map { it.themeId }.distinct().size }
            .toSortedMap()

        val domainHeaderRow = sheet.createRow(rowIndex++)
        createCell(domainHeaderRow, 0, "Domaine", titleStyle)
        createCell(domainHeaderRow, 1, "Nombre de Thèmes", titleStyle)

        themesByDomain.forEach { (domain, count) ->
            val row = sheet.createRow(rowIndex++)
            createCell(row, 0, domain.ifBlank { "Non renseigné" }, null)
            createCell(row, 1, count.toString(), valueStyle)
        }

        rowIndex++ // empty row

        // ── Section 3: Performance par critère ────────────────────
        rowIndex = addSectionHeader(sheet, rowIndex, "Performance par Critère (Échelle 1-4)", headerStyle)

        if (criteriaAverages != null) {
            val critHeaderRow = sheet.createRow(rowIndex++)
            createCell(critHeaderRow, 0, "Critère", titleStyle)
            createCell(critHeaderRow, 1, "Moyenne", titleStyle)

            listOf(
                "Satisfaction du Besoin"         to criteriaAverages.satisfactionBesoin,
                "Impact sur la Performance"       to criteriaAverages.impactPerformance,
                "Application des Connaissances"   to criteriaAverages.applicationConnaissances,
                "Satisfaction Globale"            to criteriaAverages.satisfactionGlobale
            ).forEach { (label, value) ->
                val row = sheet.createRow(rowIndex++)
                createCell(row, 0, label, null)
                createCell(row, 1, decimalFormat.format(value), valueStyle)
            }
        } else {
            addRow(sheet, rowIndex++, null, null, "Aucune donnée disponible", "")
        }

        rowIndex++ // empty row

        // ── Section 4: Taux de satisfaction ──────────────────────
        rowIndex = addSectionHeader(sheet, rowIndex, "Taux de Satisfaction", headerStyle)

        if (satisfactionRate != null) {
            val satHeaderRow = sheet.createRow(rowIndex++)
            createCell(satHeaderRow, 0, "Catégorie", titleStyle)
            createCell(satHeaderRow, 1, "Nombre", titleStyle)
            createCell(satHeaderRow, 2, "Pourcentage", titleStyle)

            val rowSat = sheet.createRow(rowIndex++)
            createCell(rowSat, 0, "Satisfaisant (score 3-4)", null)
            createCell(rowSat, 1, satisfactionRate.positiveCount.toString(), valueStyle)
            createCell(rowSat, 2, "${decimalFormat.format(satisfactionRate.positivePercent)}%", valueStyle)

            val rowUnsat = sheet.createRow(rowIndex++)
            createCell(rowUnsat, 0, "Insatisfaisant (score 1-2)", null)
            createCell(rowUnsat, 1, satisfactionRate.negativeCount.toString(), valueStyle)
            createCell(rowUnsat, 2, "${decimalFormat.format(satisfactionRate.negativePercent)}%", valueStyle)

            val rowTotal = sheet.createRow(rowIndex++)
            createCell(rowTotal, 0, "Total", titleStyle)
            createCell(rowTotal, 1, satisfactionRate.total.toString(), titleStyle)
            createCell(rowTotal, 2, "100%", titleStyle)
        } else {
            addRow(sheet, rowIndex++, null, null, "Aucune donnée disponible", "")
        }

        // Auto-size columns
        for (i in 0..3) sheet.setColumnWidth(i, 8000)

        return saveWorkbook(workbook, context, "Rapport_Analytique_$timestamp.xlsx")
    }

    // ── File 2: Sessions ──────────────────────────────────────────
    private fun createSessionsFile(
        context   : Context,
        timestamp : String,
        formations: List<FormationEntity>
    ): File {
        val workbook = XSSFWorkbook()
        val sheet    = workbook.createSheet("Sessions")

        val headerStyle = workbook.createCellStyle().apply {
            fillForegroundColor = IndexedColors.DARK_GREEN.index
            fillPattern         = FillPatternType.SOLID_FOREGROUND
            alignment           = HorizontalAlignment.CENTER
            val font = workbook.createFont().apply {
                bold  = true
                color = IndexedColors.WHITE.index
                fontHeightInPoints = 12
            }
            setFont(font)
        }

        // Header row
        val headerRow = sheet.createRow(0)
        createCell(headerRow, 0, "Sessions", headerStyle)

        // Unique sessions
        val uniqueSessions = formations
            .map { it.session.trim() }
            .filter { it.isNotBlank() }
            .distinct()
            .sorted()

        uniqueSessions.forEachIndexed { index, session ->
            val row = sheet.createRow(index + 1)
            createCell(row, 0, session, null)
        }

        sheet.setColumnWidth(0, 10000)

        return saveWorkbook(workbook, context, "Sessions_$timestamp.xlsx")
    }

    // ── Helpers ───────────────────────────────────────────────────
    private fun addSectionHeader(
        sheet    : Sheet,
        rowIndex : Int,
        title    : String,
        style    : CellStyle
    ): Int {
        val row = sheet.createRow(rowIndex)
        val cell = row.createCell(0)
        cell.setCellValue(title)
        cell.cellStyle = style
        sheet.addMergedRegion(CellRangeAddress(rowIndex, rowIndex, 0, 3))
        return rowIndex + 1
    }

    private fun addRow(
        sheet      : Sheet,
        rowIndex   : Int,
        labelStyle : CellStyle?,
        valueStyle : CellStyle?,
        label      : String,
        value      : String
    ): Int {
        val row = sheet.createRow(rowIndex)
        createCell(row, 0, label, labelStyle)
        createCell(row, 1, value, valueStyle)
        return rowIndex + 1
    }

    private fun createCell(row: Row, col: Int, value: String, style: CellStyle?) {
        val cell = row.createCell(col)
        cell.setCellValue(value)
        style?.let { cell.cellStyle = it }
    }

    private fun saveWorkbook(workbook: XSSFWorkbook, context: Context, filename: String): File {
        val dir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            ?: context.filesDir
        dir.mkdirs()
        val file = File(dir, filename)
        FileOutputStream(file).use { workbook.write(it) }
        workbook.close()
        return file
    }
}