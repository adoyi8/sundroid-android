package com.sundroid.sundroid.repositories

import com.sundroid.sundroid.data.local.dao.UserDao
import com.sundroid.sundroid.models.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    val users: Flow<List<User>> = userDao.getUsers()

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }
}