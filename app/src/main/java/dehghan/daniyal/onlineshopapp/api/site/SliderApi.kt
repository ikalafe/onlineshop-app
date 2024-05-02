package dehghan.daniyal.onlineshopapp.api.site

import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.site.Slider
import retrofit2.http.GET
import retrofit2.http.Path

interface SliderApi {
    @GET("/api/slider")
    suspend fun getSliders(): ServiceResponse<Slider>

    @GET("/api/slider/{id}")
    suspend fun getSliderById(@Path("id") id: Long): ServiceResponse<Slider>
}