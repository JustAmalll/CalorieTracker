package dev.amal.tracker_data.mapper

import dev.amal.tracker_data.source.cache.entity.TrackedFoodEntity
import dev.amal.tracker_domain.model.MealType
import dev.amal.tracker_domain.model.TrackedFood
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood(): TrackedFood = TrackedFood(
    name = name,
    carbs = carbs,
    protein = protein,
    fat = fat,
    imageUrl = imageUrl,
    mealType = MealType.valueOf(type),
    amount = amount,
    date = LocalDate.of(year, month, dayOfMonth),
    calories = calories,
    id = id
)

fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity = TrackedFoodEntity(
    name = name,
    carbs = carbs,
    protein = protein,
    fat = fat,
    imageUrl = imageUrl,
    type = mealType.name,
    amount = amount,
    dayOfMonth = date.dayOfMonth,
    month = date.monthValue,
    year = date.year,
    calories = calories,
    id = id
)