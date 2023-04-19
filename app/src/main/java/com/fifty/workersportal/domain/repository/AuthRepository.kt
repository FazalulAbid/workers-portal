package com.fifty.workersportal.domain.repository

import com.fifty.workersportal.data.model.Auth
import com.fifty.workersportal.data.model.LoginResponse
import com.fifty.workersportal.util.ApiResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun sendOtpToPhone(auth: Auth): Flow<ApiResponse<LoginResponse>>
    fun signupAndLogin(auth: Auth): Flow<ApiResponse<LoginResponse>>
}