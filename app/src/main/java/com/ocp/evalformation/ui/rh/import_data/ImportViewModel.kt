package com.ocp.evalformation.ui.rh.import_data

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ocp.evalformation.data.local.entity.*
import com.ocp.evalformation.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.InputStream
import javax.inject.Inject

sealed class ImportState {
    object Idle : ImportState()
    object Loading : ImportState()
    data class Success(val message: String) : ImportState()
    data class Error(val message: String) : ImportState()
}

@HiltViewModel
class ImportViewModel @Inject constructor(
    private val repo: MainRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val allCollaborateurs = repo.collaborateurDao.getAllLive()
    val allFlms = repo.flmDao.getAllLive()
    val allThemes = repo.themeDao.getAllLive()
    val allFormations = repo.formationDao.getAllLive()

    private val _collabState = MutableStateFlow<ImportState>(ImportState.Idle)
    val collabState: StateFlow<ImportState> = _collabState

    private val _flmState = MutableStateFlow<ImportState>(ImportState.Idle)
    val flmState: StateFlow<ImportState> = _flmState

    private val _themeState = MutableStateFlow<ImportState>(ImportState.Idle)
    val themeState: StateFlow<ImportState> = _themeState

    private val _bilanState = MutableStateFlow<ImportState>(ImportState.Idle)
    val bilanState: StateFlow<ImportState> = _bilanState

    fun resetCollabState()  { _collabState.value  = ImportState.Idle }
    fun resetFlmState()     { _flmState.value     = ImportState.Idle }
    fun resetThemeState()   { _themeState.value   = ImportState.Idle }
    fun resetBilanState()   { _bilanState.value   = ImportState.Idle }

    /* --------------------------------------------------- */
    /* Helper functions                                     */
    /* --------------------------------------------------- */

    private fun col(headers: Map<String, Int>, vararg keys: String): Int? {
        for (k in keys) headers[k]?.let { return it }
        return null
    }

    private fun cellStr(row: org.apache.poi.ss.usermodel.Row, colIndex: Int): String {
        return try {
            val cell = row.getCell(colIndex) ?: return ""
            when (cell.cellType) {
                CellType.STRING  -> cell.stringCellValue.trim()
                CellType.NUMERIC -> {
                    val d = cell.numericCellValue
                    if (d == kotlin.math.floor(d)) d.toLong().toString() else d.toString()
                }
                CellType.FORMULA -> try {
                    cell.stringCellValue.trim()
                } catch (e: Exception) {
                    cell.numericCellValue.toLong().toString()
                }
                else -> ""
            }
        } catch (e: Exception) { "" }
    }

    /**
     * Validates a row's required fields. Returns a list of missing field names, empty if all present.
     * rowNum is 0-based (POI), displayed as 1-based to the user.
     */
    private fun missingFields(
        row: org.apache.poi.ss.usermodel.Row,
        fields: Map<String, Int>   // display name -> column index
    ): List<String> = fields.entries
        .filter { (_, colIdx) -> cellStr(row, colIdx).isBlank() }
        .map { (name, _) -> name }

    /* --------------------------------------------------- */
    /* ADD COLLABORATEUR (manual form)                      */
    /* --------------------------------------------------- */

    fun addCollaborateur(
        matricule: String?,
        nom: String?,
        prenom: String?,
        service: String?,
        flmMatricule: String?
    ) {
        val missing = listOfNotNull(
            "Matricule".takeIf { matricule.isNullOrBlank() },
            "Nom".takeIf { nom.isNullOrBlank() },
            "Prénom".takeIf { prenom.isNullOrBlank() },
            "Service".takeIf { service.isNullOrBlank() },
            "Matricule FLM".takeIf { flmMatricule.isNullOrBlank() }
        )
        if (missing.isNotEmpty()) {
            _collabState.value = ImportState.Error("❌ Champ(s) obligatoire(s) manquant(s) : ${missing.joinToString(", ")}")
            return
        }
        viewModelScope.launch {
            _collabState.value = ImportState.Loading
            try {
                val entity = CollaborateurEntity(
                    matricule    = matricule!!.trim(),
                    nom          = nom!!.trim(),
                    prenom       = prenom!!.trim(),
                    service      = service!!.trim(),
                    flmMatricule = flmMatricule!!.trim()
                )
                val affected = repo.collaborateurDao.upsertCollaborateurs(listOf(entity))
                val uploadRes = repo.firebase.uploadCollaborateurs(listOf(entity))
                if (uploadRes.isSuccess) {
                    repo.collaborateurDao.markSynced(affected)
                    _collabState.value = ImportState.Success("✅ Collaborateur ajouté.")
                } else {
                    _collabState.value = ImportState.Error("❌ Erreur upload: ${uploadRes.exceptionOrNull()?.message}")
                }
            } catch (e: Exception) {
                _collabState.value = ImportState.Error("❌ Erreur : ${e.message}")
            }
        }
    }

    /* --------------------------------------------------- */
    /* ADD FLM (manual form)                                */
    /* --------------------------------------------------- */

    fun addFlm(
        matricule: String?,
        nom: String?,
        prenom: String?,
        email: String?,
        service: String?
    ) {
        val missing = listOfNotNull(
            "Matricule".takeIf { matricule.isNullOrBlank() },
            "Nom".takeIf { nom.isNullOrBlank() },
            "Prénom".takeIf { prenom.isNullOrBlank() },
            "Email".takeIf { email.isNullOrBlank() },
            "Service".takeIf { service.isNullOrBlank() }
        )
        if (missing.isNotEmpty()) {
            _flmState.value = ImportState.Error("❌ Champ(s) obligatoire(s) manquant(s) : ${missing.joinToString(", ")}")
            return
        }
        viewModelScope.launch {
            _flmState.value = ImportState.Loading
            try {
                val entity = FlmEntity(
                    matricule = matricule!!.trim(),
                    nom       = nom!!.trim(),
                    prenom    = prenom!!.trim(),
                    email     = email!!.trim(),
                    service   = service!!.trim()
                )
                val affected = repo.flmDao.upsertFlms(listOf(entity))
                val uploadRes = repo.firebase.uploadFlms(listOf(entity))
                if (uploadRes.isSuccess) {
                    repo.flmDao.markSynced(affected)
                    _flmState.value = ImportState.Success("✅ FLM ajouté.")
                } else {
                    _flmState.value = ImportState.Error("❌ Erreur upload: ${uploadRes.exceptionOrNull()?.message}")
                }
            } catch (e: Exception) {
                _flmState.value = ImportState.Error("❌ Erreur : ${e.message}")
            }
        }
    }

    /* --------------------------------------------------- */
    /* ADD THEME (manual form)                              */
    /* --------------------------------------------------- */

    fun addTheme(nom: String?, objectifPedagogique: String?) {
        val missing = listOfNotNull(
            "Nom".takeIf { nom.isNullOrBlank() },
            "Objectif pédagogique".takeIf { objectifPedagogique.isNullOrBlank() }
        )
        if (missing.isNotEmpty()) {
            _themeState.value = ImportState.Error("❌ Champ(s) obligatoire(s) manquant(s) : ${missing.joinToString(", ")}")
            return
        }
        viewModelScope.launch {
            _themeState.value = ImportState.Loading
            try {
                val entity = ThemeEntity(
                    nom                 = nom!!.trim(),
                    objectifPedagogique = objectifPedagogique!!.trim()
                )
                val affectedIds = repo.themeDao.upsertThemes(listOf(entity))
                val syncedThemes = affectedIds.mapNotNull { repo.themeDao.getById(it) }
                val uploadRes = repo.firebase.uploadThemes(syncedThemes)
                if (uploadRes.isSuccess) {
                    repo.themeDao.markSynced(affectedIds)
                    _themeState.value = ImportState.Success("✅ Thème ajouté.")
                } else {
                    _themeState.value = ImportState.Error("❌ Erreur upload: ${uploadRes.exceptionOrNull()?.message}")
                }
            } catch (e: Exception) {
                _themeState.value = ImportState.Error("❌ Erreur : ${e.message}")
            }
        }
    }

    /* --------------------------------------------------- */
    /* IMPORT THEMES (Excel)                                */
    /* --------------------------------------------------- */

    fun importThemesFromExcel(inputStream: InputStream) {
        viewModelScope.launch {
            _themeState.value = ImportState.Loading
            try {
                val wb = WorkbookFactory.create(inputStream)
                val sheet = wb.getSheetAt(0)

                var headerRow: Row? = null
                val headers = mutableMapOf<String, Int>()

                for (rowIdx in 0..minOf(9, sheet.lastRowNum)) {
                    val row = sheet.getRow(rowIdx) ?: continue
                    val tempHeaders = mutableMapOf<String, Int>()
                    for (cell in row) tempHeaders[cell.toString().trim().uppercase()] = cell.columnIndex

                    if (col(tempHeaders, "NOM", "THEME", "INTITULE") != null) {
                        headerRow = row
                        headers.putAll(tempHeaders)
                        break
                    }
                }

                if (headerRow == null) {
                    _themeState.value = ImportState.Error("Colonnes requises manquantes : Nom, ObjectifPedagogique")
                    return@launch
                }

                val colNom = col(headers, "NOM", "THEME", "INTITULE")!!
                val colObj = col(headers, "OBJECTIFPEDAGOGIQUE", "OBJECTIF", "OBJ")

                if (colObj == null) {
                    _themeState.value = ImportState.Error("Colonne manquante : ObjectifPedagogique")
                    return@launch
                }

                // required fields map: display name -> column index
                val requiredFields = mapOf("Nom" to colNom, "ObjectifPedagogique" to colObj)

                val list = mutableListOf<ThemeEntity>()
                val rowErrors = mutableListOf<String>()
                val dataStartRow = headerRow.rowNum + 1

                for (i in dataStartRow..sheet.lastRowNum) {
                    val row = sheet.getRow(i) ?: continue
                    if ((0 until row.lastCellNum).all { cellStr(row, it).isBlank() }) continue // skip empty rows

                    val missing = missingFields(row, requiredFields)
                    if (missing.isNotEmpty()) {
                        rowErrors.add("Ligne ${i + 1} : champ(s) manquant(s) → ${missing.joinToString(", ")}")
                        continue
                    }

                    list.add(ThemeEntity(
                        nom                 = cellStr(row, colNom),
                        objectifPedagogique = cellStr(row, colObj)
                    ))
                }
                wb.close()

                if (rowErrors.isNotEmpty()) {
                    _themeState.value = ImportState.Error("❌ Erreurs dans le fichier :\n${rowErrors.joinToString("\n")}")
                    return@launch
                }

                if (list.isEmpty()) { _themeState.value = ImportState.Error("Aucune donnée trouvée."); return@launch }

                val affectedIds = repo.themeDao.upsertThemes(list)
                val syncedThemes = affectedIds.mapNotNull { repo.themeDao.getById(it) }

                val uploadRes = repo.firebase.uploadThemes(syncedThemes)
                if (uploadRes.isSuccess) {
                    repo.themeDao.markSynced(affectedIds)
                    _themeState.value = ImportState.Success("✅ ${syncedThemes.size} thème(s) importé(s).")
                } else {
                    _themeState.value = ImportState.Error("❌ Erreur upload: ${uploadRes.exceptionOrNull()?.message}")
                }

            } catch (e: Exception) {
                _themeState.value = ImportState.Error("❌ Erreur : ${e.message}")
            }
        }
    }

    /* --------------------------------------------------- */
    /* IMPORT COLLABORATEURS (Excel)                        */
    /* --------------------------------------------------- */

    fun importCollaborateursFromExcel(inputStream: InputStream) {
        viewModelScope.launch {
            _collabState.value = ImportState.Loading
            try {
                val wb = WorkbookFactory.create(inputStream)
                val sheet = wb.getSheetAt(0)

                var headerRow: Row? = null
                val headers = mutableMapOf<String, Int>()

                for (rowIdx in 0..minOf(9, sheet.lastRowNum)) {
                    val row = sheet.getRow(rowIdx) ?: continue
                    val tempHeaders = mutableMapOf<String, Int>()
                    for (cell in row) tempHeaders[cell.toString().trim().uppercase()] = cell.columnIndex

                    val hasRequired = col(tempHeaders, "MATRICULE", "MLE", "MAT") != null &&
                            col(tempHeaders, "NOM", "NAME") != null &&
                            col(tempHeaders, "PRENOM", "FIRSTNAME") != null &&
                            col(tempHeaders, "SERVICE", "DIVISION", "DIV", "DEPARTEMENT") != null

                    if (hasRequired) {
                        headerRow = row
                        headers.putAll(tempHeaders)
                        break
                    }
                }

                if (headerRow == null) {
                    _collabState.value = ImportState.Error("Colonnes requises manquantes : Matricule, Nom, Prenom, Service")
                    return@launch
                }

                val colMat = col(headers, "MATRICULE", "MLE", "MAT")!!
                val colNom = col(headers, "NOM", "NAME")!!
                val colPre = col(headers, "PRENOM", "FIRSTNAME")!!
                val colSvc = col(headers, "SERVICE", "DIVISION", "DIV", "DEPARTEMENT")!!
                val colFlm = col(headers, "FLM", "FLMMATRICULE", "MATRICULE_FLM")

                // all non-optional fields are required
                val requiredFields = mapOf(
                    "Matricule" to colMat,
                    "Nom"       to colNom,
                    "Prenom"    to colPre,
                    "Service"   to colSvc
                )

                val list = mutableListOf<CollaborateurEntity>()
                val rowErrors = mutableListOf<String>()
                val dataStartRow = headerRow.rowNum + 1

                for (i in dataStartRow..sheet.lastRowNum) {
                    val row = sheet.getRow(i) ?: continue
                    if ((0 until row.lastCellNum).all { cellStr(row, it).isBlank() }) continue // skip empty rows

                    val missing = missingFields(row, requiredFields)
                    if (missing.isNotEmpty()) {
                        rowErrors.add("Ligne ${i + 1} : champ(s) manquant(s) → ${missing.joinToString(", ")}")
                        continue
                    }

                    list.add(CollaborateurEntity(
                        matricule    = cellStr(row, colMat),
                        nom          = cellStr(row, colNom),
                        prenom       = cellStr(row, colPre),
                        service      = cellStr(row, colSvc),
                        flmMatricule = colFlm?.let { val v = cellStr(row, it); if (v.isBlank()) null else v }
                    ))
                }
                wb.close()

                if (rowErrors.isNotEmpty()) {
                    _collabState.value = ImportState.Error("❌ Erreurs dans le fichier :\n${rowErrors.joinToString("\n")}")
                    return@launch
                }

                if (list.isEmpty()) { _collabState.value = ImportState.Error("Aucune donnée trouvée."); return@launch }

                val affected = repo.collaborateurDao.upsertCollaborateurs(list)
                val uploadRes = repo.firebase.uploadCollaborateurs(list)
                if (uploadRes.isSuccess) {
                    repo.collaborateurDao.markSynced(affected)
                    _collabState.value = ImportState.Success("✅ ${affected.size} collaborateur(s) importé(s).")
                } else {
                    _collabState.value = ImportState.Error("❌ Erreur upload: ${uploadRes.exceptionOrNull()?.message}")
                }

            } catch (e: Exception) {
                _collabState.value = ImportState.Error("❌ Erreur : ${e.message}")
            }
        }
    }

    /* --------------------------------------------------- */
    /* IMPORT FLMS (Excel)                                  */
    /* --------------------------------------------------- */

    fun importFlmsFromExcel(inputStream: InputStream) {
        viewModelScope.launch {
            _flmState.value = ImportState.Loading
            try {
                val wb = WorkbookFactory.create(inputStream)
                val sheet = wb.getSheetAt(0)

                var headerRow: Row? = null
                val headers = mutableMapOf<String, Int>()

                for (rowIdx in 0..minOf(9, sheet.lastRowNum)) {
                    val row = sheet.getRow(rowIdx) ?: continue
                    val tempHeaders = mutableMapOf<String, Int>()
                    for (cell in row) tempHeaders[cell.toString().trim().uppercase()] = cell.columnIndex

                    val hasRequired = col(tempHeaders, "MATRICULE", "MLE") != null &&
                            col(tempHeaders, "NOM", "NAME") != null &&
                            col(tempHeaders, "PRENOM", "FIRSTNAME") != null &&
                            col(tempHeaders, "EMAIL", "MAIL", "COURRIEL") != null &&
                            col(tempHeaders, "SERVICE", "DIVISION", "DIV") != null

                    if (hasRequired) {
                        headerRow = row
                        headers.putAll(tempHeaders)
                        break
                    }
                }

                if (headerRow == null) {
                    _flmState.value = ImportState.Error("Colonnes requises manquantes : Matricule, Nom, Prenom, Email, Service")
                    return@launch
                }

                val colMat = col(headers, "MATRICULE", "MLE")!!
                val colNom = col(headers, "NOM", "NAME")!!
                val colPre = col(headers, "PRENOM", "FIRSTNAME")!!
                val colEml = col(headers, "EMAIL", "MAIL", "COURRIEL")!!
                val colSvc = col(headers, "SERVICE", "DIVISION", "DIV")!!

                val requiredFields = mapOf(
                    "Matricule" to colMat,
                    "Nom"       to colNom,
                    "Prenom"    to colPre,
                    "Email"     to colEml,
                    "Service"   to colSvc
                )

                val list = mutableListOf<FlmEntity>()
                val rowErrors = mutableListOf<String>()
                val dataStartRow = headerRow.rowNum + 1

                for (i in dataStartRow..sheet.lastRowNum) {
                    val row = sheet.getRow(i) ?: continue
                    if ((0 until row.lastCellNum).all { cellStr(row, it).isBlank() }) continue // skip empty rows

                    val missing = missingFields(row, requiredFields)
                    if (missing.isNotEmpty()) {
                        rowErrors.add("Ligne ${i + 1} : champ(s) manquant(s) → ${missing.joinToString(", ")}")
                        continue
                    }

                    list.add(FlmEntity(
                        matricule = cellStr(row, colMat),
                        nom       = cellStr(row, colNom),
                        prenom    = cellStr(row, colPre),
                        email     = cellStr(row, colEml),
                        service   = cellStr(row, colSvc)
                    ))
                }
                wb.close()

                if (rowErrors.isNotEmpty()) {
                    _flmState.value = ImportState.Error("❌ Erreurs dans le fichier :\n${rowErrors.joinToString("\n")}")
                    return@launch
                }

                if (list.isEmpty()) { _flmState.value = ImportState.Error("Aucune donnée trouvée."); return@launch }

                val affected = repo.flmDao.upsertFlms(list)
                val uploadRes = repo.firebase.uploadFlms(list)
                if (uploadRes.isSuccess) {
                    repo.flmDao.markSynced(affected)
                    _flmState.value = ImportState.Success("✅ ${affected.size} FLM(s) importé(s).")
                } else {
                    _flmState.value = ImportState.Error("❌ Erreur upload: ${uploadRes.exceptionOrNull()?.message}")
                }

            } catch (e: Exception) {
                _flmState.value = ImportState.Error("❌ Erreur : ${e.message}")
            }
        }
    }

    /* --------------------------------------------------- */
    /* IMPORT BILAN FC (Excel)                              */
    /* --------------------------------------------------- */

    fun importBilanFC(inputStream: InputStream) {
        viewModelScope.launch {
            _bilanState.value = ImportState.Loading
            try {
                val wb = WorkbookFactory.create(inputStream)
                val sheet = wb.getSheetAt(0)

                var headerRow: org.apache.poi.ss.usermodel.Row? = null
                val headers = mutableMapOf<String, Int>()

                for (rowIdx in 0..minOf(9, sheet.lastRowNum)) {
                    val row = sheet.getRow(rowIdx) ?: continue
                    val tempHeaders = mutableMapOf<String, Int>()
                    for (cell in row) tempHeaders[cell.toString().trim().uppercase()] = cell.columnIndex

                    val hasRequired = col(tempHeaders, "MATRICULE", "MLE", "COLLAB") != null &&
                            col(tempHeaders, "THEMEID", "THEME_ID", "THEME") != null &&
                            col(tempHeaders, "DEBUT") != null &&
                            col(tempHeaders, "FIN") != null

                    if (hasRequired) {
                        headerRow = row
                        headers.putAll(tempHeaders)
                        break
                    }
                }

                if (headerRow == null) {
                    _bilanState.value = ImportState.Error("Colonnes requises manquantes : Matricule, Theme, Debut, Fin")
                    return@launch
                }

                val colMat   = col(headers, "MATRICULE", "MLE", "COLLAB")!!
                val colTheme = col(headers, "THEMEID", "THEME_ID", "THEME")!!
                val colDebut = col(headers, "DEBUT")!!
                val colFin   = col(headers, "FIN")!!

                val requiredFields = mapOf(
                    "Matricule" to colMat,
                    "Theme"     to colTheme,
                    "Debut"     to colDebut,
                    "Fin"       to colFin
                )

                // Prefetch themes & collaborators
                val themes = repo.themeDao.getAll()
                val themeByNom = themes.associateBy { it.nom.trim().uppercase() }

                val collabs = repo.collaborateurDao.getAll()
                val collabMatSet = collabs.map { it.matricule }.toSet()

                // Prefetch existing formations to detect duplicates
                val existingFormations = repo.formationDao.getAll()
                val existingKeys = existingFormations
                    .map { Triple(it.collaborateurMatricule, it.themeId, it.debut + "|" + it.fin) }
                    .toSet()

                val formations = mutableListOf<FormationEntity>()
                val rowErrors = mutableListOf<String>()
                var duplicates = 0

                val dataStartRow = headerRow.rowNum + 1
                for (i in dataStartRow..sheet.lastRowNum) {
                    val row = sheet.getRow(i) ?: continue
                    if ((0 until row.lastCellNum).all { cellStr(row, it).isBlank() }) continue // skip empty rows

                    // Check for blank required fields first
                    val missing = missingFields(row, requiredFields)
                    if (missing.isNotEmpty()) {
                        rowErrors.add("Ligne ${i + 1} : champ(s) manquant(s) → ${missing.joinToString(", ")}")
                        continue
                    }

                    val mat      = cellStr(row, colMat)
                    val themeRaw = cellStr(row, colTheme).trim()
                    val debut    = cellStr(row, colDebut)
                    val fin      = cellStr(row, colFin)

                    // Validate matricule exists
                    if (!collabMatSet.contains(mat)) {
                        rowErrors.add("Ligne ${i + 1} : matricule '$mat' introuvable dans les collaborateurs.")
                        continue
                    }

                    // Resolve theme
                    val themeId = themeRaw.toLongOrNull()
                        ?: themeRaw.toDoubleOrNull()?.toLong()
                        ?: themeByNom[themeRaw.uppercase()]?.id

                    if (themeId == null) {
                        rowErrors.add("Ligne ${i + 1} : thème '$themeRaw' introuvable.")
                        continue
                    }

                    // Skip duplicates
                    val key = Triple(mat, themeId, "$debut|$fin")
                    if (existingKeys.contains(key)) {
                        duplicates++; continue
                    }

                    formations.add(FormationEntity(
                        collaborateurMatricule = mat,
                        themeId               = themeId,
                        debut                 = debut,
                        fin                   = fin
                    ))
                }
                wb.close()

                if (rowErrors.isNotEmpty()) {
                    _bilanState.value = ImportState.Error("❌ Erreurs dans le fichier :\n${rowErrors.joinToString("\n")}")
                    return@launch
                }

                if (formations.isEmpty()) {
                    val reason = if (duplicates > 0) "$duplicates doublon(s) ignoré(s)." else ""
                    _bilanState.value = ImportState.Error("Aucune nouvelle formation valide. $reason".trim())
                    return@launch
                }

                val insertIds = repo.formationDao.insertAll(formations).filter { it != -1L }
                val syncedFormations = formations.zip(insertIds).map { (f, id) -> f.copy(id = id) }

                val uploadRes = repo.firebase.uploadFormations(syncedFormations)
                if (uploadRes.isSuccess) {
                    repo.formationDao.markSynced(insertIds)
                    val summary = buildString {
                        append("✅ ${syncedFormations.size} formation(s) importée(s).")
                        if (duplicates > 0) append(" $duplicates doublon(s) ignoré(s).")
                    }
                    _bilanState.value = ImportState.Success(summary)
                } else {
                    _bilanState.value = ImportState.Error("❌ Erreur upload: ${uploadRes.exceptionOrNull()?.message}")
                }

            } catch (e: Exception) {
                _bilanState.value = ImportState.Error("❌ ${e.message}")
            }
        }
    }

}