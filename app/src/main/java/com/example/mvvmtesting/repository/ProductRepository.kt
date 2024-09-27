package com.example.mvvmtesting.repository

import com.example.mvvmtesting.api.ProductAPI
import com.example.mvvmtesting.models.ProductListItem
import com.example.mvvmtesting.utils.NetworkResult

class ProductRepository(val productAPI: ProductAPI) {


    suspend fun getProduct():NetworkResult<List<ProductListItem>>{
        val response = productAPI.getProduct()

        return if(response.isSuccessful){
            val responseBody = response.body()

            if(responseBody!=null){
                NetworkResult.Success(responseBody)
            }else{
                NetworkResult.Error(null,"")
            }

        }else{
            NetworkResult.Error(null,"something went wrong")
        }
    }

}