package com.example.ecommerce_kotlin

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerce_kotlin.adapter.ProductListAdapter
import com.example.ecommerce_kotlin.databinding.ActivityMainBinding
import com.example.ecommerce_kotlin.helper.RetrofitHelper
import com.example.ecommerce_kotlin.model.product_model.ProductModel
import com.example.ecommerce_kotlin.model.product_model.ProductModelItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var productList = listOf<ProductModelItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getData()
    }

    private fun getData() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitHelper.apiInterface.getAllProducts().enqueue(object :
                    Callback<ProductModel?> {
                    override fun onResponse(
                        call: Call<ProductModel?>,
                        response: Response<ProductModel?>
                    ) {
                        println("RESPONSE-->${response.body().toString()}")
                        productList = response.body()!!
                        println("Title-->${productList[0].title}")
                        binding.rvProduct.adapter = ProductListAdapter(productList).apply {
//                            binding.rvProduct.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL, false)
                            binding.rvProduct.layoutManager = GridLayoutManager(this@MainActivity,2)
                        }
                    }

                    override fun onFailure(call: Call<ProductModel?>, t: Throwable) {
                        t.localizedMessage?.let { Log.e("ERROR-->", it) }
                    }
                })
            } catch (e: IOException) {
                Log.e("APP_ERROR", "app error ${e.message}")
                return@launch
            } catch (e: HttpException) {
//                Toast.makeText(applicationContext,"http error ${e.message}",Toast.LENGTH_LONG).show()
                Log.e("HTTP_ERROR", "http error ${e.message}")
                return@launch
            }
        }
    }
}