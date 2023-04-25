package com.fifty.workersportal.domain.usecase.auth

import com.fifty.workersportal.data.model.AuthRequest
import com.fifty.workersportal.data.model.OtpResponse
import com.fifty.workersportal.domain.repository.AuthRepository
import com.fifty.workersportal.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher
import okhttp3.Request
import javax.inject.Inject

class SendOtpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(auth: AuthRequest): Flow<Resource<OtpResponse>> = flow {
        emit(Resource.Loading())

        val response = authRepository.sentOtpToPhoneNumber(auth)

        if (response.isSuccessful && response.body() != null) {
            emit(Resource.Success(response.body()!!))
        } else {
            emit(Resource.Error(response.message()))
        }
    }.catch { exception ->
        emit(Resource.Error(exception.localizedMessage ?: "Something went wrong"))
    }.flowOn(Dispatchers.IO)
}