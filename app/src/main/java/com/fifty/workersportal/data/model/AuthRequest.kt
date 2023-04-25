package com.fifty.workersportal.data.model

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("countrycode")
    val countryCode: String,
    val otp: String,
    @SerializedName("phonenumber")
    val phoneNumber: String
)
