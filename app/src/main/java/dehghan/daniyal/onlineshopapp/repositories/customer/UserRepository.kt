package dehghan.daniyal.onlineshopapp.repositories.customer

import dagger.hilt.android.scopes.ActivityScoped
import dehghan.daniyal.onlineshopapp.api.customer.UserApi
import dehghan.daniyal.onlineshopapp.models.ServiceResponse
import dehghan.daniyal.onlineshopapp.models.customer.User
import dehghan.daniyal.onlineshopapp.models.customer.UserVM
import dehghan.daniyal.onlineshopapp.repositories.base.BaseRepository
import javax.inject.Inject

@ActivityScoped
class UserRepository @Inject constructor(private val api: UserApi) : BaseRepository() {

    suspend fun getUserInfo(
        token: String,
    ): ServiceResponse<User> {
        return try {
            api.getUserInfo(prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun changePassword(
        data: UserVM,
        token: String,
    ): ServiceResponse<User> {
        return try {
            api.changePassword(data, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun login(
        data: UserVM,
    ): ServiceResponse<UserVM> {
        return try {
            api.login(data)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun register(
        data: UserVM,
    ): ServiceResponse<User> {
        return try {
            api.register(data)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun update(
        token: String,
        data: UserVM,
    ): ServiceResponse<User> {
        return try {
            api.update(prepareToken(token), data)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}