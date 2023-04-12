package com.fifty.workersportal.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fifty.workersportal.R
import com.fifty.workersportal.presentation.ui.theme.*

@Composable
fun SplashScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(screenPadding),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // For arranging space between.
            Text(text = "")
            // App icon or name.
            Text(
                text = stringResource(id = R.string.app_logo_text),
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = PrimaryColor
            )
            // Bottom app details.
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.app_tagline),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium,
                    color = DarkTextColor,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier.width(300.dp),
                    text = stringResource(id = R.string.app_description),
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    color = LightTextColor
                )
            }
        }
    }
}