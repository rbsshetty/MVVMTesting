package com.example.mvvmtesting.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmtesting.models.ProductListItem
import com.example.mvvmtesting.repository.ProductRepository
import com.example.mvvmtesting.utils.NetworkResult
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductRepository) : ViewModel(){


    private val _product = MutableLiveData<NetworkResult<List<ProductListItem>>>()
    val product : LiveData<NetworkResult<List<ProductListItem>>>
    get() = _product

    fun getProduct(){
        viewModelScope.launch  {
            val result = repository.getProduct()
            _product.postValue(result)
        }
    }
}