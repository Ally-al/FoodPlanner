package com.example.foodplanner.presentation.recipelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodplanner.domain.model.Recipe
import com.example.foodplanner.domain.model.RecipeFilter
import com.example.foodplanner.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    // Внутренние мутабельные состояния
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    private val _searchQuery = MutableStateFlow("")
    private val _selectedCategory = MutableStateFlow<String?>(null)
    private val _maxCookingTime = MutableStateFlow<Int?>(null)

    // Комбинируем фильтры и обновляем список рецептов при изменении любого параметра
    init {
        viewModelScope.launch {
            combine(
                _searchQuery,
                _selectedCategory,
                _maxCookingTime
            ) { search, category, maxTime ->
                RecipeFilter(
                    searchQuery = search,
                    category = category,
                    maxCookingTimeMinutes = maxTime
                )
            }.collectLatest { filter ->
                loadRecipes(filter)
            }
        }
    }

    private suspend fun loadRecipes(filter: RecipeFilter) {
        val allRecipes = recipeRepository.getRecipes(filter)

        // Фильтруем по названию, так как твой репозиторий не делает это (если хочешь, можешь потом добавить)
        val filtered = if (filter.searchQuery.isNullOrBlank()) {
            allRecipes
        } else {
            allRecipes.filter {
                it.title.contains(filter.searchQuery, ignoreCase = true)
            }
        }

        _recipes.value = filtered
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun onCategorySelected(category: String?) {
        _selectedCategory.value = category
    }

    fun onMaxCookingTimeChanged(maxTime: Int?) {
        _maxCookingTime.value = maxTime
    }

    fun onToggleFavorite(recipeId: String) {
        viewModelScope.launch {
            recipeRepository.toggleFavorite(recipeId)
            // Обновляем список, чтобы отобразить изменение
            loadRecipes(
                RecipeFilter(
                    searchQuery = _searchQuery.value,
                    category = _selectedCategory.value,
                    maxCookingTimeMinutes = _maxCookingTime.value
                )
            )
        }
    }
}
