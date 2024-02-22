package dev.amal.tracker_data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    @SerialName("image_front_thumb_url") val imageFrontThumbUrl: String?,
    @SerialName("nutriments") val nutriments: NutrimentsDto,
    @SerialName("product_name") val productName: String?
)