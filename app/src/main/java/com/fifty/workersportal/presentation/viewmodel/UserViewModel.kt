package com.fifty.workersportal.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.fifty.workersportal.data.model.UserInfoResponse
import com.fifty.workersportal.domain.repository.UserRepository
import com.fifty.workersportal.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {
    private val _userInfoResponse = MutableLiveData<ApiResponse<UserInfoResponse>>()
    val userInfoResponse = _userInfoResponse

    fun getUserInfo(coroutinesErrorHandler: CoroutinesErrorHandler) = baseRequest(
        _userInfoResponse, coroutinesErrorHandler
    ) {
        userRepository.getUserInfo()
    }

}