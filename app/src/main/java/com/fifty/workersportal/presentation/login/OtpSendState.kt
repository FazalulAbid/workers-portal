package com.fifty.workersportal.presentation.login

import com.fifty.workersportal.domain.model.Country

data class OtpSendState(
    val isLoading: Boolean = false,
    val isSent: Boolean = false,
    val error: String? = null
)