package com.sundroid.sundroid.repositories

import com.sundroid.sundroid.data.local.dao.UserDao
import com.sundroid.sundroid.models.Job
import com.sundroid.sundroid.models.RoomUserEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    val users: Flow<List<RoomUserEntity>> = userDao.getUsers()
    val jobs: Flow<List<Job>> = userDao.getAllJobs()

    suspend fun insertUser(user: RoomUserEntity) {
        userDao.insertUser(user)
    }

    suspend fun deleteUser(user: RoomUserEntity) {
        userDao.deleteUser(user)
    }
    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    suspend fun insertJob(job: Job) {
        userDao.insertJob(job)
    }

    suspend fun updateJob(job: Job) {
        userDao.updateJob(job)
    }
    suspend fun deleteJob(job: Job) {
        userDao.deleteJob(job)
    }
    suspend fun deleteAllJobs() {
        userDao.deleteAllJobs()
    }
}