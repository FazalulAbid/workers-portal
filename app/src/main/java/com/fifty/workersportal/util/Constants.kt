package com.fifty.workersportal.util

import com.fifty.workersportal.data.model.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeoutOrNull
import retrofit2.Response

object Constants {
    const val SPLASH_SCREEN_DURATION = 7000L
    const val WORKERS_PORTAL_BASE_URL = "https://fazilnbr.online/"
    const val REST_COUNTRIES_BASE_URL = "https://restcountries.com/v2/"

    // Country code default values.
    const val DEFAULT_COUNTRY_NAME = "India"
    const val DEFAULT_COUNTRY_CODE = "91"
    const val DEFAULT_COUNTRY_FLAG_URL = "https://flagcdn.com/w320/in.png"

}