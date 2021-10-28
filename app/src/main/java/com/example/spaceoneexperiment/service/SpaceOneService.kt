package com.example.spaceoneexperiment.service

import com.example.spaceoneexperiment.models.Aircraft
import com.example.spaceoneexperiment.models.AircraftDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://intro.hq-hydra.hibyte.ro/api/scope/chris/"

private val client =  OkHttpClient.Builder()
    .addInterceptor(BasicAuthInterceptor("admin", "12345678"))
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(client)
    .build()

interface SpaceOneService {
    @GET("items/aircraft")
    fun getAllAircrafts() : Call<List<Aircraft>>


    @DELETE("item/{id}")
    fun deleteAircraft(@Path("id") id : String) : Call<ResponseBody>

    @PUT("item/{id}")
    fun updateItem(@Path("id") id : String, @Body aircraft: Aircraft) : Call<ResponseBody>

    @POST("items/aircraft")
    fun postAircraft(@Body aircraftDto: AircraftDTO) : Call<ResponseBody>

}

object SpaceOneApi {
    val retrofitService : SpaceOneService by lazy { retrofit.create(SpaceOneService::class.java) }
}

class BasicAuthInterceptor(username: String, password: String): Interceptor {
    private var credentials: String = Credentials.basic(username, password)

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()

        return chain.proceed(request)
    }
}