package com.example.mvvmtesting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmtesting.R
import com.example.mvvmtesting.models.ProductListItem

class ProductAdapter(private val productList: List<ProductListItem>) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.nameTextView.text = ""+product.id
        //holder.descriptionTextView.text = product.description
    }

    override fun getItemCount(): Int = productList.size
}

class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameTextView: TextView = itemView.findViewById(R.id.product_name)
    val descriptionTextView: TextView = itemView.findViewById(R.id.product_description)

}