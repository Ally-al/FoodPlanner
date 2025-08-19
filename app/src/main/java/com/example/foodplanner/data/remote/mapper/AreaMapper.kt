package com.example.foodplanner.data.remote.mapper

import com.example.foodplanner.data.remote.dto.ApiArea
import com.example.foodplanner.domain.model.Area

fun ApiArea.toDomain(): Area = Area(name = strArea)
