package com.fifty.workersportal.presentation.login

sealed class PhoneNumberFormEvent {
    data class PhoneNumberChanged(val phoneNumber:String): PhoneNumberFormEvent()

    object Submit: PhoneNumberFormEvent()
}