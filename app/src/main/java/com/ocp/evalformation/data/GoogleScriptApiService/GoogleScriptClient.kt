package com.ocp.evalformation.com.ocp.evalformation.data.GoogleScriptApiService

import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.ocp.evalformation.data.GoogleScriptApiService.GoogleScriptApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    val deploymentID="AKfycbzfs4pMjTTGi_l5Uf5Xi_-XJXAugly5tS85auU4V6UESZxJ5v1Xi4W2WRaVSQ3A47fe"



    val api: GoogleScriptApiService by lazy {

        val gson = GsonBuilder()
            .registerTypeAdapter(Long::class.java, JsonDeserializer { json, _, _ ->
                json.asDouble.toLong()
            })
            .create()

        // ← Increase timeouts for Apps Script (form creation takes ~15 sec)
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)    // ← key fix
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl("https://script.google.com/macros/s/$deploymentID/")
            .client(okHttpClient)                  // ← attach custom client
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GoogleScriptApiService::class.java)
    }
}