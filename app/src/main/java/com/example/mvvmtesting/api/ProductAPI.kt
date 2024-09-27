package com.example.mvvmtesting.api

import com.example.mvvmtesting.models.ProductListItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {

    @GET("/products")
    suspend fun getProduct(): Response<List<ProductListItem>>
}