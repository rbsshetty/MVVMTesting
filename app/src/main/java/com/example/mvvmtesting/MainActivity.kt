package com.example.mvvmtesting

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtesting.adapter.ProductAdapter
import com.example.mvvmtesting.utils.NetworkResult
import com.example.mvvmtesting.viewmodels.MainViewModel
import com.example.mvvmtesting.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)



        val repository =  (application as StoreApplication).productRepository
        mainViewModel = ViewModelProvider.create(this,MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.getProduct()

        mainViewModel.product.observe(this, Observer {

            when(it){
               is NetworkResult.Success -> {
                   val adapter = it.data?.let { list -> ProductAdapter(list) }
                   recyclerView.adapter = adapter
               }

                is NetworkResult.Error -> {
                }

                is NetworkResult.Loading -> {}
            }
        })






    }
}