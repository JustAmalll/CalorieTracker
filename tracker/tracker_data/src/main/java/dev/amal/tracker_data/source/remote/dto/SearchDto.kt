package dev.amal.tracker_data.source.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchDto(
    @SerialName("products") val products: List<ProductDto>,
)