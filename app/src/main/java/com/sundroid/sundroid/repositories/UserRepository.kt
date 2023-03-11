package com.sundroid.sundroid.repositories

import com.sundroid.sundroid.data.local.dao.UserDao
import com.sundroid.sundroid.models.RoomUserEntity
import com.sundroid.sundroid.models.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    val users: Flow<List<RoomUserEntity>> = userDao.getUsers()

    suspend fun insertUser(user: RoomUserEntity) {
        userDao.insertUser(user)
    }

    suspend fun deleteUser(user: RoomUserEntity) {
        userDao.deleteUser(user)
    }
}