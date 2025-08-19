package com.example.foodplanner.data.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalFavoriteDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao
) {

    suspend fun getAllFavorites(): List<FavoriteEntity> = withContext(Dispatchers.IO) {
        favoriteDao.getAllFavorites()
    }

    suspend fun isFavorite(id: String): Boolean = withContext(Dispatchers.IO) {
        favoriteDao.isFavorite(id)
    }

    suspend fun addFavorite(favorite: FavoriteEntity) = withContext(Dispatchers.IO) {
        favoriteDao.insertFavorite(favorite)
    }

    suspend fun removeFavorite(favorite: FavoriteEntity) = withContext(Dispatchers.IO) {
        favoriteDao.deleteFavorite(favorite)
    }
}
