package dehghan.daniyal.onlineshopapp.models.invoices

import Product
import dehghan.daniyal.onlineshopapp.db.models.BasketEntity

data class InvoiceItem(
    var id: Long? = null,
    var product: Product?,
    var quantity: Int?,
    var unitPrice: Long? = 0,
) {
    companion object {
        fun convertFromBasket(basketEntity: BasketEntity): InvoiceItem {
            return InvoiceItem(
                id = null,
                product = Product(id = basketEntity.productId),
                quantity = basketEntity.quntity,
            )
        }
    }
}
