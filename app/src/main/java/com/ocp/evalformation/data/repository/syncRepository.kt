package com.ocp.evalformation.data.repository

import android.util.Log
import com.ocp.evalformation.data.local.dao.CollaborateurDao
import com.ocp.evalformation.data.local.dao.FlmDao
import com.ocp.evalformation.data.local.dao.FormationDao
import com.ocp.evalformation.data.local.dao.ThemeDao
import com.ocp.evalformation.data.remote.FirebaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class syncRepository @Inject constructor(
    private val firebase: FirebaseRepository,
    private val themeDao: ThemeDao,
    private val flmDao: FlmDao,
    private val collaborateurDao: CollaborateurDao,
    private val formationDao: FormationDao
) {

    suspend fun syncIfEmpty() = withContext(Dispatchers.IO) {

        Log.d("SYNC", "━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
        Log.d("SYNC", "Starting sync...")

        val themesCount = themeDao.count()
        val flmsCount = flmDao.count()
        val collabsCount = collaborateurDao.count()
        val formationsCount = formationDao.count()

        Log.d("SYNC", "Room DB status:")
        Log.d("SYNC", "Themes: $themesCount")
        Log.d("SYNC", "FLMs: $flmsCount")
        Log.d("SYNC", "Collaborateurs: $collabsCount")
        Log.d("SYNC", "Formations: $formationsCount")


        // ───────── THEMES ─────────
        if (themesCount == 0) {

            Log.d("SYNC", "Fetching THEMES from Firebase...")

            val list = firebase.fetchThemes()

            Log.d("SYNC", "Themes fetched from Firebase: ${list.size}")

            list.take(3).forEach {
                Log.d("SYNC", "Theme sample: $it")
            }

            if (list.isNotEmpty()) {

                themeDao.insertAll(list)

                val newCount = themeDao.count()
                Log.d("SYNC", "Themes inserted into Room. New count: $newCount")

            } else {
                Log.w("SYNC", "No themes received from Firebase")
            }
        }


        // ───────── FLMS ─────────
        if (flmsCount == 0) {

            Log.d("SYNC", "Fetching FLMs from Firebase...")

            val list = firebase.fetchFlms()

            Log.d("SYNC", "FLMs fetched: ${list.size}")

            list.take(3).forEach {
                Log.d("SYNC", "FLM sample: $it")
            }

            if (list.isNotEmpty()) {

                flmDao.insertAll(list)

                val newCount = flmDao.count()
                Log.d("SYNC", "FLMs inserted into Room. New count: $newCount")

            } else {
                Log.w("SYNC", "No FLMs received from Firebase")
            }
        }


        // ───────── COLLABORATEURS ─────────
        if (collabsCount == 0) {

            Log.d("SYNC", "Fetching COLLABORATEURS from Firebase...")

            val list = firebase.fetchCollaborateurs()

            Log.d("SYNC", "Collaborateurs fetched: ${list.size}")

            list.take(5).forEach {
                Log.d("SYNC", "Collaborateur sample: $it")
            }

            if (list.isNotEmpty()) {

                Log.d("SYNC", "Inserting collaborateurs into Room...")

                try {
                    collaborateurDao.insertAll(list)
                    Log.d("SYNC", "Collaborateurs inserted")

                } catch (e: Exception) {
                    Log.e("SYNC", "INSERT ERROR", e)
                }

                Log.d("SYNC", "Insert finished.")

                val newCount = collaborateurDao.count()

                Log.d("SYNC", "Collaborateurs count AFTER insert: $newCount")

            } else {
                Log.w("SYNC", "No collaborateurs received from Firebase")
            }
        }


        // ───────── FORMATIONS ─────────
        if (formationsCount == 0) {

            Log.d("SYNC", "Fetching FORMATIONS from Firebase...")

            val list = firebase.fetchFormations()

            Log.d("SYNC", "Formations fetched: ${list.size}")

            list.take(3).forEach {
                Log.d("SYNC", "Formation sample: $it")
            }

            if (list.isNotEmpty()) {

                Log.d("SYNC", "Inserting formations into Room...")
                Log.d("SYNC", "Inserting formations: ${list.size}")
                formationDao.insertAll(list)

                val newCount = formationDao.count()

                Log.d("SYNC", "Formations inserted. New count: $newCount")

            } else {
                Log.w("SYNC", "No formations received from Firebase")
            }
        }

        Log.d("SYNC", "Sync complete.")
        Log.d("SYNC", "━━━━━━━━━━━━━━━━━━━━━━━━━━━━")
    }

    suspend fun getFormations() {
        val savedFormations = formationDao.getAll()
        logLong("Formations", savedFormations.toString())
    }

    /**
     * Logs long messages in chunks to avoid Logcat truncation.
     */
    fun logLong(tag: String, msg: String) {
        val maxLength = 1000 // chunk size, you can increase up to 4000
        var start = 0
        while (start < msg.length) {
            val end = (start + maxLength).coerceAtMost(msg.length)
            Log.d(tag, msg.substring(start, end))
            start = end
        }
    }

}