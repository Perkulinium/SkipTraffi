package com.example.skiptraffi.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skiptraffi.data.Area
import com.example.skiptraffi.data.api.ApiService
import com.example.skiptraffi.util.Constants.LATITUDE_KEY
import com.example.skiptraffi.util.Constants.LONGITUDE_KEY
import kotlinx.coroutines.launch

class TrafficViewModel : ViewModel() {

    var trafficAreas: List<Area>? by mutableStateOf(listOf())
    var coordinatesArea: String by mutableStateOf("")
    var errorMessage: String by mutableStateOf("")


    fun getAreaList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val areaList = apiService?.getTrafficAreas()
                trafficAreas = areaList?.body()?.areas
                Log.d("TestHej", "usch: " + trafficAreas)
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("TestHej", "errorMessage: " + errorMessage)
            }
        }
    }

    fun getAreaListWithCoordinates(longitude: Double, latitude: Double) {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()

            try {
                val coordinatesAreaList = apiService?.getTrafficAreaCoordinates(longitude, latitude)
                coordinatesAreaList?.body()?.area
                coordinatesArea = coordinatesAreaList?.body()?.area?.name.toString()

            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("TestHej", "errorMessage: " + errorMessage)
            }
        }
    }


    init {

      //  getAreaListWithCoordinates(0.0, 0.0)

    }
}