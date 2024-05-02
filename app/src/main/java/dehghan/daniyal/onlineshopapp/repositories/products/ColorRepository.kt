package dehghan.daniyal.onlineshopapp.repositories.products

import dagger.hilt.android.scopes.ActivityScoped
import dehghan.daniyal.onlineshopapp.api.products.ColorApi
import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.products.ProductColor
import javax.inject.Inject

@ActivityScoped
class ColorRepository @Inject constructor(private val api: ColorApi) {
    suspend fun getColors(): ServiceResponse<ProductColor> {
        return try {
            api.getColor()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getColorById(id:Long): ServiceResponse<ProductColor> {
        return try {
            api.getColorById(id = id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}