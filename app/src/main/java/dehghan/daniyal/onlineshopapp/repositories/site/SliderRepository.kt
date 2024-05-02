package dehghan.daniyal.onlineshopapp.repositories.site

import dagger.hilt.android.scopes.ActivityScoped
import dehghan.daniyal.onlineshopapp.api.site.SliderApi
import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.site.Slider
import javax.inject.Inject

@ActivityScoped
class SliderRepository @Inject constructor(private val api: SliderApi) {
    suspend fun getSliders(): ServiceResponse<Slider> {
        return try {
            api.getSliders()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getSliderById(id:Long): ServiceResponse<Slider> {
        return try {
            api.getSliderById(id = id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}