import com.example.ecommerce_kotlin.model.product_model.ProductModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("products")
    fun getAllProducts() : Call<ProductModel>
}