package com.example.modul5.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.modul5.data.local.entity.MakeupEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MakeupDao {
    @Upsert
    suspend fun upsertAll(products: List<MakeupEntity>)

    @Query("SELECT * FROM makeup_products ORDER BY id ASC")
    fun getAllMakeup(): Flow<List<MakeupEntity>>

    @Query("SELECT * FROM makeup_products WHERE id = :id")
    fun getMakeupById(id: Int): Flow<MakeupEntity?>

    @Query("UPDATE makeup_products SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Int, isFavorite: Boolean)

    @Query("SELECT * FROM makeup_products WHERE isFavorite = 1 ORDER BY id ASC")
    fun getFavoriteMakeup(): Flow<List<MakeupEntity>>

    @Query("SELECT id FROM makeup_products WHERE isFavorite = 1")
    suspend fun getFavoriteIds(): List<Int>

    @Query("DELETE FROM makeup_products")
    suspend fun clearAll()
}