package com.fifty.workersportal.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("access-token")
    val accessToken: String,
    @SerializedName("refresh-token")
    val refreshToken: String
)
