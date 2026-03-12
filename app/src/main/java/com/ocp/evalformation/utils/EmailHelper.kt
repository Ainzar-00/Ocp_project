package com.ocp.evalformation.utils

import android.content.Context
import android.util.Log

/**
 * EmailHelper — placeholder for future email sending feature
 */
object EmailHelper {
    fun sendEmail(context: Context, to: String, subject: String, body: String) {
        Log.i("EmailHelper", "Email to $to: $subject")
    }
}
