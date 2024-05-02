package dehghan.daniyal.onlineshopapp.api.products

import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.products.ProductCategory
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductCategoryApi {
    @GET("/api/productCategory")
    suspend fun getCategory(): ServiceResponse<ProductCategory>

    @GET("/api/productCategory/{id}")
    suspend fun getCategoryById(@Path("id") id: Long): ServiceResponse<ProductCategory>
}