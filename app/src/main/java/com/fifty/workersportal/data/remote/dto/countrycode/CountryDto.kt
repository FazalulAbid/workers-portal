package com.fifty.workersportal.data.remote.dto.countrycode

data class CountryDto(
    val alpha2Code: String,
    val callingCodes: List<String>,
    val flags: Flags,
    val independent: Boolean,
    val name: String
)