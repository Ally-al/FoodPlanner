package com.example.foodplanner.domain.model

data class RecipeFilter(
    val category: String? = null,
    val area: String? = null,
    val searchQuery: String? = null
)
