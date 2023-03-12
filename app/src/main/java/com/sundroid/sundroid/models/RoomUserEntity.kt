package com.sundroid.sundroid.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")










data class RoomUserEntity @JvmOverloads constructor( @PrimaryKey(autoGenerate = true) val id: Int = 0,
                                                     val email: String? = "",
                                                     val displayName: String?,
                                                     val familyName: String?,
                                                     val givenName: String?,
                                                     val idToken: String?,
                                                     val photoUrl: String?,
                                                     val serverAuthCode: String?)



