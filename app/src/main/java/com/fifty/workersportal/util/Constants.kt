package com.fifty.workersportal.util

import com.fifty.workersportal.data.model.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeoutOrNull
import retrofit2.Response

fun <T> apiRequestFlow(call: suspend () -> Response<T>): Flow<ApiResponse<T>> = flow {
    emit(ApiResponse.Loading)

    withTimeoutOrNull(20000L) {
        val response = call()

        try {
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    emit(ApiResponse.Success(data))
                }
            } else {
                response.errorBody()?.let { error ->
                    error.close()
                    val parsedError: ErrorResponse =
                        Gson().fromJson(error.charStream(), ErrorResponse::class.java)
                    emit(ApiResponse.Failure(parsedError.message, parsedError.code))
                }
            }
        } catch (e: Exception) {
            emit(ApiResponse.Failure(e.message ?: e.toString(), 400))
        }
    } ?: emit(ApiResponse.Failure("Timeout! Please try again.", 408))
}.flowOn(Dispatchers.IO)

object Constants {
    const val SPLASH_SCREEN_DURATION = 7000L
    const val WORKERS_PORTAL_BASE_URL = "https://fazilnbr.online/"
    const val REST_COUNTRIES_BASE_URL = "https://restcountries.com/v2/"

    // Country code default values.
    const val DEFAULT_COUNTRY_NAME = "India"
    const val DEFAULT_COUNTRY_CODE = "91"
    const val DEFAULT_COUNTRY_FLAG_URL = "https://flagcdn.com/w320/in.png"

}