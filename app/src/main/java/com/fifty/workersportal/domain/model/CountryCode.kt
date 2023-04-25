package com.fifty.workersportal.domain.model

import com.fifty.workersportal.data.remote.dto.countrycode.CountryDto

data class Country(
    val alpha2Code: String,
    val callingCode: String,
    val flagUrl: String,
    val name: String
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$name$alpha2Code",
            alpha2Code,
            callingCode
        )
        return matchingCombinations.any {
            it.contains(query, ignoreCase = true)
        }
    }
}

fun CountryDto.toCountry(): Country {
    return Country(
        name = name,
        alpha2Code = alpha2Code,
        callingCode = callingCodes.first(),
        flagUrl = flags.png ?: flags.svg
    )
}