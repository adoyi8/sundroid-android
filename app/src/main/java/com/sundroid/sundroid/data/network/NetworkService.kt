package com.sundroid.sundroid.data.network

import com.sundroid.sundroid.data.local.dao.database_models.Job
import com.sundroid.sundroid.data.network.model.LoginResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


private const val BASE_URL =
    "http://192.168.56.1:8090/"

/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

/**
 * Retrofit service object for creating api calls
 */
interface SundroidApiService {
    @GET("home")
    suspend fun testConnection():LoginResponse


    @POST("login")
    suspend fun login(@Body body:HashMap<String, String>):LoginResponse

    @POST("authenticate")
    suspend fun authenticate(@Body body:HashMap<String, String>): Response<LoginResponse>

    @POST("add_job")
    suspend fun addJob(@Body body:HashMap<String, Any>): Response<Job>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object SundroidApi {
    val retrofitService: SundroidApiService by lazy {
        retrofit.create(SundroidApiService::class.java)
    }
}