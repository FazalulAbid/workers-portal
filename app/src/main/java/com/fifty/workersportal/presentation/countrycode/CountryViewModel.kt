package com.fifty.workersportal.presentation.countrycode

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fifty.workersportal.domain.model.Country
import com.fifty.workersportal.domain.usecase.countrycode.GetCountriesUseCase
import com.fifty.workersportal.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel() {

    private val _countryListState = mutableStateOf(CountryListState())
    val countryListState: State<CountryListState> = _countryListState

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _countries = MutableStateFlow(listOf<Country>())

    @OptIn(FlowPreview::class)
    val countries = searchText
        .debounce(100L)
        .combine(_countries) { text, countries ->
            if (text.isBlank()) {
                countries
            } else {
                countries.filter {
                    it.doesMatchSearchQuery(text)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _countries.value
        )

    init {
        getCountries()
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private fun getCountries() {
        getCountriesUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _countryListState.value = CountryListState(isLoading = true)
                }
                is Resource.Error -> {
                    _countryListState.value = CountryListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Success -> {
                    _countryListState.value =
                        CountryListState(countries = result.data ?: emptyList())
                    _countries.value = _countryListState.value.countries
                }
            }
        }.launchIn(viewModelScope)
    }
}