package dev.amal.tracker_data.mapper

import dev.amal.tracker_data.source.remote.dto.ProductDto
import dev.amal.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt

fun ProductDto.toTrackableFood(): TrackableFood? = productName?.let { productName ->
    TrackableFood(
        name = productName,
        carbsPer100g = nutriments.carbohydrates100g.roundToInt(),
        proteinPer100g = nutriments.proteins100g.roundToInt(),
        fatPer100g = nutriments.proteins100g.roundToInt(),
        caloriesPer100g = nutriments.energyKcal100g.roundToInt(),
        imageUrl = imageFrontThumbUrl
    )
}