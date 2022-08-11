package com.example.skiptraffi.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skiptraffi.data.Message
import com.example.skiptraffi.data.api.ApiService
import com.example.skiptraffi.util.Constants
import kotlinx.coroutines.launch



class CurrentPositionViewModel: ViewModel() {
    var trafficMessage: List<Message>? by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    var coordinatesCityName: String by mutableStateOf("")

    fun getAreaListWithCoordinates() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()

            try {
                val coordinatesAreaList = apiService?.getTrafficAreaCoordinates(Constants.LONGITUDE_KEY!!, Constants.LATITUDE_KEY!!)
                coordinatesAreaList?.body()?.area
                coordinatesCityName = coordinatesAreaList?.body()?.area?.name.toString()
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }



    fun getMessageList(cityName: String) {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val messageList = apiService?.getTrafficMessage(cityName)
                trafficMessage = messageList?.body()?.messages
            } catch (e: Exception) {
                errorMessage = e.message.toString()
                Log.d("TestHej", "errorMessage: " + errorMessage)
            }
        }
    }
}