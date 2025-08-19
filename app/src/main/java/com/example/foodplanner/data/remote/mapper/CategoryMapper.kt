package com.example.foodplanner.data.remote.mapper

import com.example.foodplanner.data.remote.dto.ApiCategory
import com.example.foodplanner.domain.model.Category

fun ApiCategory.toDomain(): Category = Category(name = strCategory)
