package com.sundroid.sundroid.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")










data class RoomUserEntity @JvmOverloads constructor( @PrimaryKey(autoGenerate = true) val id: Int = 0, val name: String, val email: String = "")



