package com.example.mvvmtesting

import android.app.Application
import com.example.mvvmtesting.api.ProductAPI
import com.example.mvvmtesting.repository.ProductRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoreApplication : Application() {

    lateinit var productAPI: ProductAPI
    lateinit var productRepository: ProductRepository


    override fun onCreate() {
        super.onCreate()


        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("").build()


        productAPI = retrofit.create(ProductAPI::class.java)
        productRepository = ProductRepository(productAPI)
    }



}