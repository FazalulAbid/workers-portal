package com.fifty.workersportal.presentation

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object PhoneOtpScreen : Screen("phone_otp_screen")
    object SelectCountryScreen : Screen("select_country_screen")
}