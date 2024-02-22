package dev.amal.tracker_data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchDto(
    @SerialName("products") val products: List<ProductDto>,
)