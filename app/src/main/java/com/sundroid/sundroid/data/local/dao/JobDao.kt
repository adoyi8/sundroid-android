package com.sundroid.sundroid.data.local.dao

import androidx.room.*
import com.sundroid.sundroid.models.Job
import com.sundroid.sundroid.models.RoomUserEntity
import kotlinx.coroutines.flow.Flow















@Dao
interface JobDao {
    @Query("SELECT * FROM  jobs")
    fun getAllJobs(): Flow<List<RoomUserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(job: Job)

    @Delete
    suspend fun deleteUser(job: Job)
    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}