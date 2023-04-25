package com.fifty.workersportal.domain.repository

import com.fifty.workersportal.data.model.AuthRequest
import com.fifty.workersportal.data.model.OtpResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun sentOtpToPhoneNumber(auth: AuthRequest): Response<OtpResponse>
}