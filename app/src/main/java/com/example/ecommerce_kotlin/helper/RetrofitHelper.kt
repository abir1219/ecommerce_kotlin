package com.example.ecommerce_kotlin.helper

import ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    val apiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}