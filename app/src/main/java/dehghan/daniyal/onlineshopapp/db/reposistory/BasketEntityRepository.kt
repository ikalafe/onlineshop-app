package dehghan.daniyal.onlineshopapp.db.reposistory

import android.app.Application
import androidx.lifecycle.LiveData
import dehghan.daniyal.onlineshopapp.db.OnlineShopDataBase
import dehghan.daniyal.onlineshopapp.db.dao.BasketEntityDao
import dehghan.daniyal.onlineshopapp.db.models.BasketEntity

class BasketEntityRepository(application: Application) {
    private var basketDao: BasketEntityDao
    private var liveDataList:LiveData<List<BasketEntity>>

    init {
        val database = OnlineShopDataBase.getInstance(application)
        basketDao = database.basketDao()
        liveDataList = basketDao.getAllLive()
    }

    suspend fun insert(basket: BasketEntity) {
        basketDao.add(basket)
    }

    suspend fun update(basket: BasketEntity) {
        basketDao.update(basket)
    }

    suspend fun delete(basket: BasketEntity) {
        basketDao.delete(basket)
    }

    suspend fun deleteAll() {
        return basketDao.deleteAll()
    }

    suspend fun getAllBasketList(): List<BasketEntity> {
        return basketDao.getAll()
    }

    fun getAllBasketLive(): LiveData<List<BasketEntity>> {
        return liveDataList
    }

    suspend fun incrementQuantity(basket: BasketEntity) {
        basket.quntity++
        update(basket)
    }

    suspend fun decrementQuantity(basket: BasketEntity) {
        basket.quntity--
        if(basket.quntity <= 0) delete(basket)
        else update(basket)
    }
}