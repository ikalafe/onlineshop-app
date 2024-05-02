package dehghan.daniyal.onlineshopapp.api.invoices

import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.invoices.PaymentTransaction
import dehghan.daniyal.onlineshopapp.models.products.ProductColor
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionApi {

    @POST("/api/trx/gotoPayment")
    suspend fun goToPayment(@Body data: PaymentTransaction): ServiceResponse<String>
}