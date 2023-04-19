package com.fifty.workersportal.data.repository

import com.fifty.workersportal.data.model.UserInfo
import com.fifty.workersportal.data.model.UserInfoResponse
import com.fifty.workersportal.data.remote.ApiService
import com.fifty.workersportal.data.remote.AuthApiService
import com.fifty.workersportal.domain.repository.UserRepository
import com.fifty.workersportal.util.ApiResponse
import com.fifty.workersportal.util.apiRequestFlow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {

    override fun saveUserInfo(userInfo: UserInfo) = apiRequestFlow {
        apiService.saveUserInfo(userInfo)
    }

    override fun getUserInfo() = apiRequestFlow {
        apiService.getUserInfo()
    }
}