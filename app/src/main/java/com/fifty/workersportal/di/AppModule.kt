package com.fifty.workersportal.di

import com.fifty.workersportal.data.remote.ApiService
import com.fifty.workersportal.data.remote.AuthApiService
import com.fifty.workersportal.data.remote.RestCountriesApiService
import com.fifty.workersportal.data.repository.AuthRepositoryImpl
import com.fifty.workersportal.data.repository.CountryRepositoryImpl
import com.fifty.workersportal.data.repository.UserRepositoryImpl
import com.fifty.workersportal.domain.repository.AuthRepository
import com.fifty.workersportal.domain.repository.CountryRepository
import com.fifty.workersportal.domain.repository.UserRepository
import com.fifty.workersportal.presentation.country.CountryViewModel
import com.fifty.workersportal.presentation.viewmodel.AuthViewModel
import com.fifty.workersportal.presentation.viewmodel.TokenViewModel
import com.fifty.workersportal.presentation.viewmodel.UserViewModel
import com.fifty.workersportal.util.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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

    @Provides
    fun provideCountryRepository(restCountriesApiService: RestCountriesApiService): CountryRepository =
        CountryRepositoryImpl(restCountriesApiService)

    @Provides
    fun provideCountryViewModel(countryRepository: CountryRepository) =
        CountryViewModel(countryRepository)
}