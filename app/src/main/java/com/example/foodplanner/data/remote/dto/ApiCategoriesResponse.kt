package com.example.foodplanner.data.remote.dto

data class ApiCategoriesResponse(
    val categories: List<ApiCategory>?
)

data class ApiCategory(
    val strCategory: String
)
