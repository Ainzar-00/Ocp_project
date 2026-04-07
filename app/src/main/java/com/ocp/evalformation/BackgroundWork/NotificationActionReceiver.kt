package com.ocp.evalformation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import com.ocp.evalformation.data.repository.MainRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NotificationActionReceiver : BroadcastReceiver() {

    @Inject
    lateinit var repo: MainRepository

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "ACTION_SEND_ALL") {
            // Dismiss notification immediately
            NotificationManagerCompat.from(context).cancel(AppreciationDateWorker.NOTIFICATION_ID)

            // Send all pending invitations in background
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val prefs = context.getSharedPreferences("worker_prefs", Context.MODE_PRIVATE)
                    val ids   = prefs.getString("pending_formation_ids", "")
                        ?.split(",")
                        ?.mapNotNull { it.toLongOrNull() }
                        ?: emptyList()

                    val formations = ids.mapNotNull { repo.formationDao.getById(it) }
                    repo.sendAllPendingInvitations(formations)

                    // Clear stored IDs
                    prefs.edit().remove("pending_formation_ids").apply()

                    Log.d("NotifReceiver", "✅ Sent ${formations.size} invitations from notification")
                } catch (e: Exception) {
                    Log.e("NotifReceiver", "Error: ${e.message}", e)
                }
            }
        }
    }
}