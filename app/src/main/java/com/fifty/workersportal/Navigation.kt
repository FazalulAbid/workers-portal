package com.fifty.workersportal

import android.accessibilityservice.AccessibilityService.ScreenshotResult
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import com.fifty.workersportal.presentation.Screen
import com.fifty.workersportal.presentation.login.LoginScreen
import com.fifty.workersportal.presentation.phoneotp.PhoneOtpScreen
import com.fifty.workersportal.presentation.splash.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {

        // Splash screen destination.
        composable(
            route = Screen.SplashScreen.route,
        ) {
            SplashScreen(navController = navController)
        }

        // Login Screen destination.
        composable(
            route = Screen.LoginScreen.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 700 }
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -700 }
                )
            }
        ) {
            LoginScreen(navController = navController)
        }

        // Phone number OTP Screen.
        composable(
            route = Screen.PhoneOtpScreen.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 700 }
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -700 }
                )
            }
        ) {
            PhoneOtpScreen(navController = navController)
        }
    }
}

//enterTransition = {
//    when (initialState.destination.route) {
//        Screen.SplashScreen.route ->
//            slideInHorizontally(
//                initialOffsetX = { 700 },
//                animationSpec = tween(700)
//            )
//        else -> null
//    }
//},