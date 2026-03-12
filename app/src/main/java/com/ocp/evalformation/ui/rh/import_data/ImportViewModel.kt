package com.ocp.evalformation.ui.rh.import_data

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ocp.evalformation.data.local.entity.*
import com.ocp.evalformation.data.repository.MainRepository
import com.ocp.evalformation.data.repository.syncRepository
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

    /* --------------------------------------------------- */
    /* ADD COLLABORATEUR (manual form)                      */
    /* --------------------------------------------------- */

    fun addCollaborateur(
        matricule: String,
        nom: String,
        prenom: String,
        service: String,
        flmMatricule: String
    ) {
        if (matricule.isBlank() || nom.isBlank() || prenom.isBlank() || service.isBlank()) {
            _collabState.value = ImportState.Error("Veuillez remplir tous les champs obligatoires.")
            return
        }
        viewModelScope.launch {
            _collabState.value = ImportState.Loading
            try {
                val entity = CollaborateurEntity(
                    matricule    = matricule.trim(),
                    nom          = nom.trim(),
                    prenom       = prenom.trim(),
                    service      = service.trim(),
                    flmMatricule = flmMatricule.trim().ifBlank { null }
                )
                repo.collaborateurDao.insert(entity)
                val uploadRes = repo.firebase.uploadCollaborateurs(listOf(entity))
                if (uploadRes.isSuccess) {
                    repo.collaborateurDao.markSynced(listOf(entity.matricule))
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
        matricule: String,
        nom: String,
        prenom: String,
        email: String,
        service: String
    ) {
        if (matricule.isBlank() || nom.isBlank() || prenom.isBlank() || email.isBlank() || service.isBlank()) {
            _flmState.value = ImportState.Error("Veuillez remplir tous les champs obligatoires.")
            return
        }
        viewModelScope.launch {
            _flmState.value = ImportState.Loading
            try {
                val entity = FlmEntity(
                    matricule = matricule.trim(),
                    nom       = nom.trim(),
                    prenom    = prenom.trim(),
                    email     = email.trim(),
                    service   = service.trim()
                )
                repo.flmDao.insert(entity)
                val uploadRes = repo.firebase.uploadFlms(listOf(entity))
                if (uploadRes.isSuccess) {
                    repo.flmDao.markSynced(listOf(entity.matricule))
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

    fun addTheme(nom: String, objectifPedagogique: String) {
        if (nom.isBlank()) {
            _themeState.value = ImportState.Error("Le nom du thème est obligatoire.")
            return
        }
        viewModelScope.launch {
            _themeState.value = ImportState.Loading
            try {
                val entity = ThemeEntity(
                    nom = nom.trim(),
                    objectifPedagogique = objectifPedagogique.trim()
                )
                val id = repo.themeDao.insert(entity)
                val synced = entity.copy(id = id)
                val uploadRes = repo.firebase.uploadThemes(listOf(synced))
                if (uploadRes.isSuccess) {
                    repo.themeDao.markSynced(listOf(id))
                    _themeState.value = ImportState.Success("✅ Thème ajouté.")
                } else {
                    _themeState.value =
                        ImportState.Error("❌ Erreur upload: ${uploadRes.exceptionOrNull()?.message}")
                }
            } catch (e: Exception) {
                _themeState.value = ImportState.Error("❌ Erreur : ${e.message}")
            }
        }
    }

    fun importThemesFromExcel(inputStream: InputStream) {
        viewModelScope.launch {
            _themeState.value = ImportState.Loading
            try {
                val wb = WorkbookFactory.create(inputStream)
                val sheet = wb.getSheetAt(0)

                val headers = mutableMapOf<String, Int>()
                val headerRow = sheet.getRow(0) ?: run {
                    _themeState.value = ImportState.Error("Fichier vide.")
                    return@launch
                }
                for (cell in headerRow) headers[cell.toString().trim().uppercase()] = cell.columnIndex

                val colNom = col(headers, "NOM", "THEME", "INTITULE")
                val colObj = col(headers, "OBJECTIFPEDAGOGIQUE", "OBJECTIF", "OBJ")

                if (colNom == null) {
                    _themeState.value = ImportState.Error("Colonne 'Nom' non trouvée.")
                    return@launch
                }

                val list = mutableListOf<ThemeEntity>()
                for (i in 1..sheet.lastRowNum) {
                    val row = sheet.getRow(i) ?: continue
                    val nom = cellStr(row, colNom)
                    if (nom.isBlank()) continue
                    list.add(ThemeEntity(
                        nom                 = nom,
                        objectifPedagogique = colObj?.let { cellStr(row, it) } ?: ""
                    ))
                }
                wb.close()

                if (list.isEmpty()) { _themeState.value = ImportState.Error("Aucune donnée trouvée."); return@launch }

                // insert into Room and get generated ids
                val ids = repo.themeDao.insertAll(list)
                val validIds = ids.filter { it != -1L }
                // map inserted ids back to theme objects
                val syncedThemes = list.zip(validIds).map { (theme, id) -> theme.copy(id = id) }

                val uploadRes = repo.firebase.uploadThemes(syncedThemes)
                if (uploadRes.isSuccess) {
                    repo.themeDao.markSynced(validIds)
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

                val headers = mutableMapOf<String, Int>()
                val headerRow = sheet.getRow(0) ?: run {
                    _collabState.value = ImportState.Error("Fichier vide ou format invalide.")
                    return@launch
                }
                for (cell in headerRow) headers[cell.toString().trim().uppercase()] = cell.columnIndex

                val colMat = col(headers, "MATRICULE", "MLE", "MAT")
                val colNom = col(headers, "NOM", "NAME")
                val colPre = col(headers, "PRENOM", "FIRSTNAME")
                val colSvc = col(headers, "SERVICE", "DIVISION", "DIV", "DEPARTEMENT")
                val colFlm = col(headers, "FLM", "FLMMATRICULE", "MATRICULE_FLM")

                if (colMat == null || colNom == null || colPre == null || colSvc == null) {
                    _collabState.value = ImportState.Error("Colonnes requises non trouvées : Matricule, Nom, Prenom, Service")
                    return@launch
                }

                val list = mutableListOf<CollaborateurEntity>()
                for (i in 1..sheet.lastRowNum) {
                    val row = sheet.getRow(i) ?: continue
                    val mat = cellStr(row, colMat)
                    if (mat.isBlank()) continue
                    list.add(CollaborateurEntity(
                        matricule    = mat,
                        nom          = cellStr(row, colNom),
                        prenom       = cellStr(row, colPre),
                        service      = cellStr(row, colSvc),
                        flmMatricule = colFlm?.let { val v = cellStr(row, it); if (v.isBlank()) null else v }
                    ))
                }
                wb.close()

                if (list.isEmpty()) { _collabState.value = ImportState.Error("Aucune donnée trouvée."); return@launch }

                repo.collaborateurDao.insertAll(list)
                val uploadRes = repo.firebase.uploadCollaborateurs(list)
                if (uploadRes.isSuccess) {
                    repo.collaborateurDao.markSynced(list.map { it.matricule })
                    _collabState.value = ImportState.Success("✅ ${list.size} collaborateur(s) importé(s).")
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

                // Search for header row in first 10 rows
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
                    _flmState.value = ImportState.Error("Colonnes requises non trouvées.")
                    return@launch
                }

                val colMat = col(headers, "MATRICULE", "MLE")!!
                val colNom = col(headers, "NOM", "NAME")!!
                val colPre = col(headers, "PRENOM", "FIRSTNAME")!!
                val colEml = col(headers, "EMAIL", "MAIL", "COURRIEL")!!
                val colSvc = col(headers, "SERVICE", "DIVISION", "DIV")!!

                val dataStartRow = headerRow.rowNum + 1
                val list = mutableListOf<FlmEntity>()

                for (i in dataStartRow..sheet.lastRowNum) {
                    val row = sheet.getRow(i) ?: continue
                    val mat = cellStr(row, colMat)
                    if (mat.isBlank()) continue
                    list.add(FlmEntity(
                        matricule = mat,
                        nom       = cellStr(row, colNom),
                        prenom    = cellStr(row, colPre),
                        email     = cellStr(row, colEml),
                        service   = cellStr(row, colSvc)
                    ))
                }
                wb.close()

                if (list.isEmpty()) {
                    _flmState.value = ImportState.Error("Aucune donnée trouvée.")
                    return@launch
                }

                repo.flmDao.insertAll(list)
                val uploadRes = repo.firebase.uploadFlms(list)
                if (uploadRes.isSuccess) {
                    repo.flmDao.markSynced(list.map { it.matricule })
                    _flmState.value = ImportState.Success("✅ ${list.size} FLM(s) importé(s).")
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

                // dynamic header search (unchanged)
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
                    _bilanState.value = ImportState.Error("Colonnes requises manquantes.")
                    return@launch
                }

                val colMat   = col(headers, "MATRICULE", "MLE", "COLLAB")!!
                val colTheme = col(headers, "THEMEID", "THEME_ID", "THEME")!!
                val colDebut = col(headers, "DEBUT")!!
                val colFin   = col(headers, "FIN")!!

                // Prefetch themes & collaborators (avoid per-row DB access)
                val themes = repo.themeDao.getAll()
                val themeByNom = themes.associateBy { it.nom.trim().uppercase() }
                val themeById  = themes.associateBy { it.id }

                val collabs = repo.collaborateurDao.getAll()
                val collabMatSet = collabs.map { it.matricule }.toSet()

                val formations = mutableListOf<FormationEntity>()
                var skipped = 0

                val dataStartRow = headerRow.rowNum + 1
                for (i in dataStartRow..sheet.lastRowNum) {
                    val row = sheet.getRow(i) ?: continue
                    val mat = cellStr(row, colMat)
                    if (mat.isBlank()) continue

                    val themeRaw = cellStr(row, colTheme).trim()
                    val debut    = cellStr(row, colDebut)
                    val fin      = cellStr(row, colFin)
                    if (debut.isBlank() || fin.isBlank()) continue

                    // more robust numeric parsing: accept "1", "1.0", numeric cells...
                    val themeId = themeRaw.toLongOrNull()
                        ?: themeRaw.toDoubleOrNull()?.toLong()
                        ?: themeByNom[themeRaw.uppercase()]?.id

                    if (themeId == null || !collabMatSet.contains(mat)) {
                        skipped++; continue
                    }

                    formations.add(FormationEntity(
                        collaborateurMatricule = mat,
                        themeId               = themeId,
                        debut                 = debut,
                        fin                   = fin
                    ))
                }
                wb.close()

                if (formations.isEmpty()) {
                    _bilanState.value = ImportState.Error("Aucune formation valide ($skipped ignorées).")
                    return@launch
                }

                // Insert into Room and get generated ids
                val insertIds = repo.formationDao.insertAll(formations).filter { it != -1L }

                // zip formations with returned ids to set formation.id before uploading
                val syncedFormations = formations.zip(insertIds).map { (f, id) -> f.copy(id = id) }

                val uploadRes = repo.firebase.uploadFormations(syncedFormations)
                if (uploadRes.isSuccess) {
                    repo.formationDao.markSynced(insertIds)
                    _bilanState.value = ImportState.Success("✅ ${syncedFormations.size} formation(s) importée(s).")
                } else {
                    _bilanState.value = ImportState.Error("❌ Erreur upload: ${uploadRes.exceptionOrNull()?.message}")
                }

            } catch (e: Exception) {
                _bilanState.value = ImportState.Error("❌ ${e.message}")
            }
        }
    }

}