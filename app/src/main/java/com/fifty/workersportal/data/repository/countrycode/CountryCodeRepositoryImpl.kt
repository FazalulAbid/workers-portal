package com.fifty.workersportal.data.repository.countrycode

import com.fifty.workersportal.data.remote.RestCountriesApiService
import com.fifty.workersportal.data.remote.dto.countrycode.CountryDto
import com.fifty.workersportal.domain.repository.CountryCodeRepository
import javax.inject.Inject

class CountryCodeRepositoryImpl @Inject constructor(
    private val restCountryApiService: RestCountriesApiService
) : CountryCodeRepository {
    override suspend fun getCountryCodes(): List<CountryDto> {
        val result = restCountryApiService.getCountries()
        return restCountryApiService.getCountries()
    }
}