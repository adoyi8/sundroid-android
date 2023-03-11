package com.sundroid.sundroid.data.local.dao

import androidx.room.*
import com.sundroid.sundroid.models.RoomUserEntity
import com.sundroid.sundroid.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<RoomUserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: RoomUserEntity)

    @Delete
    suspend fun deleteUser(user: RoomUserEntity)
}