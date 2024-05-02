package dehghan.daniyal.onlineshopapp.models.invoices

data class Invoice(
    var id: Long?,
    var addDate: String?,
    var items:List<InvoiceItem>?,
    var paymentDate: String?,
    var status: String?,
    var transaction:List<Transaction>,
)
