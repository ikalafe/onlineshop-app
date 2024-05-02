package dehghan.daniyal.onlineshopapp.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import dehghan.daniyal.onlineshopapp.models.products.ProductCategory
import dehghan.daniyal.onlineshopapp.models.products.ProductSize

@Entity
data class BasketEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var productId: Long,
    var quntity: Int,
    var colorId: Long?,
    var sizeId: Long?,
    var image: String?,
    var price: Long?,
    var title: String?,
    var size: String,
)