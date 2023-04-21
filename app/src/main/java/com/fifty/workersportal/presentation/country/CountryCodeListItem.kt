package com.fifty.workersportal.presentation.country

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.fifty.workersportal.domain.model.Country

@Composable
fun CountryCodeListItem(
    country: Country,
    onItemClick: () -> Unit
) {
    Row() {
        Text(text = country.name)
    }
}