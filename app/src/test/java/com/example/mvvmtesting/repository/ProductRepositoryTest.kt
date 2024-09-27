package com.example.mvvmtesting.repository

import com.example.mvvmtesting.api.ProductAPI
import com.example.mvvmtesting.models.ProductListItem
import com.example.mvvmtesting.utils.NetworkResult
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class ProductRepositoryTest {

    @Mock
    lateinit var productAPI: ProductAPI

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }


    @Test
    fun testGetProduct_expectedEmptyList() = runTest{
        Mockito.`when`(productAPI.getProduct()).thenReturn(Response.success(emptyList()))

        val repo = ProductRepository(productAPI)
        val result = repo.getProduct()
        Assert.assertEquals(0,result.data!!.size)
        Assert.assertEquals(true,result is NetworkResult.Success)
    }

    @Test
    fun testGetProduct_expectedProductList() = runTest{
        val list = listOf(
            ProductListItem(0,"prod1","test desc1"),
            ProductListItem(1,"prod2","test desc2")
        )

        Mockito.`when`(productAPI.getProduct()).thenReturn(Response.success(list))

        val repo = ProductRepository(productAPI)
        val result = repo.getProduct()
        Assert.assertEquals(2,result.data!!.size)
        Assert.assertEquals(true,result is NetworkResult.Success)
        Assert.assertEquals("prod1", result.data!![0].name)
    }

    @Test
    fun testGetProduct_expectedError() = runTest{
        Mockito.`when`(productAPI.getProduct()).thenReturn(Response.error(401, ResponseBody.create(
            "text/plain".toMediaTypeOrNull(),  "unauthorized")))

        val repo = ProductRepository(productAPI)
        val result = repo.getProduct()
        Assert.assertEquals(true,result is NetworkResult.Error)
        Assert.assertEquals("something went wrong", result.message)
    }



    @After
    fun tearDown() {
    }
}