package com.fifty.workersportal.presentation.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fifty.workersportal.domain.model.Country
import com.fifty.workersportal.domain.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val allCountries = listOf<Country>(
        Country("Abid", "", "", ""),
        Country("Fazal", "", "", ""),
        Country("Fasil", "", "", ""),
        Country("Nithin", "", "", ""),
        Country("Jobin", "", "", ""),
        Country("Aboobacker", "", "", ""),
    )

    private val _countries = MutableStateFlow(allCountries)
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

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun getCountries() {
        viewModelScope.launch {
            _countries.value = countryRepository.getCountries()
        }
    }
}