package com.ocp.evalformation.com.ocp.evalformation.data.GoogleScriptApiService;

import android.util.Log;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.ocp.evalformation.data.GoogleScriptApiService.GoogleScriptApiService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u000b"}, d2 = {"Lcom/ocp/evalformation/com/ocp/evalformation/data/GoogleScriptApiService/RetrofitInstance;", "", "()V", "BASE_URL", "", "api", "Lcom/ocp/evalformation/data/GoogleScriptApiService/GoogleScriptApiService;", "getApi", "()Lcom/ocp/evalformation/data/GoogleScriptApiService/GoogleScriptApiService;", "api$delegate", "Lkotlin/Lazy;", "app_debug"})
public final class RetrofitInstance {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BASE_URL = "https://evaluationformserver-production.up.railway.app/";
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy api$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.ocp.evalformation.com.ocp.evalformation.data.GoogleScriptApiService.RetrofitInstance INSTANCE = null;
    
    private RetrofitInstance() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.ocp.evalformation.data.GoogleScriptApiService.GoogleScriptApiService getApi() {
        return null;
    }
}