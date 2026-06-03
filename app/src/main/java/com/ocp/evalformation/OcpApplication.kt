package com.ocp.evalformation

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context

import android.os.Build

import androidx.hilt.work.HiltWorkerFactory

import androidx.work.Configuration

import dagger.hilt.android.HiltAndroidApp

import javax.inject.Inject

@HiltAndroidApp
class OcpApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "appreciation_channel",
                "Dates d'appréciation",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifications pour les dates d'évaluation"
            }
            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}