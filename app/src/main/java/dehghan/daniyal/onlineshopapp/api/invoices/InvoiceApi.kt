package dehghan.daniyal.onlineshopapp.api.invoices

import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.invoices.Invoice
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface InvoiceApi {

    @POST("/api/invoice")
    suspend fun addInvoice(
        @Body data: Invoice,
        @Header("Authorization") token: String,
    ): ServiceResponse<Invoice>

    @GET("/api/invoice/{id}")
    suspend fun getInvoiceById(
        @Path("id") id: Long,
        @Header("Authorization") token: String,
    ): ServiceResponse<Invoice>

    @GET("/api/invoice/user/{userId}")
    suspend fun getInvoiceByUserId(
        @Path("userId") id: Long,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int,
        @Header("Authorization") token: String,
    ): ServiceResponse<Invoice>
}