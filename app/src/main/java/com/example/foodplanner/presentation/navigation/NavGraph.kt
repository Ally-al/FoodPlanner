package com.example.foodplanner.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.foodplanner.presentation.favorites.FavoritesScreen
import com.example.foodplanner.presentation.menuconstructor.MenuConstructorScreen
import com.example.foodplanner.presentation.recipelist.RecipeListScreen
import com.example.foodplanner.presentation.shoppinglist.ShoppingListScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.RecipeList.route,
        modifier = modifier
    ) {
        composable(BottomBarScreen.RecipeList.route) {
            RecipeListScreen(navController = navController)
        }
        composable(BottomBarScreen.MenuConstructor.route) {
            MenuConstructorScreen(navController = navController)
        }
        composable(BottomBarScreen.Favorites.route) {
            FavoritesScreen(navController = navController)
        }
        composable(BottomBarScreen.ShoppingList.route) {
            ShoppingListScreen(navController = navController)
        }
    }
}
