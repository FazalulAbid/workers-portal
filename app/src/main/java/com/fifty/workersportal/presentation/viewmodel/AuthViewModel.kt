package com.fifty.workersportal.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.fifty.workersportal.data.model.Auth
import com.fifty.workersportal.data.model.LoginResponse
import com.fifty.workersportal.domain.repository.AuthRepository
import com.fifty.workersportal.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : BaseViewModel() {

    private val TAG = "AuthViewModel"

    private val _loginResponse = MutableLiveData<ApiResponse<LoginResponse>>()
    val loginResponse = _loginResponse

    fun login(auth: Auth, coroutinesErrorHandler: CoroutinesErrorHandler) =
        baseRequest(_loginResponse, coroutinesErrorHandler) {
            authRepository.sendOtpToPhone(auth)
        }
}