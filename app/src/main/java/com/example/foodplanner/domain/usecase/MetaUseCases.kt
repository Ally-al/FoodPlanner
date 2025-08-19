package com.example.foodplanner.domain.usecase

import com.example.foodplanner.domain.model.Area
import com.example.foodplanner.domain.model.Category
import com.example.foodplanner.domain.repository.MetaRepository

data class MetaUseCases(
    val getCategories: GetCategoriesUseCase,
    val getAreas: GetAreasUseCase
)

class GetCategoriesUseCase(
    private val repository: MetaRepository
) {
    suspend operator fun invoke(): List<Category> {
        return repository.getCategories()
    }
}

class GetAreasUseCase(
    private val repository: MetaRepository
) {
    suspend operator fun invoke(): List<Area> {
        return repository.getAreas()
    }
}
