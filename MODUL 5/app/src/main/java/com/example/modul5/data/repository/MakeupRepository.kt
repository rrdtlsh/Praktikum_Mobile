package com.example.modul5.data.repository

import com.example.modul5.data.local.dao.MakeupDao
import com.example.modul5.data.local.entity.toEntity
import com.example.modul5.data.local.entity.toModel
import com.example.modul5.data.model.MakeupProduct
import com.example.modul5.data.remote.api.MakeupApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException

class MakeupRepository(
    private val apiService: MakeupApiService,
    private val makeupDao: MakeupDao
) {
    fun fetchAndCacheProducts(): Flow<Result<Unit>> = flow {
        try {
            val networkResponse = apiService.getProducts()
            val favoriteIds = makeupDao.getFavoriteIds()
            makeupDao.upsertAll(networkResponse.toEntity(favoriteIds))
            emit(Result.success(Unit))
        } catch (e: IOException) {
            emit(Result.failure(e))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    fun getProductsFromCache(): Flow<List<MakeupProduct>> {
        return makeupDao.getAllMakeup().map { it.toModel() }
    }

    fun getProductById(id: Int): Flow<MakeupProduct?> {
        return makeupDao.getMakeupById(id).map { it?.toModel() }
    }

    fun getFavoriteProducts(): Flow<List<MakeupProduct>> {
        return makeupDao.getFavoriteMakeup().map { it.toModel() }
    }

    suspend fun toggleFavoriteStatus(id: Int, isFavorite: Boolean) {
        makeupDao.updateFavoriteStatus(id, isFavorite)
    }
}