package com.fifty.workersportal.di

import android.content.Context
import com.fifty.workersportal.data.remote.ApiService
import com.fifty.workersportal.data.remote.AuthApiService
import com.fifty.workersportal.domain.usecase.auth.ValidatePhoneNumberUseCase
import com.fifty.workersportal.presentation.login.LoginScreenViewModel
import com.fifty.workersportal.util.Constants
import com.fifty.workersportal.util.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context):
            TokenManager = TokenManager(context)

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(Constants.WORKERS_PORTAL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    @Singleton
    @Provides
    fun provideApiService(okHttpClient: OkHttpClient, retrofit: Retrofit.Builder): ApiService =
        retrofit.client(okHttpClient)
            .build()
            .create(ApiService::class.java)

}