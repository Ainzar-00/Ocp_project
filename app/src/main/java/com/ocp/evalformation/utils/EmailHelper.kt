package com.ocp.evalformation.utils

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object EmailHelper {

    // ── Config ─────────────────────────────────────────────────
    private const val SMTP_HOST     = "smtp.gmail.com"
    private const val SMTP_PORT     = "587"
    private const val SENDER_EMAIL  = "gremin11111@gmail.com"   // ← your dedicated Gmail
    private const val SENDER_PASS   = "hbfc fpjl llsp qcim"     // ← your App Password

    // ── Send email (must be called from coroutine) ─────────────

    fun buildGroupedInvitationBody(
        flmNom       : String,
        collaborateur: String,
        formations   : List<Pair<String, String>> // themeNom to formUrl
    ): String {
        val formationsBlock = formations.joinToString("\n\n") { (theme, url) ->
            """  Thème : $theme
            Lien  : $url"""
        }

        return """
        Bonjour $flmNom,

        Vous êtes invité(e) à évaluer votre collaborateur suite aux formations suivies.

        ─────────────────────────────
        Collaborateur : $collaborateur
        ─────────────────────────────

        Veuillez remplir les formulaires d'évaluation via les liens ci-dessous :

$formationsBlock

        Merci de bien vouloir compléter ces formulaires dans les plus brefs délais.

        Cordialement,
        Service RH — OCP
    """.trimIndent()
    }

    suspend fun sendEmail(
        to      : String,
        subject : String,
        body    : String
    ): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val props = Properties().apply {
                put("mail.smtp.auth",            "true")
                put("mail.smtp.starttls.enable", "true")
                put("mail.smtp.host",             SMTP_HOST)
                put("mail.smtp.port",             SMTP_PORT)
                put("mail.smtp.ssl.trust",        SMTP_HOST)
            }

            val session = Session.getInstance(props, object : Authenticator() {
                override fun getPasswordAuthentication() =
                    PasswordAuthentication(SENDER_EMAIL, SENDER_PASS)
            })

            val message = MimeMessage(session).apply {
                setFrom(InternetAddress(SENDER_EMAIL, "OCP Évaluation Formation"))
                setRecipients(Message.RecipientType.TO, InternetAddress.parse(to))
                setSubject(subject, "UTF-8")
                setText(body, "UTF-8")
            }

            Transport.send(message)
            Log.i("EmailHelper", "✅ Email sent to $to")
            Result.success(Unit)

        } catch (e: Exception) {
            Log.e("EmailHelper", "❌ Email failed: ${e.message}", e)
            Result.failure(e)
        }
    }

    // ── Build invitation email body ────────────────────────────
    fun buildInvitationBody(
        flmNom      : String,
        themeNom    : String,
        collaborateur: String,
        formUrl     : String
    ): String {
        return """
            Bonjour $flmNom,

            Vous êtes invité(e) à évaluer votre collaborateur suite à la formation suivie.

            ─────────────────────────────
            Collaborateur : $collaborateur
            Thème         : $themeNom
            ─────────────────────────────

            Veuillez remplir le formulaire d'évaluation via le lien ci-dessous :

            👉 $formUrl

            Merci de bien vouloir compléter ce formulaire dans les plus brefs délais.

            Cordialement,
            Service RH — OCP
        """.trimIndent()
    }
}