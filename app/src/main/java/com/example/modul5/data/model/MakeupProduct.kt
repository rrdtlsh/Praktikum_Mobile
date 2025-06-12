package com.example.modul5.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MakeupProduct(
    val id: Int,
    val brand: String?,
    val name: String?,
    val price: String?,
    @SerialName("image_link") val imageLink: String?,
    @SerialName("direct_image_link") val directImageLink: String?,
    val description: String?,
    @SerialName("product_type") val productType: String?,
    val isFavorite: Boolean = false
)