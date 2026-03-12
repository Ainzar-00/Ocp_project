package com.ocp.evalformation.utils

import android.util.Log

/**
 * GoogleFormHelper — placeholder for Google Form integration
 */
object GoogleFormHelper {
    fun buildFormUrl(formationId: Long, collaborateurMatricule: String): String {
        Log.i("GoogleFormHelper", "Building form URL for formation $formationId")
        return "https://forms.google.com/"  // placeholder
    }
}
