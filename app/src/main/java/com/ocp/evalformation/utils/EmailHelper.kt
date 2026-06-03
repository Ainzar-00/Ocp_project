package com.ocp.evalformation.utils

import android.util.Log
import com.ocp.evalformation.BuildConfig
import io.github.cdimascio.dotenv.dotenv
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
    val dotenv = dotenv()
    // ── Config ─────────────────────────────────────────────────
    private const val SMTP_HOST     = "smtp.gmail.com"
    private const val SMTP_PORT     = "587"

    private val SENDER_EMAIL  = dotenv["SENDER_EMAIL"]
    private val SENDER_PASS   = dotenv["SENDER_PASS"]

    // ── Send email (must be called from coroutine) ─────────────

    fun buildGroupedInvitationBody(
        flmNom       : String,
        collaborateur: String,
        formations   : List<Pair<String, String>> // themeNom to formUrl
    ): String {
        val formationsBlock = formations.joinToString("\n\n") { (theme, url) ->
            """ 
                ──────────────────────────────────────────────────────────────────
                 Thème : $theme
                ──────────────────────────────────────────────────────────────────
                 Lien  : $url"""
        }

        return """
            
        Bonjour M. $flmNom;

        Dans le cadre de l’évaluation des formations dispensées à vos collaborateurs, nous vous remercions par avance de bien vouloir renseigner le(s) formulaire(s) ci-dessous.
        
       
        
        ─────────────────────────────
        Collaborateur Concerné(es): $collaborateur
        ─────────────────────────────



        $formationsBlock

        

        Meilleures Salutations,

        BELKACIM Mohamed

        Chargé de développement RH OE/TAMCA Safi

        0 661 690 470
        
    """.trimIndent()
    }

    suspend fun sendEmail(
        to: String,
        cc: String? = null,
        subject: String,
        body: String
    ): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val props = Properties().apply {
                put("mail.smtp.auth", "true")
                put("mail.smtp.starttls.enable", "true")
                put("mail.smtp.host", SMTP_HOST)
                put("mail.smtp.port", SMTP_PORT)
                put("mail.smtp.ssl.trust", SMTP_HOST)
            }

            val session = Session.getInstance(props, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    Log.d("sender",SENDER_EMAIL)
                    return PasswordAuthentication(SENDER_EMAIL, SENDER_PASS)
                }
            })

            val message = MimeMessage(session).apply {
                setFrom(InternetAddress(SENDER_EMAIL, "OCP Évaluation Formation"))
                setRecipients(Message.RecipientType.TO, InternetAddress.parse(to))

                if (!cc.isNullOrBlank()) {
                    setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc))
                }

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
            Bonjour M. $flmNom;

            Dans le cadre de l’évaluation des formations dispensées à vos collaborateurs, nous vous remercions par avance de bien vouloir renseigner le(s) formulaire(s) ci-dessous.


            ────────────────────────────────────────────
            Collaborateur Concerné(es): $collaborateur
            Thème         : $themeNom
            ────────────────────────────────────────────



            👉 $formUrl



            
            Meilleures Salutations,

            BELKACIM Mohamed

            Chargé de développement RH OE/TAMCA Safi

            0 661 690 470
        """.trimIndent()
    }
}