package com.fifty.workersportal.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import androidx.datastore.preferences.core.Preferences
import com.fifty.workersportal.data.model.Auth
import com.fifty.workersportal.data.remote.ApiService
import com.fifty.workersportal.data.remote.AuthApiService
import com.fifty.workersportal.util.AuthAuthenticator
import com.fifty.workersportal.util.AuthInterceptor
import com.fifty.workersportal.util.Constants
import com.fifty.workersportal.util.TokenManager
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_store")

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context):
            TokenManager = TokenManager(context)

    @Singleton
    @Provides
    fun provideOkHttpClient(
        authInterceptor: AuthInterceptor,
        authAuthenticator: AuthAuthenticator
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .authenticator(authAuthenticator)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthInterceptor(tokenManager: TokenManager): AuthInterceptor =
        AuthInterceptor(tokenManager)

    @Singleton
    @Provides
    fun provideAuthAuthenticator(tokenManager: TokenManager): AuthAuthenticator =
        AuthAuthenticator(tokenManager)

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(Constants.WORKERS_PORTAL_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    @Singleton
    @Provides
    fun provideAuthApiService(retrofit: Retrofit.Builder): AuthApiService =
        retrofit.build()
            .create(AuthApiService::class.java)

    @Singleton
    @Provides
    fun provideApiService(okHttpClient: OkHttpClient, retrofit: Retrofit.Builder): ApiService =
        retrofit.client(okHttpClient)
            .build()
            .create(ApiService::class.java)
}