package com.fifty.workersportal.presentation.country

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fifty.workersportal.R
import com.fifty.workersportal.presentation.ui.theme.ScreenPaddingValue

@Composable
fun SelectCountryScreen(
    navController: NavController,
    countryViewModel: CountryViewModel = hiltViewModel()
) {

    val searchText by countryViewModel.searchText.collectAsState()
    val countries by countryViewModel.countries.collectAsState()
    val isSearching by countryViewModel.isSearching.collectAsState()

    Surface {
        Column {
            // Top bar
            Row(
                modifier = Modifier
                    .padding(horizontal = ScreenPaddingValue)
                    .height(60.dp)
                    .fillMaxWidth()
                    .background(Color.Red),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back button
                IconButton(onClick = {

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = "Select country back button"
                    )
                }
                // Select country head text
                Text(
                    text = "Select Country",
                    style = MaterialTheme.typography.subtitle1
                )
            }

            // Search bar.
            TextField(
                value = searchText, onValueChange = countryViewModel::onSearchTextChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text(text = "Search by country name...") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                items(countries) { country ->
                    Text(
                        text = "${country.name} ${country.alpha2Code} ${country.phoneCode}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                    )
                }
            }

        }
    }
}