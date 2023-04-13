package com.fifty.workersportal.presentation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object PhoneOtpScreen : Screen("phone_otp_screen")
}