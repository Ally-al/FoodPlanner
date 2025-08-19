package com.example.foodplanner.di

import com.example.foodplanner.domain.repository.RecipeRepository
import com.example.foodplanner.domain.repository.MetaRepository
import com.example.foodplanner.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideRecipeUseCases(
        repository: RecipeRepository
    ): RecipeUseCases {
        return RecipeUseCases(
            getRecipes = GetRecipesUseCase(repository),
            getRecipeById = GetRecipeByIdUseCase(repository),
            toggleFavorite = ToggleFavoriteUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideMetaUseCases(
        repository: MetaRepository
    ): MetaUseCases {
        return MetaUseCases(
            getCategories = GetCategoriesUseCase(repository),
            getAreas = GetAreasUseCase(repository)
        )
    }
}
