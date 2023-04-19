package com.fifty.workersportal.domain.repository

import com.fifty.workersportal.data.model.UserInfo
import com.fifty.workersportal.data.model.UserInfoResponse
import com.fifty.workersportal.util.ApiResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.POST

interface UserRepository {
    @POST("/user/profile")
    fun saveUserInfo(userInfo: UserInfo): Flow<ApiResponse<UserInfoResponse>>
    fun getUserInfo(): Flow<ApiResponse<UserInfoResponse>>
}