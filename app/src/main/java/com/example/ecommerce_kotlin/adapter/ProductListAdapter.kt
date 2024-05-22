package com.example.ecommerce_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerce_kotlin.databinding.ChildProductBinding
import com.example.ecommerce_kotlin.model.product_model.ProductModelItem

class ProductListAdapter(private val products: List<ProductModelItem>) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ChildProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
//        val productName: TextView = view.findViewById(R.id.tvProdName)
//        val productPrice: TextView = view.findViewById(R.id.tvPrice)
//        val productRating: TextView = view.findViewById(R.id.tvRating)
//        val productImage: ImageView = view.findViewById(R.id.ivProdImg)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ChildProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.apply {

            tvProdName.text = products[position].title
            tvPrice.text = "₹${products[position].price.toString()}"
            tvRating.text = products[position].rating.rate.toString()
            //ivProdImg.setImageResource(products[position].image)
            Glide.with(ivProdImg.context).load(products[position].image).into(ivProdImg)
        }
    }
}