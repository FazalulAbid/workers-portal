package com.fifty.workersportal.data.remote

import com.fifty.workersportal.data.model.UserInfo
import com.fifty.workersportal.data.model.UserInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("/user/profile")
    suspend fun getUserInfo(): Response<UserInfoResponse>

    @POST("/user/profile")
    suspend fun saveUserInfo(
        @Body userInfo: UserInfo
    ): Response<UserInfoResponse>
}