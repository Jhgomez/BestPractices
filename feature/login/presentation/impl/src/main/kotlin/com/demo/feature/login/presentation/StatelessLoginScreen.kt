package com.demo.feature.login.presentation

import android.util.Log
import androidx.compose.animation.core.EaseOutCirc
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imeAnimationSource
import androidx.compose.foundation.layout.imeAnimationTarget
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldLabelPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PlatformImeOptions
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demo.feature.login.presentation.impl.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun LoginScreen(
    modifier: Modifier,
    isUsernameWrong: Boolean,
    isPasswordWrong: Boolean,
    handleIntent: (LoginIntent) -> Unit
) {

    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val iconSpacingAnim by animateDpAsState(
                targetValue = if (WindowInsets.isImeVisible)
                    120.dp
                else
                    160.dp,
                animationSpec = tween(
                    durationMillis = 450,
                    easing = EaseOutCirc
                )
            )

            Spacer(modifier = Modifier.height(iconSpacingAnim))

            Image(
                colorFilter = ColorFilter.tint(color = Color.Black),
                imageVector = ImageVector.vectorResource(id = R.drawable.enable_visibility),
                contentDescription = null
            )
        }

        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val focusManager = LocalFocusManager.current

            val usernameState = rememberTextFieldState()
            val passwordState = rememberTextFieldState()
            var isPasswordVisible by rememberSaveable(null) { mutableStateOf(false) }

//        LaunchedEffect(passwordState) {
//            snapshotFlow { passwordState.text }
//                .collect { currentPassword ->
//                    handleIntent(LoginIntent.UpdatePasswordInput(currentPassword.toString()))
//                }
//        }

            val imeHeightDp = WindowInsets.ime.getBottom(LocalDensity.current) /
                    LocalDensity.current.density

            val usernameAnimation by animateDpAsState(
                targetValue = imeHeightDp.dp,
                animationSpec = tween(
                    durationMillis = 450,
                    easing = EaseOutCirc
                ),
                label = "ime_animation"
            )

            Spacer(modifier = Modifier.weight(1f))

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                modifier = Modifier.graphicsLayer {
                    translationY -= usernameAnimation.value
                },
                state = usernameState,
                isError = isUsernameWrong,
                trailingIcon = {
                    val showIcon by remember { derivedStateOf { usernameState.text.isNotEmpty() } }

                    if (showIcon) {
                        IconButton(
                            onClick = { usernameState.clearText() }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.close),
                                contentDescription = null
                            )
                        }
                    }
                },
                inputTransformation = InputTransformation {
                    handleIntent(LoginIntent.UpdateUserNameInput)
                },
                shape = MaterialTheme.shapes.large,
                label = { Text("Username")},
                placeholder = { Text("Username") },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                onKeyboardAction = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedSecureTextField(
                modifier = Modifier.graphicsLayer {
                    translationY -= usernameAnimation.value
                },
                state = passwordState,
                isError = isPasswordWrong,
                labelPosition = TextFieldLabelPosition.Attached(),
                label = { Text("My Label")},
                placeholder = { Text("My Placeholder") },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(
                                id = if (isPasswordVisible)
                                    R.drawable.disable_visibility
                                else
                                    R.drawable.enable_visibility
                            ),
                            contentDescription = null
                        )
                    }
                },
                inputTransformation = InputTransformation {
                    handleIntent(LoginIntent.UpdatePasswordInput)
                },
                textObfuscationMode = if (isPasswordVisible)
                    TextObfuscationMode.Visible
                else
                    TextObfuscationMode.RevealLastTyped,
                textObfuscationCharacter = '#',
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrectEnabled = true,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    platformImeOptions = PlatformImeOptions(
                        privateImeOptions = "Private options"
                    ),
                    showKeyboardOnFocus = true,
                    hintLocales = LocaleList(
                        Locale("en"),
                        Locale("es")
                    )
                ),
                onKeyboardAction = {
                    handleIntent(
                        LoginIntent.Login(
                            username = usernameState.text.toString(),
                            password = passwordState.text.toString()
                        )
                    )
                },
                shape = MaterialTheme.shapes.large,
                contentPadding = PaddingValues(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier.graphicsLayer {
                    translationY -= usernameAnimation.value
                },
                onClick = {
                    handleIntent(
                        LoginIntent.Login(
                            username = usernameState.text.toString(),
                            password = passwordState.text.toString()
                        )
                    )
                }
            ) {
                Text("Login")
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        modifier = Modifier.fillMaxSize(),
        handleIntent = {},
        isUsernameWrong = false,
        isPasswordWrong = false
    )
}