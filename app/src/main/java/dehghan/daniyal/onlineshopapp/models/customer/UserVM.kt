package dehghan.daniyal.onlineshopapp.models.customer

import dehghan.daniyal.onlineshopapp.db.models.UserEntity

data class UserVM(
    var address: String? = "",
    var customerId: Long? = null,
    var firstName: String? = "",
    var id: Long? = null,
    var lastName: String? = "",
    var oldPassword: String? = null,
    var password: String? = null,
    var phone: String? = "",
    var postalCode: String? = "",
    var repeatPassword: String? = null,
    var token: String? = null,
    var username: String?,
) {
    fun convertToEntity(): UserEntity {
        return UserEntity(
            userId = id!!,
            address = address,
            customerId = customerId!!,
            firstName = firstName,
            lastName = lastName,
            phone = phone,
            postalCode = postalCode,
            token = token,
            username = username
        )
    }
}
