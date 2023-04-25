package com.fifty.workersportal.presentation.countrycode

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fifty.workersportal.R
import com.fifty.workersportal.presentation.common.CustomSearchTextField
import com.fifty.workersportal.presentation.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SelectCountryScreen(
    navController: NavController,
    countryViewModel: CountryViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val searchText by countryViewModel.searchText.collectAsState()
    val countries by countryViewModel.countries.collectAsState()
    val countryListState = countryViewModel.countryListState.value

    Surface {
        Box {
            Column {
                // Top bar
                Row(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Back button
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_round_arrow_back),
                            contentDescription = "Select country back button"
                        )
                    }
                    // Select country head text
                    Text(
                        text = "Select Country",
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Medium
                    )
                }

                Column() {
                    // Search bar.
                    Box(
                        modifier = Modifier.padding(
                            horizontal = ScreenPaddingValue,
                            vertical = 10.dp
                        )
                    ) {
                        CustomSearchTextField(
                            value = searchText,
                            onValueChange = countryViewModel::onSearchTextChange,
                            modifier = Modifier
                                .height(42.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_round_search),
                                    contentDescription = "Search Icon",
                                    tint = DarkTextColor
                                )
                            },
                            placeholderText = stringResource(id = R.string.search_by_country_name),
                            backgroundColor = VeryLightColor
                        )
                    }

                    // Countries list.
                    CompositionLocalProvider(
                        LocalOverscrollConfiguration provides null
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),

                            ) {
                            items(countries) { country ->
                                CountryCodeListItem(country = country) {
                                    navController.previousBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("country_code", country.callingCode)
                                    navController.previousBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("country_flag_url", country.flagUrl)
                                    navController.previousBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("country_name", country.name)
                                    navController.popBackStack()
                                }
                            }
                        }
                    }
                }
            }

            // Circular progress indicator.
            if (countryListState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}