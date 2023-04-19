package com.fifty.workersportal.data.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    val id: Int,
    @SerializedName("first_name")
    val firstName: String,
    val password: String,
    @SerializedName("user_type")
    val userType: String,
    val verification: Boolean
)