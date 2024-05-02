package dehghan.daniyal.onlineshopapp.viewmodels.invoices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.invoices.PaymentTransaction
import dehghan.daniyal.onlineshopapp.repositories.invoices.TransactionRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository,
) : ViewModel() {

    fun goToPayment(
        data: PaymentTransaction,
        onResponse: (response: ServiceResponse<String>) -> Unit,
    ) {
        viewModelScope.launch {
            val response = repository.goToPayment(data)
            onResponse(response)
        }
    }
}