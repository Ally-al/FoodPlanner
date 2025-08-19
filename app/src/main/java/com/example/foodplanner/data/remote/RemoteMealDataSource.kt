package com.example.foodplanner.data.remote

import com.example.foodplanner.data.remote.mapper.toDomain
import com.example.foodplanner.domain.model.Area
import com.example.foodplanner.domain.model.Category
import com.example.foodplanner.domain.model.Recipe
import javax.inject.Inject

class RemoteMealDataSource @Inject constructor(
    private val api: MealApiService
) {
    suspend fun searchMeals(name: String): List<Recipe> {
        return api.searchMealsByName(name).meals?.map { it.toDomain() }.orEmpty()
    }

    suspend fun getMeal(id: String): Recipe? {
        return api.getMealById(id).meals?.firstOrNull()?.toDomain()
    }

    suspend fun getCategories(): List<Category> {
        return api.getCategories().categories?.map { it.toDomain() }.orEmpty()
    }

    suspend fun getAreas(): List<Area> {
        return api.getAreas().areas?.map { it.toDomain() }.orEmpty()
    }

    suspend fun filterMealsByCategory(category: String): List<Recipe> {
        return api.filterMealsByCategory(category).meals?.map { it.toDomain() }.orEmpty()
    }

    suspend fun filterMealsByArea(area: String): List<Recipe> {
        return api.filterMealsByArea(area).meals?.map { it.toDomain() }.orEmpty()
    }
}
