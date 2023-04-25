package com.fifty.workersportal.di

import com.fifty.workersportal.data.remote.AuthApiService
import com.fifty.workersportal.data.repository.auth.AuthRepositoryImpl
import com.fifty.workersportal.domain.repository.AuthRepository
import com.fifty.workersportal.domain.usecase.auth.SendOtpUseCase
import com.fifty.workersportal.domain.usecase.auth.ValidatePhoneNumberUseCase
import com.fifty.workersportal.presentation.login.LoginScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Singleton
    @Provides
    fun provideAuthApiService(retrofit: Retrofit.Builder): AuthApiService =
        retrofit.build()
            .create(AuthApiService::class.java)

    @Singleton
    @Provides
    fun provideAuthRepository(
        authApiService: AuthApiService
    ): AuthRepository = AuthRepositoryImpl(
        authApiService = authApiService
    )

    @Provides
    fun provideValidatePhoneNumberUseCase(): ValidatePhoneNumberUseCase =
        ValidatePhoneNumberUseCase()

    @Provides
    fun provideSendOtpUseCase(authRepository: AuthRepository): SendOtpUseCase =
        SendOtpUseCase(authRepository)

    @Provides
    fun provideLoginScreenViewModel(
        validatePhoneNumberUseCase: ValidatePhoneNumberUseCase,
        sendOtpUseCase: SendOtpUseCase
    ): LoginScreenViewModel =
        LoginScreenViewModel(
            validatePhoneNumberUseCase,
            sendOtpUseCase
        )
}