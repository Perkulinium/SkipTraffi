package com.example.skiptraffi.data.api

import com.example.skiptraffi.data.TrafficArea
import com.example.skiptraffi.data.TrafficMessage
import com.example.skiptraffi.util.Constants.DETAIL_CITY_KEY
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("v2/traffic/areas?format=json&pagination=false")
    suspend fun getTrafficAreas() : Response<TrafficArea>

    @Header("careaname")
    @GET("v2/traffic/messages?format=json&trafficareaname=$cityName&pagination=false")
    suspend fun getTrafficMessage() : Response<TrafficMessage>

    companion object {
        var apiService: ApiService? = null
        fun getInstance() : ApiService? {
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

