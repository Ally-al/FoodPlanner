package com.example.foodplanner.presentation.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavBar(navController: NavController) {
    val screens = listOf(
        BottomBarScreen.RecipeList,
        BottomBarScreen.MenuConstructor,
        BottomBarScreen.Favorites,
        BottomBarScreen.ShoppingList
    )

    // currentBackStackEntryAsState возвращает State<NavBackStackEntry?>
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        screens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),            // <- drawable (Int)
                        contentDescription = stringResource(id = screen.title) // <- строка из resources
                    )
                },
                label = { Text(text = stringResource(id = screen.title)) },   // <- stringResource вместо Int
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}
