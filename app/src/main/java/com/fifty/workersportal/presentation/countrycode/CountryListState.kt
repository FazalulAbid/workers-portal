package com.fifty.workersportal.presentation.countrycode

import com.fifty.workersportal.domain.model.Country

data class CountryListState(
    val isLoading: Boolean = false,
    val countries: List<Country> = emptyList(),
    val error: String = ""
)
