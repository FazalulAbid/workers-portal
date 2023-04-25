package com.fifty.workersportal.domain.usecase.auth

data class PhoneNumberValidationResult(
    val successful: Boolean,
    val errorMessage:String? = null
)
