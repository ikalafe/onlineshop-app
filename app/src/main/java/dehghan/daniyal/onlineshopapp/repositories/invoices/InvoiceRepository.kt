package dehghan.daniyal.onlineshopapp.repositories.invoices

import dagger.hilt.android.scopes.ActivityScoped
import dehghan.daniyal.onlineshopapp.api.invoices.InvoiceApi
import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.invoices.Invoice
import dehghan.daniyal.onlineshopapp.repositories.base.BaseRepository
import javax.inject.Inject

@ActivityScoped
class InvoiceRepository @Inject constructor(private val api: InvoiceApi) : BaseRepository() {

    suspend fun getInvoiceById(
        id: Long,
        token: String,
    ): ServiceResponse<Invoice> {
        return try {
            api.getInvoiceById(id = id, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getInvoiceByUserId(
        userId: Long,
        pageIndex: Int,
        pageSize: Int,
        token: String,
    ): ServiceResponse<Invoice> {
        return try {
            api.getInvoiceByUserId(id = userId, pageIndex, pageSize, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun addInvoice(
        data: Invoice,
        token: String,
    ): ServiceResponse<Invoice> {
        return try {
            api.addInvoice(data, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}