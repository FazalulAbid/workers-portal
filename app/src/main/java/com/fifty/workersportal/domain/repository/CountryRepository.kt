package com.fifty.workersportal.domain.repository

import com.fifty.workersportal.data.remote.dto.country.CountryDto
import com.fifty.workersportal.domain.model.Country

interface CountryRepository {
    suspend fun getCountries():List<CountryDto>
}