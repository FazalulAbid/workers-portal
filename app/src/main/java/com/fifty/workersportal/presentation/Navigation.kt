package com.fifty.workersportal

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.fifty.workersportal.presentation.Screen
import com.fifty.workersportal.presentation.country.SelectCountryScreen
import com.fifty.workersportal.presentation.login.LoginScreen
import com.fifty.workersportal.presentation.phoneotp.PhoneOtpScreen
import com.fifty.workersportal.presentation.splash.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation() {
    // Define slide transition animations with screen width-based offsets
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    // Screen transition animations.
    val enterTransition = slideInHorizontally(
        initialOffsetX = { 300 },
        animationSpec = tween(300)
    ) + fadeIn(animationSpec = tween(300))

    val popEnterTransition = slideInHorizontally(
        initialOffsetX = { -300 },
        animationSpec = tween(300)
    ) + fadeIn(animationSpec = tween(300))

    val exitTransition = slideOutHorizontally(
        targetOffsetX = { -300 },
        animationSpec = tween(300)
    ) + fadeOut(animationSpec = tween(300))

    val popExitTransition = slideOutHorizontally(
        targetOffsetX = { 300 },
        animationSpec = tween(300)
    ) + fadeOut(animationSpec = tween(300))

    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.SelectCountryScreen.route
    ) {

        // Login Screen destination.
        composable(
            route = Screen.LoginScreen.route,
            enterTransition = { enterTransition },
            exitTransition = { exitTransition },
            popEnterTransition = { popEnterTransition },
            popExitTransition = { popExitTransition }
        ) {
            LoginScreen(navController = navController)
        }

        // Phone number OTP Screen.
        composable(
            route = Screen.PhoneOtpScreen.route,
            enterTransition = { enterTransition },
            exitTransition = { exitTransition },
            popEnterTransition = { popEnterTransition },
            popExitTransition = { popExitTransition }
        ) {
            PhoneOtpScreen(navController = navController)
        }

        // Select Country Screen.
        composable(
            route = Screen.SelectCountryScreen.route
        ) {
            SelectCountryScreen(navController = navController)
        }
    }
}