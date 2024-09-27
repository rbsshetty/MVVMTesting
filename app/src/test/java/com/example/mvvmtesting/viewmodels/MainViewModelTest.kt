package com.example.mvvmtesting.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mvvmtesting.getOrAwaitValue
import com.example.mvvmtesting.repository.ProductRepository
import com.example.mvvmtesting.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainViewModelTest {


    @Mock
    lateinit var productRepository: ProductRepository

    val testdispacher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testdispacher)
    }

    @Test
    fun test_getProduct()= runTest{
        Mockito.`when`(productRepository.getProduct()).thenReturn(NetworkResult.Success(emptyList()))

        val sut = MainViewModel(productRepository)
        sut.getProduct()
        testdispacher.scheduler.advanceUntilIdle()
        val result = sut.product.getOrAwaitValue()
        Assert.assertEquals(0,result.data!!.size)

    }

    @Test
    fun test_getProduct_expectedError()= runTest{
        Mockito.`when`(productRepository.getProduct()).thenReturn(NetworkResult.Error(null,"error"))

        val mvm= MainViewModel(productRepository)
        mvm.getProduct()
        testdispacher.scheduler.advanceUntilIdle()
        val result = mvm.product.getOrAwaitValue()

        Assert.assertEquals(true, result is NetworkResult.Error)
        Assert.assertEquals("error", result.message)


    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}