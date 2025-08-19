package com.example.foodplanner.domain.repository

import com.example.foodplanner.domain.model.Recipe
import com.example.foodplanner.domain.model.RecipeFilter

interface RecipeRepository {
    suspend fun getRecipes(filter: RecipeFilter = RecipeFilter()): List<Recipe>
    suspend fun getRecipeById(id: String): Recipe?
    suspend fun toggleFavorite(recipe: Recipe)
}
