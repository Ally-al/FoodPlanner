package com.example.foodplanner.data.repository

import com.example.foodplanner.data.remote.RemoteMealDataSource
import com.example.foodplanner.domain.model.Area
import com.example.foodplanner.domain.model.Category
import com.example.foodplanner.domain.repository.MetaRepository
import javax.inject.Inject

class MetaRepositoryImpl @Inject constructor(
    private val remoteMealDataSource: RemoteMealDataSource
) : MetaRepository {

    override suspend fun getCategories(): List<Category> {
        return remoteMealDataSource.getCategories()
    }

    override suspend fun getAreas(): List<Area> {
        return remoteMealDataSource.getAreas()
    }
}
