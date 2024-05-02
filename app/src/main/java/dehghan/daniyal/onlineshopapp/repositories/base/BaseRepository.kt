package dehghan.daniyal.onlineshopapp.repositories.base

import java.util.Locale

open class BaseRepository {
    protected fun prepareToken(token: String): String {
        var fixedToken = token
        if (!fixedToken.lowercase(Locale.getDefault()).startsWith("bearer")) {
            fixedToken = "Bearer $token"
        }
        return fixedToken
    }
}