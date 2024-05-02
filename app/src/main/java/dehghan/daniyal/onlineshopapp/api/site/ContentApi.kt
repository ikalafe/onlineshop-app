package dehghan.daniyal.onlineshopapp.api.site

import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.site.Content
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentApi {
    @GET("/api/content")
    suspend fun getContent(): ServiceResponse<Content>

    @GET("/api/content/{title}")
    suspend fun getContentByTitle(@Path("title") id: String): ServiceResponse<Content>
}