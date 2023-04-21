package com.fifty.workersportal.data.remote

import com.fifty.workersportal.domain.model.Country
import retrofit2.http.GET

interface RestCountriesApiService {

    @GET("all?fields=name,alpha2Code,callingCodes,flags")
    suspend fun getCountries(): List<Country>
}