package com.ocp.evalformation.com.ocp.evalformation.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.ocp.evalformation.data.GoogleScriptApiService.GoogleScriptApiService
import com.ocp.evalformation.data.local.dao.FormDao
import com.ocp.evalformation.data.local.entity.FormCreationRequest
import com.ocp.evalformation.data.local.entity.Forms
import com.ocp.evalformation.data.local.entity.InvitationFlmEntity
import com.ocp.evalformation.data.local.entity.ThemeEntity
import javax.inject.Inject

class FormRepository @Inject constructor(
    private val formDao: FormDao,
    private val api: GoogleScriptApiService,
    private val firestore: FirebaseFirestore
) {


    // Build pre-filled URL when sending invitation
    fun buildPreFilledUrl(form: Forms, invitation: InvitationFlmEntity): String {
        return "${form.formUrl}?usp=pp_url" +
                "&entry.${form.entryIds.formationId}=${invitation.formationId}" +
                "&entry.${form.entryIds.intituleAction}=${encode(invitation.themeNom)}" +
                "&entry.${form.entryIds.nomPrenom}=${encode(invitation.nomCompletCollaborateur)}" +
                "&entry.${form.entryIds.service}=${encode(invitation.service)}" +
                "&entry.${form.entryIds.dates}=${encode(invitation.datesFormation)}" +
                "&entry.${form.entryIds.formateur}=${encode(invitation.formateur)}" +
                "&entry.${form.entryIds.matricule}=${encode(invitation.matriculeCollaborateur)}"
    }

    private fun encode(value: String) =
        java.net.URLEncoder.encode(value, "UTF-8")
}