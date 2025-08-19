package com.example.foodplanner.presentation.recipelist

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.foodplanner.domain.model.Recipe


@Composable
fun RecipeListScreen(
    navController: NavController,
    viewModel: RecipeListViewModel = hiltViewModel()
) {
    val recipes by viewModel.recipes.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val maxTime by viewModel.maxCookingTime.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        // Поиск по названию
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { viewModel.onSearchQueryChanged(it) },
            label = { Text("Поиск по названию") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Фильтр по категории (пример — простой DropdownMenu)
        CategoryDropdown(selectedCategory = selectedCategory, onCategorySelected = {
            viewModel.onCategorySelected(it)
        })

        Spacer(modifier = Modifier.height(8.dp))

        // Фильтр по времени
        OutlinedTextField(
            value = maxTime?.toString() ?: "",
            onValueChange = { viewModel.onMaxTimeChanged(it) },
            label = { Text("Максимальное время (мин)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Список рецептов
        LazyColumn {
            items(recipes) { recipe ->
                RecipeItem(
                    recipe = recipe,
                    onFavoriteClick = { viewModel.toggleFavorite(recipe.id) },
                    onRecipeClick = {
                        // Здесь сделаем навигацию на детальный экран, позже
                        // navController.navigate("recipe_detail/${recipe.id}")
                    }
                )
                Divider()
            }
        }
    }
}

@Composable
fun CategoryDropdown(
    selectedCategory: Category?,
    onCategorySelected: (Category?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(onClick = { expanded = true }) {
            Text(text = selectedCategory?.name ?: "Выберите категорию")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(onClick = {
                onCategorySelected(null)
                expanded = false
            }) {
                Text("Все категории")
            }
            // Пример категорий, замени на свои
            Category.values().forEach { category ->
                DropdownMenuItem(onClick = {
                    onCategorySelected(category)
                    expanded = false
                }) {
                    Text(category.name)
                }
            }
        }
    }
}


@Composable
fun RecipeItem(
    recipe: Recipe,
    onFavoriteClick: () -> Unit,
    onRecipeClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRecipeClick() }
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = recipe.title, style = MaterialTheme.typography.titleMedium)
            Text(text = recipe.category, style = MaterialTheme.typography.bodySmall)
            Text(text = "Время: ${recipe.cookingTimeMinutes} мин", style = MaterialTheme.typography.bodySmall)
            Text(text = recipe.description, style = MaterialTheme.typography.bodyMedium, maxLines = 2)
        }
        IconButton(onClick = onFavoriteClick) {
            Icon(
                imageVector = if (recipe.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Избранное"
            )
        }
    }
}

