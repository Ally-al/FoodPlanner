package com.example.foodplanner.data.remote.dto

data class ApiAreasResponse(
    val areas: List<ApiArea>?
)

data class ApiArea(
    val strArea: String
)
