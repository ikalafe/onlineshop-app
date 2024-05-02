package dehghan.daniyal.onlineshopapp.viewmodels.site

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.site.Slider
import dehghan.daniyal.onlineshopapp.repositories.site.SliderRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor(
    private val repository: SliderRepository,
) : ViewModel() {

    var dataList = mutableStateOf<List<Slider>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        viewModelScope.launch {
            getSliders { response ->
                isLoading.value = false
                if (response.status == "OK".uppercase()) {
                    dataList.value = response.data!!
                }
            }
        }
    }

    fun getSliders(onResponse: (response: ServiceResponse<Slider>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getSliders()
            onResponse(response)
        }
    }

    fun getSliderById(id: Long, onResponse: (response: ServiceResponse<Slider>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getSliderById(id)
            onResponse(response)
        }
    }
}