package com.example.skiptraffi.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skiptraffi.data.Area
import com.example.skiptraffi.data.api.ApiService
import kotlinx.coroutines.launch

class TrafficViewModel: ViewModel() {

    var trafficAreas: List<Area>? by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun getAreaList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val areaList = apiService?.getTrafficAreas()
                 trafficAreas = areaList?.body()?.areas
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("TestHej", "errorMessage: " + errorMessage)
            }
        }
    }
}