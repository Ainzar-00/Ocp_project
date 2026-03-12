package com.ocp.evalformation.ui.flm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.ocp.evalformation.data.local.entity.CollaborateurEntity
import com.ocp.evalformation.data.local.entity.FormationEntity
import com.ocp.evalformation.data.repository.MainRepository
import com.ocp.evalformation.databinding.ActivityFlmBinding
import com.ocp.evalformation.ui.auth.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CollabAvecFormation(
    val collaborateur: CollaborateurEntity,
    val formation: FormationEntity?
)

@HiltViewModel
class FlmViewModel @Inject constructor(
    private val repo: MainRepository
) : ViewModel() {

    sealed class SearchState {
        object Idle    : SearchState()
        object Loading : SearchState()
        data class Found(val data: CollabAvecFormation) : SearchState()
        data class NotFound(val matricule: String)      : SearchState()
        data class Error(val message: String)           : SearchState()
    }

    private val _searchState = MutableStateFlow<SearchState>(SearchState.Idle)
    val searchState: StateFlow<SearchState> = _searchState

    fun searchByMatricule(matricule: String) {
        viewModelScope.launch {
            _searchState.value = SearchState.Loading
            val collab = repo.collaborateurDao.getByMatricule(matricule)
            if (collab == null) {
                _searchState.value = SearchState.NotFound(matricule)
                return@launch
            }
            val formation = repo.formationDao.getByCollaborateur(matricule).firstOrNull()
            _searchState.value = SearchState.Found(CollabAvecFormation(collab, formation))
        }
    }
}

@AndroidEntryPoint
class FlmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlmBinding
    private val viewModel: FlmViewModel by viewModels()

    @Inject lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // btn_logout_flm → binding.btnLogoutFlm
        binding.btnLogoutFlm.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // btn_rechercher → binding.btnRechercher
        binding.btnRechercher.setOnClickListener {
            val matricule = binding.etMatriculeFlm.text?.toString()?.trim() ?: ""
            if (matricule.isBlank()) {
                Toast.makeText(this, "Veuillez saisir un matricule", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.searchByMatricule(matricule)
        }

        lifecycleScope.launch {
            viewModel.searchState.collect { state ->
                when (state) {
                    is FlmViewModel.SearchState.Loading -> {
                        binding.btnRechercher.isEnabled = false
                    }
                    is FlmViewModel.SearchState.Found -> {
                        binding.btnRechercher.isEnabled = true
                        displayCollab(state.data)
                    }
                    is FlmViewModel.SearchState.NotFound -> {
                        binding.btnRechercher.isEnabled = true
                        Toast.makeText(
                            this@FlmActivity,
                            "Collaborateur « ${state.matricule} » non trouvé",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is FlmViewModel.SearchState.Error -> {
                        binding.btnRechercher.isEnabled = true
                        Toast.makeText(this@FlmActivity, state.message, Toast.LENGTH_SHORT).show()
                    }
                    is FlmViewModel.SearchState.Idle -> Unit
                }
            }
        }
    }

    private fun displayCollab(data: CollabAvecFormation) {
        val c = data.collaborateur
        val f = data.formation

        // card_collab_info → binding.cardCollabInfo
        binding.cardCollabInfo.visibility = View.VISIBLE

        // tv_collab_nom_flm → binding.tvCollabNomFlm
        binding.tvCollabNomFlm.text = "${c.prenom} ${c.nom}"

        // tv_collab_matricule → binding.tvCollabMatricule
        binding.tvCollabMatricule.text = "Matricule : ${c.matricule}"

        // tv_collab_entite → binding.tvCollabEntite
        binding.tvCollabEntite.text = "Service : ${c.service}"

        // tv_collab_theme → binding.tvCollabTheme
        binding.tvCollabTheme.text = "📚 Formation ID : ${f?.id ?: "—"}"

        // tv_collab_formateur → binding.tvCollabFormateur
        binding.tvCollabFormateur.text = "Début : ${f?.debut ?: "—"}"

        // tv_collab_mois → binding.tvCollabMois
        binding.tvCollabMois.text = "Fin : ${f?.fin ?: "—"}"

        // tv_collab_objectifs → binding.tvCollabObjectifs
        binding.tvCollabObjectifs.text = "FLM : ${c.flmMatricule ?: "—"}"

        // tv_form_url → binding.tvFormUrl (empty for now)
        binding.tvFormUrl.text = ""

        // btn_ouvrir_formulaire and btn_copier_lien stay gone until form URL is available
        binding.btnOuvrirFormulaire.visibility = View.GONE
        binding.btnCopierLien.visibility = View.GONE
    }
}
