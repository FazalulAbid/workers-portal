package com.fifty.workersportal.data.repository

import android.util.Log
import com.fifty.workersportal.data.remote.RestCountriesApiService
import com.fifty.workersportal.data.remote.dto.country.CountryDto
import com.fifty.workersportal.domain.repository.CountryRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val restCountryApiService: RestCountriesApiService
) : CountryRepository {
    override suspend fun getCountries(): List<CountryDto> {
        val result = restCountryApiService.getCountries()
        return restCountryApiService.getCountries()
    }
}