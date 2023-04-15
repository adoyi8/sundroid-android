package com.sundroid.sundroid.data.local.dao.database_models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")















data class RoomUserEntity @JvmOverloads constructor(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                                                    val email: String = "",
                                                    val displayName: String?,
                                                    val familyName: String?,
                                                    val givenName: String?,
                                                    var accessToken: String?,
                                                    val photoUrl: String?,
                                                    val serverAuthCode: String?)



