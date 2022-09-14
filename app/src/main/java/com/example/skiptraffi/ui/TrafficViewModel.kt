package com.example.skiptraffi.ui

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
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun getAreaListWithCoordinates() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()

            try {
                val coordinatesAreaList =
                    apiService?.getTrafficAreaCoordinates(LONGITUDE_KEY!!, LATITUDE_KEY!!)
                coordinatesAreaList?.body()?.area
                coordinatesArea = coordinatesAreaList?.body()?.area?.name.toString()
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}

