package dehghan.daniyal.onlineshopapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import dehghan.daniyal.onlineshopapp.db.models.UserEntity

@Dao
interface UserEntityDao {

    @Insert
    fun add(userEntity: UserEntity)

    @Update
    fun update(userEntity: UserEntity)

    @Delete
    fun delete(userEntity: UserEntity)

    @Query("select * from UserEntity limit 1")
    fun get(): LiveData<UserEntity>

    @Query("delete from UserEntity")
    fun deleteAll()
}