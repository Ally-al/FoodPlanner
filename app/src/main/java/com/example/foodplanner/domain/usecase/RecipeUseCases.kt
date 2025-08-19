package com.example.foodplanner.domain.usecase

import com.example.foodplanner.domain.model.Recipe
import com.example.foodplanner.domain.model.RecipeFilter
import com.example.foodplanner.domain.repository.RecipeRepository

data class RecipeUseCases(
    val getRecipes: GetRecipesUseCase,
    val getRecipeById: GetRecipeByIdUseCase,
    val toggleFavorite: ToggleFavoriteUseCase
)

class GetRecipesUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(filter: RecipeFilter): List<Recipe> {
        return repository.getRecipes(filter)
    }
}

class GetRecipeByIdUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(id: String): Recipe? {
        return repository.getRecipeById(id)
    }
}

class ToggleFavoriteUseCase(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipe: Recipe) {
        repository.toggleFavorite(recipe)
    }
}
