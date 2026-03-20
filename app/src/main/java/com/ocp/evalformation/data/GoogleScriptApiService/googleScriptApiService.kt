package com.ocp.evalformation.data.GoogleScriptApiService

import com.ocp.evalformation.data.local.entity.FormCreationRequest
import com.ocp.evalformation.data.local.entity.FormCreationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleScriptApiService {

    // ── Create a Google Form for a given theme ──
    // POST http://YOUR_SERVER/createForm
    // Body: { themeNom: String, competences: List<String> }
    // Response: { status, formUrl, formId, responseSheetId, entryIds }
    @POST("createForm")
    suspend fun createForm(
        @Body request: FormCreationRequest
    ): Response<FormCreationResponse>
}