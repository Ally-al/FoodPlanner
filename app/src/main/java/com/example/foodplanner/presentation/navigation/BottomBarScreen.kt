package com.example.foodplanner.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.foodplanner.R

sealed class BottomBarScreen(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int
) {
    object RecipeList : BottomBarScreen(
        route = "recipe_list",
        icon = R.drawable.ic_recipes,
        title = R.string.recipe_list
    )

    object MenuConstructor : BottomBarScreen(
        route = "menu_constructor",
        icon = R.drawable.ic_menu_constructor,
        title = R.string.menu_constructor
    )

    object Favorites : BottomBarScreen(
        route = "favorites",
        icon = R.drawable.ic_favorites,
        title = R.string.favorites
    )

    object ShoppingList : BottomBarScreen(
        route = "shopping_list",
        icon = R.drawable.ic_shopping_list,
        title = R.string.shopping_list
    )
}
