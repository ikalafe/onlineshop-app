package dehghan.daniyal.onlineshopapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dehghan.daniyal.onlineshopapp.db.models.BasketEntity

@Dao
interface BasketEntityDao {

    @Insert
    fun add(basketEntity: BasketEntity)

    @Update
    fun update(basketEntity: BasketEntity)

    @Delete
    fun delete(basketEntity: BasketEntity)

    @Query("select * from BasketEntity")
    fun getAll(): List<BasketEntity>

    @Query("delete from BasketEntity")
    fun deleteAll()

    @Query("select * from BasketEntity")
    fun getAllLive(): LiveData<List<BasketEntity>>
}