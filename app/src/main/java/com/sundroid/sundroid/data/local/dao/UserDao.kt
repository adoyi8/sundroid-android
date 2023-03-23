package com.sundroid.sundroid.data.local.dao

import androidx.room.*
import com.sundroid.sundroid.models.Job
import com.sundroid.sundroid.models.RoomUserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<RoomUserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: RoomUserEntity)

    @Delete
    suspend fun deleteUser(user: RoomUserEntity)
    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM jobs")
    fun getAllJobs(): Flow<List<Job>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: Job)

    @Delete
    suspend fun deleteJob(job: Job)
    @Query("DELETE FROM jobs")
    suspend fun deleteAllJobs()

    @Update
    suspend fun updateJob(job: Job)

}