package com.fifty.workersportal.di

import com.fifty.workersportal.data.remote.ApiService
import com.fifty.workersportal.data.remote.AuthApiService
import com.fifty.workersportal.data.repository.AuthRepositoryImpl
import com.fifty.workersportal.data.repository.UserRepositoryImpl
import com.fifty.workersportal.domain.repository.AuthRepository
import com.fifty.workersportal.domain.repository.UserRepository
import com.fifty.workersportal.presentation.viewmodel.AuthViewModel
import com.fifty.workersportal.presentation.viewmodel.TokenViewModel
import com.fifty.workersportal.presentation.viewmodel.UserViewModel
import com.fifty.workersportal.util.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object AuthModule {
    @Provides
    fun provideAuthRepository(authApiService: AuthApiService): AuthRepository =
        AuthRepositoryImpl(authApiService)

    @Provides
    fun provideUserRepository(apiService: ApiService): UserRepository =
        UserRepositoryImpl(apiService)

    @Provides
    fun provideAuthViewModel(authRepository: AuthRepository): AuthViewModel =
        AuthViewModel(authRepository)

    @Provides
    fun provideTokenViewModel(tokenManager: TokenManager): TokenViewModel =
        TokenViewModel(tokenManager)

    @Provides
    fun provideUserViewModel(userRepository: UserRepository): UserViewModel =
        UserViewModel(userRepository)
}