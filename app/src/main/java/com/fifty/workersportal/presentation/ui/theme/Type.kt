package com.fifty.workersportal.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.fifty.workersportal.R

val appFontFamily = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_regular, FontWeight.SemiBold),
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_bold, FontWeight.ExtraBold),
    Font(R.font.roboto_black, FontWeight.Black),
)

// Set of Material typography styles to start with
val Typography = Typography(
    // Bodies
    body1 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 96.sp
    ),

    // Headings
    h1 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 96.sp
    ),
    h2 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 60.sp
    ),
    h3 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 48.sp
    ),
    h4 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 34.sp
    ),
    h5 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 20.sp
    ),

    // Subtitles
    subtitle1 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 14.sp
    ),

    button = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 10.sp
    ),

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)