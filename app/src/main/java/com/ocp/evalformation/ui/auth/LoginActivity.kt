package com.ocp.evalformation.ui.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.ocp.evalformation.R
import com.ocp.evalformation.data.repository.syncRepository
import com.ocp.evalformation.databinding.ActivityLoginBinding
import com.ocp.evalformation.model.UserRole
import com.ocp.evalformation.ui.flm.FlmActivity
import com.ocp.evalformation.ui.rh.RhActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

// ─────────────────────────────────────────────
// ViewModel with Full Debug
// ─────────────────────────────────────────────
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val syncRepository: syncRepository
) : ViewModel() {

    sealed class LoginState {
        object Idle : LoginState()
        object Loading : LoginState()
        object Syncing : LoginState()
        data class Success(val role: UserRole) : LoginState()
        data class Error(val message: String) : LoginState()
    }

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state

    fun isAlreadyLoggedIn() = auth.currentUser != null

    suspend fun getCurrentUserRole(): UserRole? {
        val uid = auth.currentUser?.uid
        val email = auth.currentUser?.email
        Log.d("DEBUG_FIREBASE", "getCurrentUserRole → UID=$uid | email=$email")
        if (uid == null) {
            Log.w("DEBUG_FIREBASE", "getCurrentUserRole → UID is null, returning null")
            return null
        }

        return try {
            Log.d("DEBUG_FIRESTORE", "Fetching users/$uid from Firestore...")
            val doc = firestore.collection("users").document(uid).get().await()
            Log.d("DEBUG_FIRESTORE", "Doc fetched → exists=${doc.exists()} | data=${doc.data}")

            if (!doc.exists()) {
                Log.w("DEBUG_FIRESTORE", "Doc does NOT exist for UID=$uid")
                return null
            }

            val roleStr = doc.getString("role")
            Log.d("DEBUG_FIRESTORE", "Role string from Firestore: '$roleStr'")

            if (roleStr == null) {
                Log.w("DEBUG_FIRESTORE", "'role' field is missing or null in document!")
                return UserRole.FLM
            }

            val role = runCatching { UserRole.valueOf(roleStr) }.getOrDefault(UserRole.FLM)
            Log.d("DEBUG_FIRESTORE", "Resolved UserRole: $role")
            role
        } catch (e: Exception) {
            Log.e("DEBUG_FIRESTORE", "Exception fetching user role: ${e::class.simpleName} → ${e.message}", e)
            null
        }
    }

    fun syncAndNavigate(role: UserRole) {
        viewModelScope.launch {
            _state.value = LoginState.Syncing
            try {
                Log.d("DEBUG_SYNC", "syncAndNavigate → role=$role, starting sync...")
                syncRepository.syncIfEmpty()
                Log.d("DEBUG_SYNC", "syncAndNavigate → sync completed OK")
            } catch (e: Exception) {
                Log.e("DEBUG_SYNC", "syncAndNavigate → sync FAILED: ${e::class.simpleName} → ${e.message}", e)
            } finally {
                Log.d("DEBUG_SYNC", "syncAndNavigate → emitting Success(role=$role)")
                _state.value = LoginState.Success(role)
            }
        }
    }

    fun firebaseAuthWithGoogle(idToken: String) {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            try {
                Log.d("DEBUG_FIREBASE", "firebaseAuthWithGoogle → idToken length=${idToken.length}")
                Log.d("DEBUG_FIREBASE", "firebaseAuthWithGoogle → idToken preview=${idToken.take(20)}...")

                val credential = GoogleAuthProvider.getCredential(idToken, null)
                Log.d("DEBUG_FIREBASE", "Credential created, calling signInWithCredential...")

                val result = auth.signInWithCredential(credential).await()
                val user = result.user

                Log.d("DEBUG_FIREBASE", "signInWithCredential → uid=${user?.uid} | email=${user?.email} | isNew=${result.additionalUserInfo?.isNewUser}")

                val uid = user?.uid ?: throw Exception("UID introuvable après signIn")
                val isNewUser = result.additionalUserInfo?.isNewUser ?: false
                Log.d("DEBUG_REGISTER", "Is new user: $isNewUser")

                val role = fetchOrCreateUserRole(uid, isNewUser)
                Log.d("DEBUG_REGISTER", "Role after fetchOrCreate: $role")

                _state.value = LoginState.Syncing
                try {
                    Log.d("DEBUG_SYNC", "Post-login sync starting...")
                    syncRepository.syncIfEmpty()
                    Log.d("DEBUG_SYNC", "Post-login sync completed OK")
                } catch (e: Exception) {
                    Log.e("DEBUG_SYNC", "Post-login sync FAILED: ${e::class.simpleName} → ${e.message}", e)
                }

                Log.d("DEBUG_FIREBASE", "Emitting Success(role=$role)")
                _state.value = LoginState.Success(role)

            } catch (e: Exception) {
                Log.e("DEBUG_FIREBASE", "firebaseAuthWithGoogle FAILED: ${e::class.simpleName} → ${e.message}", e)
                auth.signOut()
                _state.value = LoginState.Error("Erreur de connexion : ${e.message}")
            }
        }
    }

    private suspend fun fetchOrCreateUserRole(uid: String, isNewUser: Boolean = false): UserRole {
        Log.d("DEBUG_REGISTER", "fetchOrCreateUserRole → uid=$uid | isNewUser=$isNewUser")
        return try {
            val docRef = firestore.collection("users").document(uid)
            Log.d("DEBUG_REGISTER", "Fetching Firestore doc at users/$uid...")

            val doc = try {
                docRef.get().await().also {
                    Log.d("DEBUG_REGISTER", "Doc fetched → exists=${it.exists()} | data=${it.data}")
                }
            } catch (e: Exception) {
                Log.e("DEBUG_REGISTER", "Failed to fetch doc: ${e::class.simpleName} → ${e.message}", e)
                null
            }

            when {
                doc == null -> {
                    Log.w("DEBUG_REGISTER", "Doc is null (fetch threw exception) → defaulting to FLM")
                    UserRole.FLM
                }
                !doc.exists() -> {
                    Log.d("DEBUG_REGISTER", "Doc does NOT exist → creating new user document with role=FLM")
                    try {
                        val userData = mapOf(
                            "role" to "FLM",
                            "uid" to uid,
                            "createdAt" to com.google.firebase.Timestamp.now()
                        )
                        Log.d("DEBUG_REGISTER", "Writing to Firestore: $userData")
                        docRef.set(userData).await()
                        Log.d("DEBUG_REGISTER", "New user document written successfully ✓")
                    } catch (e: Exception) {
                        Log.e("DEBUG_REGISTER", "FAILED to write new user doc: ${e::class.simpleName} → ${e.message}", e)
                    }
                    UserRole.FLM
                }
                else -> {
                    val roleStr = doc.getString("role")
                    Log.d("DEBUG_REGISTER", "Existing doc → roleStr='$roleStr'")
                    if (roleStr == null) {
                        Log.w("DEBUG_REGISTER", "'role' field missing in existing doc! data=${doc.data}")
                    }
                    val role = runCatching { UserRole.valueOf(roleStr ?: "FLM") }.getOrElse {
                        Log.e("DEBUG_REGISTER", "Failed to parse role '$roleStr': ${it.message} → defaulting to FLM")
                        UserRole.FLM
                    }
                    Log.d("DEBUG_REGISTER", "Resolved role: $role")
                    role
                }
            }
        } catch (e: Exception) {
            Log.e("DEBUG_REGISTER", "Unexpected error in fetchOrCreateUserRole: ${e::class.simpleName} → ${e.message}", e)
            UserRole.FLM
        }
    }
}

// ─────────────────────────────────────────────
// Activity with Full Debug
// ─────────────────────────────────────────────
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var googleSignInClient: GoogleSignInClient

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.d("DEBUG_GOOGLE", "ActivityResult received → resultCode=${result.resultCode}")
        if (result.resultCode == Activity.RESULT_OK) {
            Log.d("DEBUG_GOOGLE", "Result OK → extracting signed-in account...")
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                Log.d("DEBUG_GOOGLE", "Account retrieved → email=${account.email} | id=${account.id} | idToken=${if (account.idToken != null) "present (${account.idToken!!.length} chars)" else "NULL ⚠️"}")

                val idToken = account.idToken
                    ?: return@registerForActivityResult showError("Token Google introuvable.")

                viewModel.firebaseAuthWithGoogle(idToken)

            } catch (e: ApiException) {
                Log.e("DEBUG_GOOGLE", "ApiException → statusCode=${e.statusCode} | message=${e.message}", e)
                Log.e("DEBUG_GOOGLE", "Common codes → 10=Developer error (SHA/OAuth mismatch) | 12500=Sign-in cancelled | 7=Network error")
                showError("Connexion Google annulée ou échouée (code ${e.statusCode}).")
            }
        } else {
            Log.w("DEBUG_GOOGLE", "Result NOT OK → resultCode=${result.resultCode} | data=${result.data}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("DEBUG_UI", "LoginActivity onCreate")

        val webClientId = getString(R.string.default_web_client_id)
        Log.d("DEBUG_GOOGLE", "Using web client ID: $webClientId")

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        Log.d("DEBUG_GOOGLE", "GoogleSignInClient initialized")

        setupUI()

        if (viewModel.isAlreadyLoggedIn()) {
            val uid = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.uid
            Log.d("DEBUG_FIREBASE", "User already logged in → UID=$uid, fetching role...")
            lifecycleScope.launch {
                val role = viewModel.getCurrentUserRole()
                if (role != null) {
                    Log.d("DEBUG_FIREBASE", "Existing session role=$role → syncing and navigating")
                    viewModel.syncAndNavigate(role)
                } else {
                    Log.w("DEBUG_FIREBASE", "Role returned null for existing session → staying on login screen")
                    setLoading(false)
                }
            }
        } else {
            Log.d("DEBUG_FIREBASE", "No existing session → showing login screen")
        }
    }

    private fun setupUI() {
        binding.btnLogin.setOnClickListener {
            Log.d("DEBUG_UI", "Login button clicked → signing out previous Google session first")
            googleSignInClient.signOut().addOnCompleteListener { task ->
                Log.d("DEBUG_UI", "GoogleSignOut complete → success=${task.isSuccessful} | launching sign-in intent")
                googleSignInLauncher.launch(googleSignInClient.signInIntent)
            }
        }

        lifecycleScope.launch {
            viewModel.state.collect { state ->
                Log.d("DEBUG_STATE", "State changed → $state")
                when (state) {
                    is LoginViewModel.LoginState.Idle    -> setLoading(false)
                    is LoginViewModel.LoginState.Loading -> setLoading(true, "Connexion en cours...")
                    is LoginViewModel.LoginState.Syncing -> setLoading(true, "Synchronisation en cours...")
                    is LoginViewModel.LoginState.Success -> {
                        setLoading(false)
                        Log.d("DEBUG_NAV", "Navigating to role=${state.role}")
                        navigateToRole(state.role)
                    }
                    is LoginViewModel.LoginState.Error   -> {
                        setLoading(false)
                        Log.e("DEBUG_NAV", "Login error state: ${state.message}")
                        showError(state.message)
                    }
                }
            }
        }
    }

    private fun setLoading(loading: Boolean, message: String = "") {
        Log.d("DEBUG_UI", "setLoading → loading=$loading | message='$message'")
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        binding.btnLogin.isEnabled = !loading
        binding.tvError.visibility = View.GONE
    }

    private fun showError(message: String) {
        Log.e("DEBUG_UI", "showError → $message")
        binding.tvError.text = message
        binding.tvError.visibility = View.VISIBLE
    }

    private fun navigateToRole(role: UserRole) {
        Log.d("DEBUG_NAV", "navigateToRole → $role")
        val intent = when (role) {
            UserRole.RH, UserRole.ADMIN -> Intent(this, RhActivity::class.java)
            UserRole.FLM                -> Intent(this, FlmActivity::class.java)
        }
        Log.d("DEBUG_NAV", "Starting activity: ${intent.component?.className}")
        startActivity(intent)
        finish()
    }
}