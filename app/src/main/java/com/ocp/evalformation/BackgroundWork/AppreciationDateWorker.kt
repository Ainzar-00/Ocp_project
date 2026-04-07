package com.ocp.evalformation

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.ocp.evalformation.R
import com.ocp.evalformation.data.local.entity.InvitationFlmEntity
import com.ocp.evalformation.data.repository.MainRepository
import com.ocp.evalformation.ui.auth.LoginActivity
import com.ocp.evalformation.ui.rh.RhActivity
import com.ocp.evalformation.utils.dateHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.Calendar
import java.util.jar.Manifest

@HiltWorker
class AppreciationDateWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repo: MainRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return try {
            // ── Get today as Excel serial date ────────────────────
            val today = getTodayAsExcelDate()+1
            Log.d("WorkerTest", "today (excel): $today")
            Log.d("WorkerTest", "today (normal): ${java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault()).format(java.util.Date())}")



            val invitations= repo.invitationDao.getAll().map { it.formationId }
            val formations = repo.formationDao.getAll()
                .filter {
                !invitations.contains(it.id)
            }

            Log.d("WorkerTest", "total formations: ${formations.size}")

            formations.forEach { f ->
                Log.d("WorkerTest", "id=${f.id} dateAppreciation='${f.dateAppreciation}' parsed=${f.dateAppreciation.toDoubleOrNull()?.toLong()}")
            }

            val matching = formations.filter { formation ->
                val appDate = formation.dateAppreciation.toIntOrNull()?.toLong()
                val match   = appDate!! <= today
                Log.d("WorkerTest", "id=${formation.id} appDate=$appDate today=$today → match=$match")
                match
            }

            Log.d("WorkerTest", "matching formations: ${matching.size}")

            if (matching.isEmpty()) {
                Log.d("WorkerTest", "No matching formations — done")
                return Result.success()
            }

            showNotification(matching.size)

            val ids = matching.map { it.id }.joinToString(",")

            applicationContext
                .getSharedPreferences("worker_prefs", Context.MODE_PRIVATE)
                .edit()
                .putString("pending_formation_ids", ids)
                .apply()

            Log.d("WorkerTest", "✅ Stored IDs: $ids")
            Result.success()

        } catch (e: Exception) {
            Log.e("WorkerTest", "Worker failed: ${e.message}", e)
            Result.failure()
        }
    }

    // ── Convert today's date to Excel serial number ────────────────
    private fun getTodayAsExcelDate(): Long {
        val cal = Calendar.getInstance()

        // Excel epoch starts Jan 1, 1900
        // Java epoch starts Jan 1, 1970
        val todayMillis = cal.timeInMillis
        val excelEpoch  = 25569L
        val oneDayMs    = 86400000L
        return (todayMillis / oneDayMs) + excelEpoch
    }


    private fun showNotification(count: Int) {
        val channelId = "appreciation_channel"

        val sendAllIntent = Intent(applicationContext, NotificationActionReceiver::class.java)
            .apply { action = "ACTION_SEND_ALL" }

        val sendAllPendingIntent = PendingIntent.getBroadcast(
            applicationContext, 0, sendAllIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val openAppIntent = Intent(applicationContext, RhActivity::class.java).apply {
            putExtra("destination", "pending_formations")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
        }

        val openAppPendingIntent = PendingIntent.getActivity(
            applicationContext, 1, openAppIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("📋 Évaluations à envoyer")
            .setContentText("$count formation(s) arrivent à date d'appréciation.")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("$count formation(s) arrivent à date d'appréciation. Cliquez pour envoyer les invitations aux FLMs."))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOngoing(true)
            .setAutoCancel(false)
            .setContentIntent(openAppPendingIntent)
            .addAction(R.drawable.ic_notification, "Envoyer tout", sendAllPendingIntent)
            .build()

        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            NotificationManagerCompat.from(applicationContext)
                .notify(NOTIFICATION_ID, notification)
        }
    }

    companion object {
        const val NOTIFICATION_ID = 1001
    }
}