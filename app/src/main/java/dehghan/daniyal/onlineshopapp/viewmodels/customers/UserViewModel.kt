package dehghan.daniyal.onlineshopapp.viewmodels.customers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.customer.User
import dehghan.daniyal.onlineshopapp.models.customer.UserVM
import dehghan.daniyal.onlineshopapp.repositories.customer.UserRepository
import dehghan.daniyal.onlineshopapp.utils.ThisApp
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository,
) : ViewModel() {

    var token: String = ThisApp.token

    fun getUserInfo(onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            val response = repository.getUserInfo(token)
            onResponse(response)
        }
    }

    fun changePassword(data: UserVM, onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            val response = repository.changePassword(data, token)
            onResponse(response)
        }
    }

    fun login(data: UserVM, onResponse: (response: ServiceResponse<UserVM>) -> Unit) {
        viewModelScope.launch {
            val response = repository.login(data)
            onResponse(response)
        }
    }

    fun register(data: UserVM, onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            val response = repository.register(data)
            onResponse(response)
        }
    }

    fun update(data: UserVM, onResponse: (response: ServiceResponse<User>) -> Unit) {
        viewModelScope.launch {
            val response = repository.update(token, data)
            onResponse(response)
        }
    }
}