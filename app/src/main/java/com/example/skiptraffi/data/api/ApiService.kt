package com.example.skiptraffi.data.api

import com.example.skiptraffi.data.AreaDataClass
import com.example.skiptraffi.data.TrafficArea
import com.example.skiptraffi.data.TrafficMessage
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/traffic/areas?format=json&pagination=false")
    suspend fun getTrafficAreas(): Response<TrafficArea>

    @GET("v2/traffic/areas?format=json&pagination=false")
    suspend fun getTrafficAreaCoordinates(
        @Query("longitude") longitude: Double,
        @Query("latitude") latitude: Double
    ): Response<AreaDataClass>



    @GET("v2/traffic/messages?format=json&pagination=false")
    suspend fun getTrafficMessage(@Query("trafficareaname") cityName: String): Response<TrafficMessage>

    companion object {
        var apiService: ApiService? = null
        fun getInstance(): ApiService? {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("http://api.sr.se/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService
        }
    }
}

