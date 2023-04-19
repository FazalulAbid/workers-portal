package com.fifty.workersportal.data.model

import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("data")
    val userInfo: UserInfo
)