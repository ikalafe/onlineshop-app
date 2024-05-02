package dehghan.daniyal.onlineshopapp.repositories.products

import dagger.hilt.android.scopes.ActivityScoped
import dehghan.daniyal.onlineshopapp.api.products.ProductCategoryApi
import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.products.ProductCategory
import javax.inject.Inject

@ActivityScoped
class ProductCategoryRepository @Inject constructor(private val api: ProductCategoryApi) {

    suspend fun getCategory(): ServiceResponse<ProductCategory> {
        return try {
            api.getCategory()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getCategoryById(id:Long): ServiceResponse<ProductCategory> {
        return try {
            api.getCategoryById(id = id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}