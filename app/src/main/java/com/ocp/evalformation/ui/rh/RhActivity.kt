package com.ocp.evalformation.ui.rh

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.ocp.evalformation.R
import com.ocp.evalformation.databinding.ActivityRhBinding
import com.ocp.evalformation.ui.auth.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
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
    }
}
