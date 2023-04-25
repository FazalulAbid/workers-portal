package com.fifty.workersportal.domain.repository

import com.fifty.workersportal.data.remote.dto.countrycode.CountryDto

interface CountryCodeRepository {
    suspend fun getCountryCodes():List<CountryDto>
}