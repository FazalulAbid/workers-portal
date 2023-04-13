package com.fifty.workersportal.presentation.login

import android.inputmethodservice.Keyboard
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fifty.workersportal.R
import com.fifty.workersportal.presentation.Screen
import com.fifty.workersportal.presentation.common.FullWidthRoundedButton
import com.fifty.workersportal.presentation.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    navController: NavController
) {

    val coroutineScope = rememberCoroutineScope()
    val focusManger = LocalFocusManager.current
    val bringIntoViewRequester = BringIntoViewRequester()

    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Banner image for login page.
            Image(
                painter = painterResource(
                    id = R.drawable.login_screen_banner
                ),
                contentDescription = stringResource(id = R.string.login_screen_banner),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f)
                    .padding(ScreenPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // Login screen heading text.
                    Text(
                        modifier = Modifier.widthIn(max = 340.dp),
                        text = stringResource(id = R.string.login_screen_head),
                        color = Color.Black,
                        style = MaterialTheme.typography.h6,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center,
                    )
                    // Login or Signup.
                    TextBetweenLines(
                        text = stringResource(id = R.string.login_or_signup),
                        modifier = Modifier.padding(top = 18.dp, bottom = 4.dp)
                    )
                    // Enter mobile number and country code.
                    Row() {
                        Box(
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .height(56.dp)
                                .weight(0.25f)
                                .border(1.5.dp, LightColor, RoundedCornerShape(16.dp))
                        ) {

                        }
                        Box(
                            modifier = Modifier
                                .padding(start = 4.dp)
                                .height(56.dp)
                                .weight(0.75f)
                                .border(1.5.dp, LightColor, RoundedCornerShape(16.dp))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Country code
                                Text(
                                    modifier = Modifier.padding(0.dp),
                                    text = "+91",
                                    style = MaterialTheme.typography.subtitle1,
                                    fontWeight = FontWeight.SemiBold
                                )
                                // Mobile number
                                var mobileNumberTextState by remember { mutableStateOf("") }
                                val maxChar = 10
                                TextField(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .onFocusEvent { event ->
                                            if (event.isFocused) {
                                                coroutineScope.launch {
                                                    bringIntoViewRequester.bringIntoView()
                                                }
                                            }
                                        },
                                    value = mobileNumberTextState,
                                    onValueChange = {
                                        if (it.length <= maxChar)
                                            mobileNumberTextState = it
                                    },
                                    placeholder = {
                                        Text(
                                            text = stringResource(id = R.string.enter_phone_number),
                                            style = MaterialTheme.typography.subtitle1,
                                            fontWeight = FontWeight.Normal,
                                            color = LightTextColor
                                        )
                                    },
                                    colors = TextFieldDefaults.textFieldColors(
                                        backgroundColor = Color.Transparent,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent,
                                        cursorColor = PrimaryColor
                                    ),
                                    maxLines = 1,
                                    textStyle = TextStyle(
                                        fontStyle = MaterialTheme.typography.subtitle1.fontStyle,
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = MaterialTheme.typography.subtitle1.fontSize
                                    ),
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Number,
                                        imeAction = ImeAction.Done
                                    ),
                                    keyboardActions = KeyboardActions(
                                        onDone = { focusManger.clearFocus() }
                                    )
                                )
                            }
                        }
                    }
                    // Continue button.
                    val buttonDefaults = ButtonDefaults
                    buttonDefaults.buttonColors(

                    )
                    BoxWithConstraints() {
                        FullWidthRoundedButton(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .bringIntoViewRequester(bringIntoViewRequester),
                            buttonText = stringResource(id = R.string.continue_str),
                            contentColor = Color.White,
                            backgroundColor = PrimaryColor
                        ) {
                            // Send otp to mobile number.
                            navController.navigate(Screen.PhoneOtpScreen.route) {

                            }
                        }
                    }
                    // Or.
                    TextBetweenLines(
                        text = stringResource(id = R.string.or),
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                    // Sign in with google.
                    Box(
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                            .border(1.5.dp, LightColor, RoundedCornerShape(50.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Image(
                                modifier = Modifier.height(30.dp),
                                painter = painterResource(id = R.drawable.ic_google),
                                contentDescription = stringResource(
                                    id = R.string.sign_in_with_google
                                )
                            )
                        }
                    }
                }
                // Terms and condition.
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        style = MaterialTheme.typography.caption,
                        text = stringResource(id = R.string.by_continuing_you_agree)
                    )
                    Row() {
                        TermsAndConditionButton(buttonText = stringResource(id = R.string.terms_of_service)) {

                        }
                        TermsAndConditionButton(buttonText = stringResource(id = R.string.privacy_policy)) {

                        }
                        TermsAndConditionButton(buttonText = stringResource(id = R.string.content_policy)) {

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TextBetweenLines(text: String, modifier: Modifier) {
    // Login and Signup text.
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Divider(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth(),
            color = LightColor
        )
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(SmallTextPadding),
                text = text,
                textAlign = TextAlign.Center,
                color = DarkTextColor,
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun TermsAndConditionButton(buttonText: String, onClick: () -> Unit) {
    Text(
        style = MaterialTheme.typography.caption,
        modifier = Modifier
            .clickable { onClick }
            .padding(horizontal = 4.dp),
        text = buttonText,
    )
}