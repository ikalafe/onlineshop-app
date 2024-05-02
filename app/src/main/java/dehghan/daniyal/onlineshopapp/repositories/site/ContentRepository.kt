package dehghan.daniyal.onlineshopapp.repositories.site

import dagger.hilt.android.scopes.ActivityScoped
import dehghan.daniyal.onlineshopapp.api.site.ContentApi
import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.site.Content
import javax.inject.Inject

@ActivityScoped
class ContentRepository @Inject constructor(private val api: ContentApi) {
    suspend fun getContent(): ServiceResponse<Content> {
        return try {
            api.getContent()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getContentByTitle(title:String): ServiceResponse<Content> {
        return try {
            api.getContentByTitle(id = title)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}