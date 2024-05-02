package dehghan.daniyal.onlineshopapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dehghan.daniyal.onlineshopapp.db.dao.BasketEntityDao
import dehghan.daniyal.onlineshopapp.db.dao.UserEntityDao
import dehghan.daniyal.onlineshopapp.db.models.BasketEntity
import dehghan.daniyal.onlineshopapp.db.models.UserEntity

@Database(entities = [UserEntity::class, BasketEntity::class], version = 10)
abstract class OnlineShopDataBase : RoomDatabase() {

    abstract fun userDao(): UserEntityDao
    abstract fun basketDao(): BasketEntityDao

    companion object {
        private var instance: OnlineShopDataBase? = null
        fun getInstance(context: Context): OnlineShopDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    OnlineShopDataBase::class.java,
                    "onlineshop.db"
                ).fallbackToDestructiveMigration().build()
            }
            return instance as OnlineShopDataBase
        }
    }
}