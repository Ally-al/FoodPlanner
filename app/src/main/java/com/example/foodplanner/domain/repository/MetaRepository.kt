package com.example.foodplanner.domain.repository

import com.example.foodplanner.domain.model.Area
import com.example.foodplanner.domain.model.Category

interface MetaRepository {
    suspend fun getCategories(): List<Category>
    suspend fun getAreas(): List<Area>
}
