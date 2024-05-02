package dehghan.daniyal.onlineshopapp.models.invoices

import dehghan.daniyal.onlineshopapp.models.customer.User

data class Transaction(
    var amount: Int?,
    var cardHolder: String?,
    var code: Int?,
    var custom: String?,
    var customerPhone: String?,
    var id: Long?,
    var orderId: String?,
    var refId: String?,
    var refundRequest: String?,
    var shaparakRefId: String?,
    var transId: String?,
    var user: User,
)
