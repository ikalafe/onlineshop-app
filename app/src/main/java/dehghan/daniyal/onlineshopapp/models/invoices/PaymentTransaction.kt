package dehghan.daniyal.onlineshopapp.models.invoices

import dehghan.daniyal.onlineshopapp.models.customer.UserVM

data class PaymentTransaction(
    var items: List<InvoiceItem>,
    var user: UserVM,
)
