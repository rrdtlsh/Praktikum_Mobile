package com.example.modul5.data.local.entity

import com.example.modul5.data.model.MakeupProduct

fun List<MakeupProduct>.toEntity(favoriteIds: List<Int>): List<MakeupEntity> {
    return this.map { apiProduct ->
        MakeupEntity(
            id = apiProduct.id,
            brand = apiProduct.brand,
            name = apiProduct.name,
            price = apiProduct.price,
            imageLink = apiProduct.imageLink,
            directImageLink = apiProduct.directImageLink,
            description = apiProduct.description,
            productType = apiProduct.productType,
            isFavorite = favoriteIds.contains(apiProduct.id)
        )
    }
}

fun MakeupEntity.toModel(): MakeupProduct {
    return MakeupProduct(
        id = this.id,
        brand = this.brand,
        name = this.name,
        price = this.price,
        imageLink = this.imageLink,
        directImageLink = this.directImageLink,
        description = this.description,
        productType = this.productType,
        isFavorite = this.isFavorite
    )
}

fun List<MakeupEntity>.toModel(): List<MakeupProduct> {
    return this.map { it.toModel() }
}