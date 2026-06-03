package com.ocp.evalformation.utils;

import android.util.Log;
import com.ocp.evalformation.BuildConfig;
import kotlinx.coroutines.Dispatchers;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J0\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0018\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\r0\fJ&\u0010\u000e\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004J@\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u00042\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0004H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001a"}, d2 = {"Lcom/ocp/evalformation/utils/EmailHelper;", "", "()V", "SENDER_EMAIL", "", "SENDER_PASS", "SMTP_HOST", "SMTP_PORT", "buildGroupedInvitationBody", "flmNom", "collaborateur", "formations", "", "Lkotlin/Pair;", "buildInvitationBody", "themeNom", "formUrl", "sendEmail", "Lkotlin/Result;", "", "to", "cc", "subject", "body", "sendEmail-yxL6bBk", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class EmailHelper {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SMTP_HOST = "smtp.gmail.com";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SMTP_PORT = "587";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SENDER_EMAIL = "gremin11111@gmail.com";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SENDER_PASS = "apjt qfxn scas cklc";
    @org.jetbrains.annotations.NotNull()
    public static final com.ocp.evalformation.utils.EmailHelper INSTANCE = null;
    
    private EmailHelper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String buildGroupedInvitationBody(@org.jetbrains.annotations.NotNull()
    java.lang.String flmNom, @org.jetbrains.annotations.NotNull()
    java.lang.String collaborateur, @org.jetbrains.annotations.NotNull()
    java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> formations) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String buildInvitationBody(@org.jetbrains.annotations.NotNull()
    java.lang.String flmNom, @org.jetbrains.annotations.NotNull()
    java.lang.String themeNom, @org.jetbrains.annotations.NotNull()
    java.lang.String collaborateur, @org.jetbrains.annotations.NotNull()
    java.lang.String formUrl) {
        return null;
    }
}