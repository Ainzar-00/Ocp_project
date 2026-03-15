package com.ocp.evalformation.data.GoogleScriptApiService

import com.ocp.evalformation.data.local.entity.FormCreationRequest
import com.ocp.evalformation.data.local.entity.FormCreationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface GoogleScriptApiService {
    @POST("exec")
    suspend fun createForm(
        @Body request: FormCreationRequest
    ): Response<FormCreationResponse>
}
