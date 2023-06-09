package com.fifty.workersportal.di

import com.fifty.workersportal.data.remote.RestCountriesApiService
import com.fifty.workersportal.data.repository.countrycode.CountryCodeRepositoryImpl
import com.fifty.workersportal.domain.repository.CountryCodeRepository
import com.fifty.workersportal.domain.usecase.countrycode.GetCountriesUseCase
import com.fifty.workersportal.presentation.countrycode.CountryViewModel
import com.fifty.workersportal.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountryCodeModule {

    @Singleton
    @Provides
    fun provideRestCountryService(): RestCountriesApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.REST_COUNTRIES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestCountriesApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideCountryRepository(restCountriesApiService: RestCountriesApiService): CountryCodeRepository =
        CountryCodeRepositoryImpl(restCountriesApiService)

    @Singleton
    @Provides
    fun provideGetCountriesUseCase(countryRepository: CountryCodeRepository): GetCountriesUseCase =
        GetCountriesUseCase(countryRepository)


    @Provides
    fun provideCountryViewModel(getCountriesUseCase: GetCountriesUseCase) =
        CountryViewModel(getCountriesUseCase)
}