package com.fifty.workersportal.data.repository

import com.fifty.workersportal.data.model.Auth
import com.fifty.workersportal.data.model.LoginResponse
import com.fifty.workersportal.data.remote.AuthApiService
import com.fifty.workersportal.domain.repository.AuthRepository
import com.fifty.workersportal.util.ApiResponse
import com.fifty.workersportal.util.apiRequestFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRepository {

    override fun sendOtpToPhone(auth: Auth) = apiRequestFlow {
        authApiService.sendOtpToPhone(auth)
    }

    override fun signupAndLogin(auth: Auth) = apiRequestFlow {
        authApiService.signupAndLogin(auth)
    }
}