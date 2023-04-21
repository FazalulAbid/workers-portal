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

}