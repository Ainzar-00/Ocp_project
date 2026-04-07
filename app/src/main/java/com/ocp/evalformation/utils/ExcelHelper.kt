package com.ocp.evalformation.ui.rh.charts

import android.content.Context
import android.os.Environment
import com.ocp.evalformation.data.local.entity.CriteriaAverages
import com.ocp.evalformation.data.local.entity.EvaluationEntity
import com.ocp.evalformation.data.local.entity.FormationEntity
import com.ocp.evalformation.data.local.entity.SatisfactionRate
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

    // ─────────────────────────────────────────────────────────────────────────
    // Main entry point
    // ─────────────────────────────────────────────────────────────────────────

    fun export(
        context         : Context,
        formations      : List<FormationEntity>,
        evaluations     : List<EvaluationEntity>,
        totalCollabs    : Int,
        collabsWithForm : Int,
        distinctThemes  : Int,
        totalJsp        : Double,
        criteriaAverages: CriteriaAverages?,
        satisfactionRate: SatisfactionRate?,
        exportMode      : ExportMode = ExportMode.GLOBALE,
        themeFilter     : String?    = null,
        onSuccess       : (File, File, File) -> Unit,
        onError         : (String) -> Unit
    ) {
        try {
            val timestamp = dateFormat.format(Date())

            when (exportMode) {

                // ── All data, no filter, original 3-file structure ────
                ExportMode.GLOBALE -> {
                    val file1 = createAnalyticsFile(
                        context, timestamp, formations, totalCollabs, collabsWithForm,
                        distinctThemes, totalJsp, criteriaAverages, satisfactionRate,
                        includeThemesByDomain = true
                    )
                    val file2 = createSessionsFile(context, timestamp, formations)
                    val file3 = createSuggestionsFile(context, timestamp, evaluations)
                    onSuccess(file1, file2, file3)
                }

                // ── One sheet per theme, per-theme stats ──────────────
                ExportMode.ALL_THEMES -> {
                    val file1 = createAnalyticsFileAllThemes(
                        context, timestamp, formations, evaluations,
                        totalCollabs, collabsWithForm, distinctThemes, totalJsp,
                        criteriaAverages, satisfactionRate
                    )
                    val file2 = createSessionsFile(context, timestamp, formations)
                    val file3 = createSuggestionsFileByTheme(context, timestamp, evaluations)
                    onSuccess(file1, file2, file3)
                }

                // ── Filtered to one theme, same structure as GLOBALE ──
                ExportMode.ONE_THEME -> {
                    val filteredEvals = if (themeFilter != null)
                        evaluations.filter { it.intituleAction.trim() == themeFilter.trim() }
                    else evaluations

                    val filteredFormationIds = filteredEvals.map { it.formationId }.toSet()
                    val filteredFormations   = formations.filter { it.id in filteredFormationIds }

                    val themeCollabsWithForm = filteredFormations
                        .map { it.collaborateurMatricule }.distinct().size
                    val themeDistinctThemes  = filteredFormations
                        .map { it.themeId }.distinct().size
                    val themeTotalJsp        = filteredFormations
                        .sumOf { it.jsp.toIntOrNull() ?: 0 }.toDouble()

                    val themeCriteria     = computeCriteriaAverages(filteredEvals)
                    val themeSatisfaction = computeSatisfactionRate(filteredEvals)

                    val safeThemeName = themeFilter
                        ?.replace(Regex("[\\\\/:*?\"<>|]"), "_")
                        ?.take(30)
                        ?: "Theme"

                    val file1 = createAnalyticsFile(
                        context, timestamp, filteredFormations, totalCollabs,
                        themeCollabsWithForm, themeDistinctThemes, themeTotalJsp,
                        themeCriteria, themeSatisfaction,
                        includeThemesByDomain = false,   // ← removed for ONE_THEME
                        filenameSuffix = safeThemeName
                    )
                    val file2 = createSessionsFile(
                        context, timestamp, filteredFormations,
                        filenameSuffix = safeThemeName
                    )
                    val file3 = createSuggestionsFile(
                        context, timestamp, filteredEvals,
                        filenameSuffix = safeThemeName
                    )
                    onSuccess(file1, file2, file3)
                }
            }

        } catch (e: Exception) {
            onError(e.message ?: "Erreur lors de l'export")
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Stat helpers
    // ─────────────────────────────────────────────────────────────────────────

    private fun computeCriteriaAverages(evals: List<EvaluationEntity>): CriteriaAverages? {
        if (evals.isEmpty()) return null
        val n = evals.size.toFloat()
        return CriteriaAverages(
            satisfactionBesoin       = evals.sumOf { it.critieres.satisfactionBesoin.toDouble() }.toFloat() / n,
            impactPerformance        = evals.sumOf { it.critieres.impactPerformance.toDouble() }.toFloat() / n,
            applicationConnaissances = evals.sumOf { it.critieres.applicationConnaissances.toDouble() }.toFloat() / n,
            satisfactionGlobale      = evals.sumOf { it.critieres.satisfactionGlobale.toDouble() }.toFloat() / n
        )
    }

    private fun computeSatisfactionRate(evals: List<EvaluationEntity>): SatisfactionRate? {
        if (evals.isEmpty()) return null
        val total    = evals.size
        val positive = evals.count { it.critieres.satisfactionGlobale >= 3 }
        val negative = total - positive
        return SatisfactionRate(
            positiveCount   = positive,
            negativeCount   = negative,
            total           = total,
            positivePercent = if (total > 0) positive * 100f / total else 0f,
            negativePercent = if (total > 0) negative * 100f / total else 0f
        )
    }

    // ─────────────────────────────────────────────────────────────────────────
    // File 1-A : Analytics — GLOBALE / ONE_THEME
    // includeThemesByDomain = true  → GLOBALE (full report)
    // includeThemesByDomain = false → ONE_THEME (stripped down)
    // ─────────────────────────────────────────────────────────────────────────

    private fun createAnalyticsFile(
        context              : Context,
        timestamp            : String,
        formations           : List<FormationEntity>,
        totalCollabs         : Int,
        collabsWithForm      : Int,
        distinctThemes       : Int,
        totalJsp             : Double,
        criteriaAverages     : CriteriaAverages?,
        satisfactionRate     : SatisfactionRate?,
        includeThemesByDomain: Boolean,
        filenameSuffix       : String = ""
    ): File {
        val workbook = XSSFWorkbook()
        val sheet    = workbook.createSheet("Rapport Analytique")
        val (headerStyle, titleStyle, valueStyle) = buildAnalyticsStyles(workbook)

        var rowIndex = 0

        // ── Indicateurs généraux ──────────────────────────────────
        rowIndex = addSectionHeader(sheet, rowIndex, "Indicateurs Généraux", headerStyle)
        val tauxCouverture = if (totalCollabs > 0) (collabsWithForm * 100.0 / totalCollabs) else 0.0
        rowIndex = addRow(sheet, rowIndex, titleStyle, valueStyle,
            "Taux de Couverture", "${decimalFormat.format(tauxCouverture)}% ($collabsWithForm/$totalCollabs)")
        rowIndex = addRow(sheet, rowIndex, titleStyle, valueStyle,
            "JSP Total (jours)", decimalFormat.format(totalJsp))
        rowIndex = addRow(sheet, rowIndex, titleStyle, valueStyle,
            "Nombre de Thèmes Réalisés", distinctThemes.toString())
        rowIndex++

        // ── Thèmes par domaine (GLOBALE only) ─────────────────────
        if (includeThemesByDomain) {
            rowIndex = addSectionHeader(sheet, rowIndex, "Nombre de Thèmes par Domaine", headerStyle)

            val themesByDomain = formations
                .groupBy { it.domaine }
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
            rowIndex++
        }

        // ── Performance par critère ───────────────────────────────
        rowIndex = addSectionHeader(sheet, rowIndex, "Performance par Critère (Échelle 1-4)", headerStyle)
        if (criteriaAverages != null) {
            val critHeaderRow = sheet.createRow(rowIndex++)
            createCell(critHeaderRow, 0, "Critère", titleStyle)
            createCell(critHeaderRow, 1, "Moyenne", titleStyle)
            listOf(
                "Satisfaction du Besoin"       to criteriaAverages.satisfactionBesoin,
                "Impact sur la Performance"     to criteriaAverages.impactPerformance,
                "Application des Connaissances" to criteriaAverages.applicationConnaissances,
                "Satisfaction Globale"          to criteriaAverages.satisfactionGlobale
            ).forEach { (label, value) ->
                val row = sheet.createRow(rowIndex++)
                createCell(row, 0, label, null)
                createCell(row, 1, decimalFormat.format(value), valueStyle)
            }
        } else {
            addRow(sheet, rowIndex++, null, null, "Aucune donnée disponible", "")
        }
        rowIndex++

        // ── Taux de satisfaction ──────────────────────────────────
        rowIndex = addSectionHeader(sheet, rowIndex, "Taux de Satisfaction", headerStyle)
        rowIndex = writeSatisfactionSection(sheet, rowIndex, satisfactionRate, titleStyle, valueStyle)

        for (i in 0..3) sheet.setColumnWidth(i, 8000)

        val suffix = if (filenameSuffix.isNotBlank()) "_$filenameSuffix" else ""
        return saveWorkbook(workbook, context, "Rapport_Analytique${suffix}_$timestamp.xlsx")
    }

    // ─────────────────────────────────────────────────────────────────────────
    // File 1-B : Analytics — ALL_THEMES
    // Global indicators + one section per theme (criteria + satisfaction)
    // No "Thèmes par Domaine" section.
    // ─────────────────────────────────────────────────────────────────────────

    private fun createAnalyticsFileAllThemes(
        context         : Context,
        timestamp       : String,
        formations      : List<FormationEntity>,
        evaluations     : List<EvaluationEntity>,
        totalCollabs    : Int,
        collabsWithForm : Int,
        distinctThemes  : Int,
        totalJsp        : Double,
        criteriaAverages: CriteriaAverages?,
        satisfactionRate: SatisfactionRate?
    ): File {
        val workbook = XSSFWorkbook()
        val sheet    = workbook.createSheet("Rapport Analytique")
        val (headerStyle, titleStyle, valueStyle) = buildAnalyticsStyles(workbook)

        // Teal-ish color to distinguish theme sub-headers from global headers
        val themeSubHeaderStyle = workbook.createCellStyle().apply {
            fillForegroundColor = IndexedColors.SEA_GREEN.index
            fillPattern         = FillPatternType.SOLID_FOREGROUND
            alignment           = HorizontalAlignment.CENTER
            setFont(workbook.createFont().apply {
                bold               = true
                color              = IndexedColors.WHITE.index
                fontHeightInPoints = 12
            })
        }

        var rowIndex = 0

        // ── Global indicators ─────────────────────────────────────
        rowIndex = addSectionHeader(sheet, rowIndex, "Indicateurs Généraux", headerStyle)
        val tauxCouverture = if (totalCollabs > 0) (collabsWithForm * 100.0 / totalCollabs) else 0.0
        rowIndex = addRow(sheet, rowIndex, titleStyle, valueStyle,
            "Taux de Couverture", "${decimalFormat.format(tauxCouverture)}% ($collabsWithForm/$totalCollabs)")
        rowIndex = addRow(sheet, rowIndex, titleStyle, valueStyle,
            "JSP Total (jours)", decimalFormat.format(totalJsp))
        rowIndex = addRow(sheet, rowIndex, titleStyle, valueStyle,
            "Nombre de Thèmes Réalisés", distinctThemes.toString())
        rowIndex++

        // ── Global performance / satisfaction ─────────────────────
        rowIndex = addSectionHeader(sheet, rowIndex, "Performance Globale par Critère (Échelle 1-4)", headerStyle)
        if (criteriaAverages != null) {
            val critHeaderRow = sheet.createRow(rowIndex++)
            createCell(critHeaderRow, 0, "Critère", titleStyle)
            createCell(critHeaderRow, 1, "Moyenne", titleStyle)
            listOf(
                "Satisfaction du Besoin"       to criteriaAverages.satisfactionBesoin,
                "Impact sur la Performance"     to criteriaAverages.impactPerformance,
                "Application des Connaissances" to criteriaAverages.applicationConnaissances,
                "Satisfaction Globale"          to criteriaAverages.satisfactionGlobale
            ).forEach { (label, value) ->
                val row = sheet.createRow(rowIndex++)
                createCell(row, 0, label, null)
                createCell(row, 1, decimalFormat.format(value), valueStyle)
            }
        } else {
            addRow(sheet, rowIndex++, null, null, "Aucune donnée disponible", "")
        }
        rowIndex++

        rowIndex = addSectionHeader(sheet, rowIndex, "Taux de Satisfaction Global", headerStyle)
        rowIndex = writeSatisfactionSection(sheet, rowIndex, satisfactionRate, titleStyle, valueStyle)
        rowIndex++

        // ── Per-theme sections ────────────────────────────────────
        val grouped = evaluations
            .filter { it.intituleAction.isNotBlank() }
            .groupBy { it.intituleAction.trim() }
            .toSortedMap()

        grouped.forEach { (themeName, themeEvals) ->

            // Theme sub-header (spans 4 columns)
            val themeHeaderRow = sheet.createRow(rowIndex++)
            themeHeaderRow.createCell(0).apply {
                setCellValue("🎓 $themeName — ${themeEvals.size} évaluation${if (themeEvals.size > 1) "s" else ""}")
                cellStyle = themeSubHeaderStyle
            }
            sheet.addMergedRegion(CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 3))

            // Performance par critère for this theme
            val themeCriteria = computeCriteriaAverages(themeEvals)
            rowIndex = addSectionHeader(sheet, rowIndex, "Performance par Critère", titleStyle)

            if (themeCriteria != null) {
                val critHeaderRow = sheet.createRow(rowIndex++)
                createCell(critHeaderRow, 0, "Critère", titleStyle)
                createCell(critHeaderRow, 1, "Moyenne", titleStyle)
                listOf(
                    "Satisfaction du Besoin"       to themeCriteria.satisfactionBesoin,
                    "Impact sur la Performance"     to themeCriteria.impactPerformance,
                    "Application des Connaissances" to themeCriteria.applicationConnaissances,
                    "Satisfaction Globale"          to themeCriteria.satisfactionGlobale
                ).forEach { (label, value) ->
                    val row = sheet.createRow(rowIndex++)
                    createCell(row, 0, label, null)
                    createCell(row, 1, decimalFormat.format(value), valueStyle)
                }
            } else {
                addRow(sheet, rowIndex++, null, null, "Aucune donnée disponible", "")
            }

            // Taux de satisfaction for this theme
            val themeSatisfaction = computeSatisfactionRate(themeEvals)
            rowIndex = addSectionHeader(sheet, rowIndex, "Taux de Satisfaction", titleStyle)
            rowIndex = writeSatisfactionSection(sheet, rowIndex, themeSatisfaction, titleStyle, valueStyle)

            // Blank separator row between themes
            rowIndex++
        }

        for (i in 0..3) sheet.setColumnWidth(i, 8000)

        return saveWorkbook(workbook, context, "Rapport_Analytique_ParTheme_$timestamp.xlsx")
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Shared: write satisfaction rows, return next rowIndex
    // ─────────────────────────────────────────────────────────────────────────

    private fun writeSatisfactionSection(
        sheet           : Sheet,
        startRow        : Int,
        satisfactionRate: SatisfactionRate?,
        titleStyle      : CellStyle,
        valueStyle      : CellStyle
    ): Int {
        var rowIndex = startRow
        if (satisfactionRate != null) {
            val satHeaderRow = sheet.createRow(rowIndex++)
            createCell(satHeaderRow, 0, "Catégorie",   titleStyle)
            createCell(satHeaderRow, 1, "Nombre",      titleStyle)
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
            createCell(rowTotal, 0, "Total",  titleStyle)
            createCell(rowTotal, 1, satisfactionRate.total.toString(), titleStyle)
            createCell(rowTotal, 2, "100%",   titleStyle)
        } else {
            addRow(sheet, rowIndex++, null, null, "Aucune donnée disponible", "")
        }
        return rowIndex
    }

    // ─────────────────────────────────────────────────────────────────────────
    // File 3-A : Suggestions — single sheet (GLOBALE / ONE_THEME)
    // ─────────────────────────────────────────────────────────────────────────

    private fun createSuggestionsFile(
        context        : Context,
        timestamp      : String,
        evaluations    : List<EvaluationEntity>,
        filenameSuffix : String = ""
    ): File {
        val workbook = XSSFWorkbook()
        val styles   = buildSuggestionStyles(workbook)
        val sheet    = workbook.createSheet("Suggestions & Remarques")

        val grouped = evaluations
            .filter { it.intituleAction.isNotBlank() }
            .groupBy { it.intituleAction.trim() }
            .toSortedMap()

        if (grouped.isEmpty())
            sheet.createRow(0).createCell(0).setCellValue("Aucune évaluation disponible.")
        else
            writeSuggestionGroups(sheet, grouped, styles, 0)

        setSuggestionColumnWidths(sheet)

        val suffix = if (filenameSuffix.isNotBlank()) "_$filenameSuffix" else ""
        return saveWorkbook(workbook, context, "Suggestions_SiNon${suffix}_$timestamp.xlsx")
    }

    // ─────────────────────────────────────────────────────────────────────────
    // File 3-B : Suggestions — one sheet per theme (ALL_THEMES)
    // ─────────────────────────────────────────────────────────────────────────

    private fun createSuggestionsFileByTheme(
        context    : Context,
        timestamp  : String,
        evaluations: List<EvaluationEntity>
    ): File {
        val workbook = XSSFWorkbook()
        val styles   = buildSuggestionStyles(workbook)

        val grouped = evaluations
            .filter { it.intituleAction.isNotBlank() }
            .groupBy { it.intituleAction.trim() }
            .toSortedMap()

        if (grouped.isEmpty()) {
            workbook.createSheet("Aucun résultat")
                .createRow(0).createCell(0)
                .setCellValue("Aucune évaluation disponible.")
        } else {
            grouped.forEach { (themeName, evals) ->
                val safeName = themeName
                    .replace(Regex("[\\\\/:*?\"<>|\\[\\]]"), " ")
                    .trim()
                    .take(31)
                val sheet = workbook.createSheet(safeName)
                writeSuggestionGroups(sheet, sortedMapOf(themeName to evals), styles, 0)
                setSuggestionColumnWidths(sheet)
            }
        }

        return saveWorkbook(workbook, context, "Suggestions_ParTheme_$timestamp.xlsx")
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Shared suggestion writer
    // ─────────────────────────────────────────────────────────────────────────

    private fun writeSuggestionGroups(
        sheet    : Sheet,
        grouped  : Map<String, List<EvaluationEntity>>,
        styles   : SuggestionStyles,
        startRow : Int
    ): Int {
        var rowIndex = startRow

        grouped.forEach { (themeName, evals) ->

            // Theme header
            val themeRow = sheet.createRow(rowIndex++)
            themeRow.createCell(0).apply {
                setCellValue("🎓 $themeName (${evals.size} évaluation${if (evals.size > 1) "s" else ""})")
                cellStyle = styles.themeHeader
            }
            themeRow.heightInPoints = 22f
            sheet.addMergedRegion(CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 3))

            // Column headers
            val colRow = sheet.createRow(rowIndex++)
            listOf("Collaborateur", "Date Évaluation", "Mle Flm","Si non pourquoi ?", "Suggestions")
                .forEachIndexed { i, title ->
                    colRow.createCell(i).apply {
                        setCellValue(title)
                        cellStyle = styles.columnHeader
                    }
                }

            // Data rows
            evals.forEach { eval ->
                val hasSuggestion = eval.Suggestions.isNotBlank()
                val hasRaisons    = eval.raisonsInsatisfaction.isNotEmpty()

                val dataRow = sheet.createRow(rowIndex++)
                dataRow.heightInPoints = maxOf(
                    40f,
                    (eval.raisonsInsatisfaction.size * 15f).coerceAtLeast(15f),
                    if (hasSuggestion) (eval.Suggestions.length / 40f * 15f).coerceAtLeast(15f) else 15f
                )

                createStyledCell(dataRow, 0, eval.maticuleCollaborateur, styles.cell)
                createStyledCell(dataRow, 1, eval.dateEvaluation.ifBlank { "—" }, styles.cell)

                val raisonsText = if (hasRaisons)
                    eval.raisonsInsatisfaction.joinToString("\n") { "• $it" } else "—"
                createStyledCell(dataRow, 2, raisonsText,
                    if (hasRaisons) styles.cell else styles.emptyCell)
                createStyledCell(dataRow, 3,
                    if (hasSuggestion) eval.Suggestions else "—",
                    if (hasSuggestion) styles.cell else styles.emptyCell)
            }

            // Summary row
            val withSugg    = evals.count { it.Suggestions.isNotBlank() }
            val withRaisons = evals.count { it.raisonsInsatisfaction.isNotEmpty() }
            sheet.createRow(rowIndex++).createCell(0).apply {
                setCellValue(
                    "  $withSugg suggestion${if (withSugg > 1) "s" else ""} " +
                            "recueillie${if (withSugg > 1) "s" else ""}   |   " +
                            "$withRaisons remarque${if (withRaisons > 1) "s" else ""} d'insatisfaction"
                )
                cellStyle = styles.columnHeader
            }
            sheet.addMergedRegion(CellRangeAddress(rowIndex - 1, rowIndex - 1, 0, 3))

            // Separator
            val sepRow = sheet.createRow(rowIndex++)
            for (i in 0..3) sepRow.createCell(i).cellStyle = styles.separator
            sepRow.heightInPoints = 10f
        }

        return rowIndex
    }

    // ─────────────────────────────────────────────────────────────────────────
    // File 2: Sessions
    // ─────────────────────────────────────────────────────────────────────────

    private fun createSessionsFile(
        context        : Context,
        timestamp      : String,
        formations     : List<FormationEntity>,
        filenameSuffix : String = ""
    ): File {
        val workbook = XSSFWorkbook()
        val sheet    = workbook.createSheet("Sessions")

        val headerStyle = workbook.createCellStyle().apply {
            fillForegroundColor = IndexedColors.DARK_GREEN.index
            fillPattern         = FillPatternType.SOLID_FOREGROUND
            alignment           = HorizontalAlignment.CENTER
            setFont(workbook.createFont().apply {
                bold               = true
                color              = IndexedColors.WHITE.index
                fontHeightInPoints = 12
            })
        }

        createCell(sheet.createRow(0), 0, "Sessions", headerStyle)

        formations
            .map { it.session.trim() }
            .filter { it.isNotBlank() }
            .distinct()
            .sorted()
            .forEachIndexed { index, session ->
                createCell(sheet.createRow(index + 1), 0, session, null)
            }

        sheet.setColumnWidth(0, 10000)

        val suffix = if (filenameSuffix.isNotBlank()) "_$filenameSuffix" else ""
        return saveWorkbook(workbook, context, "Sessions${suffix}_$timestamp.xlsx")
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Style builders
    // ─────────────────────────────────────────────────────────────────────────

    private data class AnalyticsStyles(
        val header: CellStyle,
        val title : CellStyle,
        val value : CellStyle
    )

    private operator fun AnalyticsStyles.component1() = header
    private operator fun AnalyticsStyles.component2() = title
    private operator fun AnalyticsStyles.component3() = value

    private fun buildAnalyticsStyles(workbook: XSSFWorkbook): AnalyticsStyles {
        val headerStyle = workbook.createCellStyle().apply {
            fillForegroundColor = IndexedColors.DARK_GREEN.index
            fillPattern         = FillPatternType.SOLID_FOREGROUND
            alignment           = HorizontalAlignment.CENTER
            setFont(workbook.createFont().apply {
                bold               = true
                color              = IndexedColors.WHITE.index
                fontHeightInPoints = 12
            })
        }
        val titleStyle = workbook.createCellStyle().apply {
            fillForegroundColor = IndexedColors.LIGHT_GREEN.index
            fillPattern         = FillPatternType.SOLID_FOREGROUND
            setFont(workbook.createFont().apply {
                bold               = true
                fontHeightInPoints = 11
            })
        }
        val valueStyle = workbook.createCellStyle().apply {
            alignment = HorizontalAlignment.CENTER
        }
        return AnalyticsStyles(headerStyle, titleStyle, valueStyle)
    }

    private data class SuggestionStyles(
        val themeHeader  : CellStyle,
        val columnHeader : CellStyle,
        val cell         : CellStyle,
        val emptyCell    : CellStyle,
        val separator    : CellStyle
    )

    private fun buildSuggestionStyles(workbook: XSSFWorkbook): SuggestionStyles {
        val themeHeader = workbook.createCellStyle().apply {
            fillForegroundColor = IndexedColors.DARK_GREEN.index
            fillPattern         = FillPatternType.SOLID_FOREGROUND
            alignment           = HorizontalAlignment.CENTER
            wrapText            = true
            setFont(workbook.createFont().apply {
                bold               = true
                color              = IndexedColors.WHITE.index
                fontHeightInPoints = 13
            })
        }
        val columnHeader = workbook.createCellStyle().apply {
            fillForegroundColor = IndexedColors.LIGHT_GREEN.index
            fillPattern         = FillPatternType.SOLID_FOREGROUND
            alignment           = HorizontalAlignment.CENTER
            wrapText            = true
            setFont(workbook.createFont().apply {
                bold               = true
                fontHeightInPoints = 11
            })
        }
        val cell = workbook.createCellStyle().apply {
            wrapText          = true
            verticalAlignment = VerticalAlignment.TOP
        }
        val emptyCell = workbook.createCellStyle().apply {
            fillForegroundColor = IndexedColors.GREY_25_PERCENT.index
            fillPattern         = FillPatternType.SOLID_FOREGROUND
        }
        val separator = workbook.createCellStyle().apply {
            fillForegroundColor = IndexedColors.WHITE.index
            fillPattern         = FillPatternType.SOLID_FOREGROUND
        }
        return SuggestionStyles(themeHeader, columnHeader, cell, emptyCell, separator)
    }

    private fun setSuggestionColumnWidths(sheet: Sheet) {
        sheet.setColumnWidth(0, 5000)
        sheet.setColumnWidth(1, 4000)
        sheet.setColumnWidth(2, 12000)
        sheet.setColumnWidth(3, 14000)
    }

    // ─────────────────────────────────────────────────────────────────────────
    // Generic helpers
    // ─────────────────────────────────────────────────────────────────────────

    private fun addSectionHeader(sheet: Sheet, rowIndex: Int, title: String, style: CellStyle): Int {
        sheet.createRow(rowIndex).createCell(0).apply {
            setCellValue(title)
            cellStyle = style
        }
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
        row.createCell(col).apply {
            setCellValue(value)
            style?.let { cellStyle = it }
        }
    }

    private fun createStyledCell(row: Row, col: Int, value: String, style: CellStyle?) {
        row.createCell(col).apply {
            setCellValue(value)
            style?.let { cellStyle = it }
        }
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