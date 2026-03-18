package com.ocp.evalformation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.ocp.evalformation.R
import com.ocp.evalformation.data.repository.syncRepository
import com.ocp.evalformation.ui.auth.LoginActivity
import com.ocp.evalformation.ui.auth.LoginViewModel
import com.ocp.evalformation.ui.rh.RhActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.getValue



@HiltViewModel
class SplashViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val syncRepository: syncRepository
) : ViewModel() {

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    suspend fun syncIfNeeded() {
        try {
            syncRepository.syncIfEmpty()
        } catch (e: Exception) {
            // optional: log error
        }
    }
}

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.tv_logo)
        val title = findViewById<TextView>(R.id.tv_title)
        val subtitle = findViewById<TextView>(R.id.tv_subtitle)
        val footerSeparator = findViewById<View>(R.id.footer_separator)
        val bottomLabel = findViewById<TextView>(R.id.bottom_label)

        // Initial state
        logo.alpha = 1f
        logo.translationY = 0f
        logo.scaleX = 1f
        logo.scaleY = 1f
        title.alpha = 1f
        title.translationY = 0f
        subtitle.alpha = 1f
        subtitle.translationY = 0f
        footerSeparator.alpha = 1f
        bottomLabel.alpha = 1f

        // Start animation
        logo.post {

            title.animate()
                .translationY(-100f)
                .alpha(0f)
                .setDuration(500)
                .setInterpolator(AccelerateInterpolator())
                .start()

            subtitle.animate()
                .translationY(-100f)
                .alpha(0f)
                .setDuration(500)
                .setInterpolator(AccelerateInterpolator())
                .start()

            footerSeparator.animate().alpha(0f).setDuration(300).start()
            bottomLabel.animate().alpha(0f).setDuration(300).start()

            logo.animate()
                .translationY(200f)
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(500)
                .setInterpolator(AccelerateInterpolator())
                .withEndAction {
                    // ✅ CRITICAL: wait next frame → no lag / no black screen
                    logo.post {
                        handleNavigation()
                    }
                }
                .start()
        }
    }

    private fun handleNavigation() {

        if (viewModel.isLoggedIn()) {

            // ✅ Navigate immediately
            startActivity(Intent(this, RhActivity::class.java))

            // ✅ Sync in background (non-blocking)
            lifecycleScope.launch {
                viewModel.syncIfNeeded()
            }

        } else {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // ✅ Apply transition AFTER startActivity
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        // ✅ Finish ONLY here
        finish()
    }
}

