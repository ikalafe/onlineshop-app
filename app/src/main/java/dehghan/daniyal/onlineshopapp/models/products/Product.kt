import dehghan.daniyal.onlineshopapp.models.products.ProductCategory
import dehghan.daniyal.onlineshopapp.models.products.ProductSize

data class Product(
    var id: Long? = null,
    var addDate: String? = "",
    var category: ProductCategory? = null,
    var colors: List<ProductCategory>? = null,
    var description: String? = "",
    var image: String? = "null",
    var price: Long? = 0,
    var sizes: List<ProductSize>? = null,
    var title: String? = "",
    var visitCount: Int? = 0,
)