package dehghan.daniyal.onlineshopapp.viewmodels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.site.Blog
import dehghan.daniyal.onlineshopapp.repositories.site.BlogRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlogViewModel @Inject constructor(
    private val repository: BlogRepository,
) : ViewModel() {

    fun getBlog(onResponse: (response: ServiceResponse<Blog>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getBlog()
            onResponse(response)
        }
    }

    fun getBlogById(id: Long, onResponse: (response: ServiceResponse<Blog>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getBlogById(id)
            onResponse(response)
        }
    }
}