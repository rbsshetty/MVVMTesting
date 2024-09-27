package com.example.mvvmtesting.api

import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductAPITest {

    lateinit var mockWebServer: MockWebServer
    lateinit var productAPI: ProductAPI

    @Before
    fun setup(){
        mockWebServer = MockWebServer()

        productAPI = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url("/"))
            .build().create(ProductAPI::class.java)
    }



    @Test
    fun testGetProduct_Expected_empty() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)
        val result = productAPI.getProduct()
        mockWebServer.takeRequest()
        Assert.assertEquals(true,result.body()!!.isEmpty())

    }
}