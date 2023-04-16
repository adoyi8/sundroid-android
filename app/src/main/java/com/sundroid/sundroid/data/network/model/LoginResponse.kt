package com.sundroid.sundroid.data.network.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") val token: String?)
