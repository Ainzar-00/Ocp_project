package com.ocp.evalformation.com.ocp.evalformation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ocp.evalformation.R
import com.ocp.evalformation.ui.auth.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        Log.d("TIMING", "4. SplashActivity.onCreate START ${System.currentTimeMillis()}")
        super.onCreate(savedInstanceState)
        Log.d("TIMING", "5. SplashActivity.onCreate after super ${System.currentTimeMillis()}")
        setContentView(R.layout.activity_splash)
        Log.d("TIMING", "6. SplashActivity.setContentView DONE ${System.currentTimeMillis()}")

        setContentView(R.layout.activity_splash)

        val logo     = findViewById<TextView>(R.id.tv_logo)
        val title    = findViewById<TextView>(R.id.tv_title)
        val subtitle = findViewById<TextView>(R.id.tv_subtitle)

        // Staggered fade-in
        logo.animate().alpha(1f).setDuration(500).setStartDelay(100).start()
        title.animate().alpha(1f).setDuration(500).setStartDelay(350).start()
        subtitle.animate().alpha(1f).setDuration(500).setStartDelay(550)
            .withEndAction {
                // After animation ends, wait a bit then go to login
                subtitle.postDelayed({
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }, 800)
            }.start()
    }

    override fun onStart() {
        super.onStart()
        Log.d("TIMING", "7. SplashActivity.onStart ${System.currentTimeMillis()}")
    }

    override fun onResume() {
        super.onResume()
        Log.d("TIMING", "8. SplashActivity.onResume ${System.currentTimeMillis()}")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.d("TIMING", "9. SplashActivity.onWindowFocusChanged hasFocus=$hasFocus ${System.currentTimeMillis()}")
    }
}