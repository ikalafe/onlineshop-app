package dehghan.daniyal.onlineshopapp.api.products

import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.products.ProductColor
import retrofit2.http.GET
import retrofit2.http.Path

interface ColorApi {
    @GET("/api/color")
    suspend fun getColor(): ServiceResponse<ProductColor>

    @GET("/api/color/{id}")
    suspend fun getColorById(@Path("id") id: Long): ServiceResponse<ProductColor>
}