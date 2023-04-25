package com.fifty.workersportal.data.repository.auth

import com.fifty.workersportal.data.model.AuthRequest
import com.fifty.workersportal.data.model.OtpResponse
import com.fifty.workersportal.data.remote.AuthApiService
import com.fifty.workersportal.domain.repository.AuthRepository
import com.fifty.workersportal.util.ApiException
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRepository {
    override suspend fun sentOtpToPhoneNumber(auth: AuthRequest): Response<OtpResponse> =
        authApiService.sendOtpToPhone(auth)

}