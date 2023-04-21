package com.fifty.workersportal.domain.repository

import com.fifty.workersportal.domain.model.Country

interface CountryRepository {
    suspend fun getCountries():List<Country>
}