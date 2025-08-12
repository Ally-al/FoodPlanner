package com.example.foodplanner.domain.model

data class Recipe(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val description: String,
    val category: String,
    val cookingTimeMinutes: Int,
    val isFavorite: Boolean = false
)
