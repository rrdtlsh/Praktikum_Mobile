package com.example.modul5.data.remote.api

import com.example.modul5.data.model.MakeupProduct
import retrofit2.http.GET

interface MakeupApiService {
     @GET("api/v1/products.json")
    suspend fun getProducts(): List<MakeupProduct>
}
