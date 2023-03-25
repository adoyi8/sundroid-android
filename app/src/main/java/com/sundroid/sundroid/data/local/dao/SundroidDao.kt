package com.sundroid.sundroid.data.local.dao

import androidx.room.*
import com.sundroid.sundroid.data.local.dao.database_models.Job
import com.sundroid.sundroid.data.local.dao.database_models.RoomUserEntity
import com.sundroid.sundroid.data.local.dao.database_models.Shop
import kotlinx.coroutines.flow.Flow

@Dao
interface SundroidDao {
    @Query("SELECT * FROM user")
    fun getUsers(): Flow<List<RoomUserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: RoomUserEntity)

    @Delete
    suspend fun deleteUser(user: RoomUserEntity)
    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()
    @Query("SELECT * FROM job")
    fun getAllJobs(): Flow<List<Job>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: Job)
    @Delete
    suspend fun deleteJob(job: Job)
    @Query("DELETE FROM job")
    suspend fun deleteAllJobs()

    @Update
    suspend fun updateJob(job: Job)


    @Query("SELECT * FROM shop")
    fun getAllShops(): Flow<List<Shop>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addShop(shop: Shop)
    @Delete
    suspend fun removeShop(shop: Shop)
    @Query("DELETE FROM shop")
    suspend fun deleteAllShops()

    @Update
    suspend fun updateShop(shop: Shop)

}