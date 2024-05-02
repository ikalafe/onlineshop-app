package dehghan.daniyal.onlineshopapp.repositories.invoices

import dagger.hilt.android.scopes.ActivityScoped
import dehghan.daniyal.onlineshopapp.api.invoices.TransactionApi
import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.invoices.PaymentTransaction
import javax.inject.Inject

@ActivityScoped
class TransactionRepository @Inject constructor(private val api: TransactionApi) {

    suspend fun goToPayment(
        data: PaymentTransaction,
    ): ServiceResponse<String> {
        return try {
            api.goToPayment(data)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}