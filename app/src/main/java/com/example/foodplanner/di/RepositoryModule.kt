package com.example.foodplanner.di

import com.example.foodplanner.data.repository.MetaRepositoryImpl
import com.example.foodplanner.data.repository.RecipeRepositoryImpl
import com.example.foodplanner.domain.repository.MetaRepository
import com.example.foodplanner.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRecipeRepository(
        impl: RecipeRepositoryImpl
    ): RecipeRepository

    @Binds
    @Singleton
    abstract fun bindMetaRepository(
        impl: MetaRepositoryImpl
    ): MetaRepository
}
