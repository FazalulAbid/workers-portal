package com.fifty.workersportal.presentation.login

data class PhoneNumberFormState(
    val phoneNumber: String = "",
    var phoneNumberError: String? = null
)