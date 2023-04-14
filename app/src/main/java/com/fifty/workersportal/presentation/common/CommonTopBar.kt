package com.fifty.workersportal.presentation.common

import android.accessibilityservice.AccessibilityService.ScreenshotResult
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fifty.workersportal.R
import com.fifty.workersportal.presentation.ui.theme.*

@Composable
fun CommonTopBar(
    backButtonNeeded: Boolean,
    appBarText: String,
    onBackPressed: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(TopBarHeight)
            .padding(horizontal = ScreenPaddingValue),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back button
        if (backButtonNeeded) {
            Box(modifier = Modifier.padding(end = ScreenPaddingValue / 2)) {
                IconButton(
                    onClick = onBackPressed,
                    modifier = Modifier
                        .then(Modifier.size(40.dp))
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = stringResource(
                            id = R.string.back_button
                        ),
                        tint = PrimaryColor
                    )
                }
            }
        }

        // App bar text
        Text(
            text = appBarText,
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Medium
        )
    }
}