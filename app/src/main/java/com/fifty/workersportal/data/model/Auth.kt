package com.fifty.workersportal.data.model

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("countrycode")
    val countryCode: String,
    val otp: String,
    @SerializedName("phonenumber")
    val phoneNumber: String
)
