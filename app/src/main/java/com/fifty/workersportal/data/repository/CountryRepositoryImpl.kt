package com.fifty.workersportal.data.repository

import com.fifty.workersportal.domain.model.Country
import com.fifty.workersportal.data.remote.RestCountriesApiService
import com.fifty.workersportal.domain.repository.CountryRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val restCountryApiService: RestCountriesApiService
) : CountryRepository {

    override suspend fun getCountries(): List<Country> {
        return restCountryApiService.getCountries()
    }
}