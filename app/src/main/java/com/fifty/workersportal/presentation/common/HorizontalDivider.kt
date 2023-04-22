package com.fifty.workersportal.presentation.common

import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalDivider(
    color: Color
) {
    Divider(
        modifier = Modifier.height(1.dp),
        color = color
    )
}