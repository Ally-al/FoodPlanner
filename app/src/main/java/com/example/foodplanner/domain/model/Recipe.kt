package com.example.foodplanner.domain.model

data class Recipe(
    val id: String,
    val name: String,
    val category: String?,
    val area: String?,
    val instructions: String?,
    val thumbnailUrl: String?,
    val ingredients: List<Ingredient>?,
    val isFavorite: Boolean = false
)
