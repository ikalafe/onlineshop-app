package dehghan.daniyal.onlineshopapp.repositories.site

import dagger.hilt.android.scopes.ActivityScoped
import dehghan.daniyal.onlineshopapp.api.site.BlogApi
import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.site.Blog
import javax.inject.Inject

@ActivityScoped
class BlogRepository @Inject constructor(private val api: BlogApi) {
    suspend fun getBlog(): ServiceResponse<Blog> {
        return try {
            api.getBlog()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getBlogById(id:Long): ServiceResponse<Blog> {
        return try {
            api.getBlogById(id = id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}