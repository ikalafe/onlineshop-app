package dehghan.daniyal.onlineshopapp.db.reposistory

import android.app.Application
import androidx.lifecycle.LiveData
import dehghan.daniyal.onlineshopapp.db.OnlineShopDataBase
import dehghan.daniyal.onlineshopapp.db.dao.UserEntityDao
import dehghan.daniyal.onlineshopapp.db.models.UserEntity

class UserEntityRepository(application: Application) {
    private var userDao: UserEntityDao
    private var currentUserEntity: LiveData<UserEntity>

    init {
        val database = OnlineShopDataBase.getInstance(application)
        userDao = database.userDao()
        currentUserEntity = userDao.get()
    }

    fun getCurrentUser(): LiveData<UserEntity> {
        return currentUserEntity
    }

    suspend fun insert(user: UserEntity) {
        deleteAll()
        userDao.add(user)
    }

    suspend fun update(user: UserEntity) {
        userDao.update(user)
    }

    suspend fun delete(user: UserEntity) {
        userDao.delete(user)
    }

    suspend fun deleteAll() {
        return userDao.deleteAll()
    }
}