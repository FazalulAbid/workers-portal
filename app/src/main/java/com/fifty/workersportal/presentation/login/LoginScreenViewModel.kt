package com.fifty.workersportal.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fifty.workersportal.data.model.AuthRequest
import com.fifty.workersportal.domain.usecase.auth.SendOtpUseCase
import com.fifty.workersportal.domain.usecase.auth.ValidatePhoneNumberUseCase
import com.fifty.workersportal.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
    private val sendOtpUseCase: SendOtpUseCase
) : ViewModel() {

    var phoneNumberFormState by mutableStateOf(PhoneNumberFormState())

    private val phoneNumberValidateEventChannel = Channel<PhoneNumberValidationEvent>()
    val phoneNumberValidationEvents = phoneNumberValidateEventChannel.receiveAsFlow()

    private val _otpSendState = MutableLiveData<Resource<OtpSendState>>()
    val otpSendState: LiveData<Resource<OtpSendState>> = _otpSendState

    fun onPhoneNumberValidationEvent(event: PhoneNumberFormEvent) {
        when (event) {
            is PhoneNumberFormEvent.PhoneNumberChanged -> {
                phoneNumberFormState = phoneNumberFormState.copy(phoneNumber = event.phoneNumber)
            }
            PhoneNumberFormEvent.Submit -> {
                submitPhoneNumber()
            }
        }
    }

    private fun submitPhoneNumber() {
        val phoneNumberResult = validatePhoneNumberUseCase(phoneNumberFormState.phoneNumber)

        if (!phoneNumberResult.successful) {
            phoneNumberFormState = phoneNumberFormState.copy(
                phoneNumberError = phoneNumberResult.errorMessage
            )
            return
        }
        viewModelScope.launch {
            phoneNumberValidateEventChannel.send(PhoneNumberValidationEvent.Success)
        }
    }

    suspend fun sendOtpToPhoneNumber(countryCode: String) {
        val phoneNumber = phoneNumberFormState.phoneNumber
        val authRequest =
            AuthRequest(phoneNumber = phoneNumber, otp = "", countryCode = "+$countryCode")
        sendOtpUseCase(authRequest).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _otpSendState.value = Resource.Loading()
                }
                is Resource.Error -> {
                    _otpSendState.value = Resource.Error(
                        "", OtpSendState(
                            error = result.message ?: "An unexpected error occurred"
                        )
                    )
                }
                is Resource.Success -> {
                    _otpSendState.value =
                        Resource.Success(OtpSendState(isSent = result.data?.status ?: false))
                }
            }
        }.launchIn(viewModelScope)
    }

    sealed class PhoneNumberValidationEvent {
        object Success : PhoneNumberValidationEvent()
    }
}