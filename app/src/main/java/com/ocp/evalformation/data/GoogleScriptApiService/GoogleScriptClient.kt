package com.ocp.evalformation.com.ocp.evalformation.data.GoogleScriptApiService

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.ocp.evalformation.data.GoogleScriptApiService.GoogleScriptApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    // ── Replace this with your Cloud Function URL when deployed ──
    // For local testing use: "http://10.0.2.2:8080/" (Android emulator)
    // For real device on same network use: "http://YOUR_LOCAL_IP:8080/"
    // For production use: "https://YOUR_CLOUD_FUNCTION_URL/"

    private const val BASE_URL = "https://evaluationformserver-production.up.railway.app/"

//    private const val BASE_URL ="http://localhost:8080/"

    val api: GoogleScriptApiService by lazy {

        val gson = GsonBuilder()
            .setLenient()
            .registerTypeAdapter(Long::class.java, JsonDeserializer { json, _, _ ->
                try {
                    json.asDouble.toLong()
                } catch (e: Exception) {
                    json.asString.toLongOrNull() ?: 0L
                }
            })
            .registerTypeAdapter(Int::class.java, JsonDeserializer { json, _, _ ->
                try {
                    json.asDouble.toInt()
                } catch (e: Exception) {
                    json.asString.toIntOrNull() ?: 0
                }
            })
            // ✅ NEW: Handle Google Forms entry IDs as strings
            .registerTypeAdapter(String::class.java, JsonDeserializer { json, _, _ ->
                // Let Gson handle strings normally, but log for debugging
                val value = json.asString
                Log.d("GsonDebug", "Deserializing string: $value")
                value
            })
            .create()

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GoogleScriptApiService::class.java)
    }
}