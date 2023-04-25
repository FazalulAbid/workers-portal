package com.fifty.workersportal.domain.usecase.countrycode

import com.fifty.workersportal.domain.model.Country
import com.fifty.workersportal.domain.model.toCountry
import com.fifty.workersportal.domain.repository.CountryCodeRepository
import com.fifty.workersportal.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val countryRepository: CountryCodeRepository
) {
    operator fun invoke(): Flow<Resource<List<Country>>> = flow {
        try {
            emit(Resource.Loading())
            val counties = countryRepository.getCountryCodes().map { it.toCountry() }
            emit(Resource.Success(data = counties))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}