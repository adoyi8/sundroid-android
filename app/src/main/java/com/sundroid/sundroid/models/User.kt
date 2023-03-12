package com.sundroid.sundroid.models

data class User(
    val email: String,
    val displayName: String,
    val familyName: String,
    val givenName: String,
    val idToken: String,
    val photoUrl: String?,
    val serverAuthCode: String?
)
