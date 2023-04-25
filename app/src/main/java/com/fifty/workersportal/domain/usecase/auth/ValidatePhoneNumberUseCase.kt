package com.fifty.workersportal.domain.usecase.auth

import android.util.Patterns
import java.util.regex.Pattern

class ValidatePhoneNumberUseCase {
    operator fun invoke(phoneNumber: String): PhoneNumberValidationResult {
        if (phoneNumber.isBlank()) {
            return PhoneNumberValidationResult(
                successful = false,
                errorMessage = "Please enter a phone number"
            )
        }
        if (phoneNumber.length != 10) {
            return PhoneNumberValidationResult(
                successful = false,
                errorMessage = "Phone number should have 10 digits"
            )
        }
        if (!Patterns.PHONE.matcher(phoneNumber).matches()) {
            return PhoneNumberValidationResult(
                successful = false,
                errorMessage = "Please enter a valid phone number"
            )
        }
        return PhoneNumberValidationResult(
            successful = true
        )
    }
}