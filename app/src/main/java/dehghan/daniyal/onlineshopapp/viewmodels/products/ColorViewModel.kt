package dehghan.daniyal.onlineshopapp.viewmodels.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.products.ProductColor
import dehghan.daniyal.onlineshopapp.repositories.products.ColorRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorViewModel @Inject constructor(
    private val repository: ColorRepository,
) : ViewModel() {

    fun getColors(onResponse: (response: ServiceResponse<ProductColor>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getColors()
            onResponse(response)
        }
    }

    fun getColorById(id: Long, onResponse: (response: ServiceResponse<ProductColor>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getColorById(id)
            onResponse(response)
        }
    }
}