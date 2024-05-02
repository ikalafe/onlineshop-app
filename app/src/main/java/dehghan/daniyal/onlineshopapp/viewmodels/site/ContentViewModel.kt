package dehghan.daniyal.onlineshopapp.viewmodels.site

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.site.Content
import dehghan.daniyal.onlineshopapp.repositories.site.ContentRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val repository: ContentRepository,
) : ViewModel() {

    fun getContent(onResponse: (response: ServiceResponse<Content>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getContent()
            onResponse(response)
        }
    }

    fun getContentByTitle(title: String, onResponse: (response: ServiceResponse<Content>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getContentByTitle(title)
            onResponse(response)
        }
    }
}