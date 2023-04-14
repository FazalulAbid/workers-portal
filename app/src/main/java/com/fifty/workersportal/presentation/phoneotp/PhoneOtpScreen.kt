package com.fifty.workersportal.presentation.phoneotp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.fifty.workersportal.R
import com.fifty.workersportal.presentation.common.CommonTopBar
import com.fifty.workersportal.presentation.ui.theme.*

@Composable
fun PhoneOtpScreen(navController: NavController) {

    var otp by remember { mutableStateOf("") }

    Column(modifier = Modifier.background(MaterialTheme.colors.background)) {
        CommonTopBar(backButtonNeeded = true, stringResource(id = R.string.otp_verification)) {
            navController.navigateUp()
        }
        // Content Column
        Column(
            modifier = Modifier
                .padding(ScreenPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // We have sent otp text
            Text(
                text = stringResource(id = R.string.we_have_sent_a_verification_code_to),
                style = TextStyle(
                    fontFamily = MaterialTheme.typography.subtitle1.fontFamily,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                ),
                color = LightTextColor,
            )

            // Mobile number
            Text(
                text = "+91-9562520502",
                style = TextStyle(
                    fontFamily = MaterialTheme.typography.subtitle1.fontFamily,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                color = DarkTextColor,
            )

            // Otp pin view
            OtpTextField(
                modifier = Modifier.padding(vertical = ScreenPaddingValue), otpText = otp
            ) {
                otp = it
            }

            // Resend otp button.
            OutlinedButton(
                onClick = { },
                modifier = Modifier
                    .height(60.dp)
                    .padding(SmallTextPadding)
            ) {
                Text(
                    text = "Resend SMS in 12",
                    color = LightTextColor,
                    style = TextStyle(
                        fontFamily = MaterialTheme.typography.button.fontFamily,
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            // Try other login methods
            OutlinedButton(
                onClick = { },
                border = BorderStroke(0.dp, Color.Transparent),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Transparent),
                modifier = Modifier
                    .padding(SmallTextPadding)
            ) {
                Text(
                    text = "Try other login methods",
                    color = MaterialTheme.colors.primary,
                    style = TextStyle(
                        fontFamily = MaterialTheme.typography.button.fontFamily,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}





