package com.example.skiptraffi.data

data class Message(
    val category: Int,
    val createddate: String,
    val description: String,
    val exactlocation: String,
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val priority: Int,
    val subcategory: String,
    val title: String
)