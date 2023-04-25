package com.fifty.workersportal.data.remote

import com.fifty.workersportal.data.model.AuthRequest
import com.fifty.workersportal.data.model.LoginResponse
import com.fifty.workersportal.data.model.OtpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApiService {

    @POST("user/sent-otp")
    suspend fun sendOtpToPhone(
        @Body auth: AuthRequest
    ): Response<OtpResponse>

    @POST("user/signup-and-login")
    suspend fun signupAndLogin(
        @Body auth: AuthRequest
    ): Response<LoginResponse>

    @GET("refresh-token")
    suspend fun refreshToken(
        @Header("Authorization") token: String
    ): Response<LoginResponse>

}