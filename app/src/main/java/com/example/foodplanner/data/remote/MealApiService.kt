package com.example.foodplanner.data.remote

import com.example.foodplanner.data.remote.dto.ApiAreasResponse
import com.example.foodplanner.data.remote.dto.ApiCategoriesResponse
import com.example.foodplanner.data.remote.dto.ApiMealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    @GET("search.php")
    suspend fun searchMealsByName(
        @Query("s") name: String
    ): ApiMealsResponse

    @GET("lookup.php")
    suspend fun getMealById(
        @Query("i") id: String
    ): ApiMealsResponse

    @GET("list.php")
    suspend fun getCategories(
        @Query("c") value: String = "list"
    ): ApiCategoriesResponse

    @GET("list.php")
    suspend fun getAreas(
        @Query("a") value: String = "list"
    ): ApiAreasResponse

    @GET("filter.php")
    suspend fun filterMealsByCategory(
        @Query("c") category: String
    ): ApiMealsResponse

    @GET("filter.php")
    suspend fun filterMealsByArea(
        @Query("a") area: String
    ): ApiMealsResponse
}

