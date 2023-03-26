package com.sundroid.sundroid.data.local.dao.database_models


import androidx.room.Entity
import androidx.room.PrimaryKey













@Entity(tableName = "staff")
data class Staff
@JvmOverloads constructor(
    @PrimaryKey(autoGenerate = true) var staffId: Int = 0,
    var name: String = "",
    var email: String = "",
    var phone: String = ""
)
