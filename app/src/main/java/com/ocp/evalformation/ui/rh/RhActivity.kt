package com.ocp.evalformation.ui.rh

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.google.firebase.auth.FirebaseAuth
import com.ocp.evalformation.R
import com.ocp.evalformation.com.ocp.evalformation.BackgroundWork.AppreciationDateWorker
import com.ocp.evalformation.databinding.ActivityRhBinding
import com.ocp.evalformation.ui.auth.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class RhActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRhBinding
    val viewModel: RhViewModel by viewModels()

    @Inject lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle notification intent
        if (intent.getStringExtra("destination") == "pending_formations") {
            window.decorView.post {
                navigateToInvitations()
            }
        }


        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_rh) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavRh.setupWithNavController(navController)

        binding.btnLogoutRh.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        // Sync pending data on start
        viewModel.syncToFirebase()

        initializeWorker()


    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (intent?.getStringExtra("destination") == "pending_formations") {
            navigateToInvitations()
        }
    }

    private fun navigateToInvitations() {
        // Replace with your actual nav graph action ID
        findNavController(R.id.nav_host_fragment_rh)
            .navigate(R.id.invitationsFragment)
    }

    fun initializeWorker() {

        val periodicRequest = PeriodicWorkRequest.Builder(
            AppreciationDateWorker::class.java,
            1,
            TimeUnit.DAYS
        )
            .setInitialDelay(2, TimeUnit.MINUTES)
            .addTag("APPRECIATION_WORKER")
            .build()

        val workManager = WorkManager.getInstance(this)

        workManager.enqueueUniquePeriodicWork(
            "Notification worker",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicRequest
        )

        workManager.getWorkInfosForUniqueWorkLiveData("Notification worker")
            .observe(this) { workInfos ->
                workInfos.forEach { workInfo ->
                    Log.d("WorkerTracker", "📊 ID: ${workInfo.id}")
                    Log.d("WorkerTracker", "📌 State: ${workInfo.state}")
                    Log.d("WorkerTracker", "🔁 Run attempt: ${workInfo.runAttemptCount}")
                    Log.d("WorkerTracker", "📤 Output: ${workInfo.outputData}")
                }
            }
    }

}
