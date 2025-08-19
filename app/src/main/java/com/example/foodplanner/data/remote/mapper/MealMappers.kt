package com.example.foodplanner.data.remote.mapper

import com.example.foodplanner.data.remote.dto.ApiMeal
import com.example.foodplanner.domain.model.Ingredient
import com.example.foodplanner.domain.model.Recipe

fun ApiMeal.toDomain(): Recipe {
    return Recipe(
        id = idMeal,
        name = strMeal,
        category = strCategory,
        area = strArea,
        instructions = strInstructions,
        thumbnailUrl = strMealThumb,
        ingredients = toIngredients(),
        isFavorite = false
    )
}

private fun ApiMeal.toIngredients(): List<Ingredient> {
    val names = listOf(
        strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5,
        strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10,
        strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15,
        strIngredient16, strIngredient17, strIngredient18, strIngredient19, strIngredient20
    )
    val measures = listOf(
        strMeasure1, strMeasure2, strMeasure3, strMeasure4, strMeasure5,
        strMeasure6, strMeasure7, strMeasure8, strMeasure9, strMeasure10,
        strMeasure11, strMeasure12, strMeasure13, strMeasure14, strMeasure15,
        strMeasure16, strMeasure17, strMeasure18, strMeasure19, strMeasure20
    )

    return names.zip(measures).mapNotNull { (n, m) ->
        val name = n?.trim().orEmpty()
        if (name.isEmpty()) null
        else Ingredient(name = name, measure = m?.trim().orEmpty())
    }
}
