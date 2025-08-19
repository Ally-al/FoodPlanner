package com.example.foodplanner.data.repository

import com.example.foodplanner.data.local.FavoriteEntity
import com.example.foodplanner.data.local.LocalFavoriteDataSource
import com.example.foodplanner.data.remote.RemoteMealDataSource
import com.example.foodplanner.domain.model.Recipe
import com.example.foodplanner.domain.model.RecipeFilter
import com.example.foodplanner.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val remoteMealDataSource: RemoteMealDataSource,
    private val localFavoriteDataSource: LocalFavoriteDataSource
) : RecipeRepository {

    override suspend fun getRecipes(filter: RecipeFilter): List<Recipe> {
        val baseList = when {
            filter.category != null -> remoteMealDataSource.filterMealsByCategory(filter.category)
            filter.area != null -> remoteMealDataSource.filterMealsByArea(filter.area)
            filter.searchQuery != null -> remoteMealDataSource.searchMeals(filter.searchQuery)
            else -> emptyList()
        }

        return baseList
            .filter { recipe ->
                (filter.category == null || recipe.category == filter.category) &&
                        (filter.area == null || recipe.area == filter.area) &&
                        (filter.searchQuery == null || recipe.name.contains(filter.searchQuery, ignoreCase = true))
            }
            .map { recipe ->
                recipe.copy(isFavorite = localFavoriteDataSource.isFavorite(recipe.id))
            }
    }

    override suspend fun getRecipeById(id: String): Recipe? {
        val recipe = remoteMealDataSource.getMeal(id)
        return recipe?.copy(
            isFavorite = localFavoriteDataSource.isFavorite(id)
        )
    }

    override suspend fun toggleFavorite(recipe: Recipe) {
        val isFav = localFavoriteDataSource.isFavorite(recipe.id)
        if (isFav) {
            localFavoriteDataSource.removeFavorite(
                FavoriteEntity(id = recipe.id, name = recipe.name, thumbnailUrl = recipe.thumbnailUrl)
            )
        } else {
            localFavoriteDataSource.addFavorite(
                FavoriteEntity(id = recipe.id, name = recipe.name, thumbnailUrl = recipe.thumbnailUrl)
            )
        }
    }
}
