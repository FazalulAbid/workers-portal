package com.fifty.workersportal.presentation.login

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.fifty.workersportal.R
import com.fifty.workersportal.presentation.Screen
import com.fifty.workersportal.presentation.common.FullWidthRoundedButton
import com.fifty.workersportal.presentation.ui.theme.*
import com.fifty.workersportal.util.Constants
import com.fifty.workersportal.util.Resource
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    loginScreenViewModel: LoginScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val focusManger = LocalFocusManager.current
    val bringIntoViewRequester = BringIntoViewRequester()

    // Phone number verification state.
    val phoneNumberState = loginScreenViewModel.phoneNumberFormState

    // Country code results.
    val countryCodeResult = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>("country_code")?.observeAsState(Constants.DEFAULT_COUNTRY_CODE)

    val countryFlagResult = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>("country_flag_url")
        ?.observeAsState(Constants.DEFAULT_COUNTRY_FLAG_URL)

    val countryNameResult = navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<String>("country_name")?.observeAsState(Constants.DEFAULT_COUNTRY_NAME)

    // If form validation success
    LaunchedEffect(key1 = context) {
        loginScreenViewModel.phoneNumberValidationEvents.collect { event ->
            when (event) {
                LoginScreenViewModel.PhoneNumberValidationEvent.Success -> {
                    loginScreenViewModel.sendOtpToPhoneNumber(
                        countryCodeResult!!.value
                    )
                    Toast.makeText(context, "Validation Success", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        loginScreenViewModel.otpSendState.observeForever { result ->
            // Observe otp sent state
            when (result) {
                is Resource.Error -> {
                    Toast.makeText(context, result.data?.error, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    navController.navigate(Screen.PhoneOtpScreen.route)
                }
            }
        }
    }

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
                                .clickable(
                                    indication = null,
                                    interactionSource = remember {
                                        MutableInteractionSource()
                                    }
                                ) {
                                    navController.navigate(Screen.SelectCountryScreen.route)
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Country flag
                                Image(
                                    modifier = Modifier
                                        .width(36.dp)
                                        .height(24.dp)
                                        .clip(shape = RoundedCornerShape(4.dp)),
                                    painter = rememberImagePainter(data = countryFlagResult?.value,
                                        builder = {
                                            placeholder(R.drawable.fixitnow_logo)
                                            crossfade(true)
                                        }
                                    ),
                                    contentDescription = "Country flag",
                                    contentScale = ContentScale.Crop
                                )

                                // Down arrow
                                Icon(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .padding(0.dp),
                                    painter = painterResource(
                                        id =
                                        R.drawable.ic_round_arrow_drop_down
                                    ),
                                    contentDescription = "Country selection down arrow",
                                    tint = Color.Gray,
                                )
                            }

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
                                    text = "+${
                                        countryCodeResult?.value
                                            ?: Constants.DEFAULT_COUNTRY_CODE
                                    }",
                                    style = MaterialTheme.typography.subtitle1,
                                    fontWeight = FontWeight.SemiBold
                                )
                                // Mobile number
                                val maxChars = 10
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
                                    value = phoneNumberState.phoneNumber,
                                    onValueChange = {
                                        if (it.length <= maxChars) {
                                            loginScreenViewModel.onPhoneNumberValidationEvent(
                                                PhoneNumberFormEvent.PhoneNumberChanged(it)
                                            )
                                        }
                                    },
                                    isError = phoneNumberState.phoneNumberError != null,
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
                                        keyboardType = KeyboardType.Phone,
                                        imeAction = ImeAction.Done,
                                        autoCorrect = false
                                    ),
                                    keyboardActions = KeyboardActions(onDone = { focusManger.clearFocus() })
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
                            loginScreenViewModel.onPhoneNumberValidationEvent(PhoneNumberFormEvent.Submit)
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
                            onClick = {}, modifier = Modifier.fillMaxSize()
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

    // Show toast for errors
    if (phoneNumberState.phoneNumberError != null) {
        Toast.makeText(context, phoneNumberState.phoneNumberError, Toast.LENGTH_SHORT).show()
        phoneNumberState.phoneNumberError = null
    }
}

@Composable
fun TextBetweenLines(text: String, modifier: Modifier) {
    // Login and Signup text.
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        Divider(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth(), color = LightColor
        )
        Box(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                modifier = Modifier.padding(SmallTextPadding),
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