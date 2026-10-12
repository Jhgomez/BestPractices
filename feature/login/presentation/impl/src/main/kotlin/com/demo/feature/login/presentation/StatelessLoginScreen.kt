package com.demo.feature.login.presentation

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.then
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldLabelPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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

@Composable
internal fun LoginScreen(
    modifier: Modifier,
    onLogin: (username: String, password: String) -> Unit,
    didLoginFail: Boolean
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var username by rememberSaveable(null) { mutableStateOf("") }
        val password = rememberTextFieldState()
        var isPasswordVisible by rememberSaveable(null) { mutableStateOf(false) }
        
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            isError = didLoginFail,
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedSecureTextField(
            state = password,
            modifier = Modifier,
            isError = didLoginFail,
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
            textObfuscationMode = TextObfuscationMode.RevealLastTyped,
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
                onLogin(username, password.text.toString())
            },
            shape = MaterialTheme.shapes.large,
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
        )

        Button(
            modifier = Modifier.wrapContentSize(align = Alignment.BottomCenter),
            onClick = {
                onLogin(username, password.text.toString())
            }
        ) {
            Text("Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    LoginScreen(
        modifier = Modifier.fillMaxSize(),
        onLogin = { _, _ -> },
        didLoginFail = false
    )
}