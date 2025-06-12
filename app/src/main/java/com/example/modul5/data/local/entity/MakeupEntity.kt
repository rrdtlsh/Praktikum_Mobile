package com.example.modul5.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "makeup_products")
data class MakeupEntity(
    @PrimaryKey val id: Int,
    val brand: String?,
    val name: String?,
    val price: String?,
    val imageLink: String?,
    val directImageLink: String?,
    val description: String?,
    val productType: String?,
    val isFavorite: Boolean
)