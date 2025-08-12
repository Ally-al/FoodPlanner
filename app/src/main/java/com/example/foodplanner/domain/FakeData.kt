package com.example.foodplanner.domain

import com.example.foodplanner.domain.model.Recipe

object FakeData {
    val recipes = listOf(
        Recipe(
            id = 1,
            title = "Спагетти Болоньезе",
            imageUrl = null,
            description = "Классический итальянский мясной соус с пастой.",
            category = "Паста",
            cookingTimeMinutes = 40
        ),
        Recipe(
            id = 2,
            title = "Цезарь с курицей",
            imageUrl = null,
            description = "Салат с курицей, листьями салата и соусом Цезарь.",
            category = "Салаты",
            cookingTimeMinutes = 20
        ),
        Recipe(
            id = 3,
            title = "Омлет с овощами",
            imageUrl = null,
            description = "Легкий омлет с болгарским перцем и помидорами.",
            category = "Завтрак",
            cookingTimeMinutes = 10
        ),
        // Добавь ещё, если хочешь
    )
}
