package com.demo.feature.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demo.core.presentation.designsystem.theme.DaggerRoomRetrofitPagingTestingTheme
import com.demo.feature.login.presentation.impl.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal inline fun LoginScreen(
    modifier: Modifier,
    crossinline handleIntent: (LoginIntent) -> Unit
) {

    Box(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(160.dp))

            Image(
                colorFilter = ColorFilter.tint(color = Color.Black),
                imageVector = ImageVector.vectorResource(id = R.drawable.enable_visibility),
                contentDescription = null,
            )
        }

        Column(
            modifier = Modifier.align(Alignment.Center).width(320.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    handleIntent(TMDBLogin())
                }
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Login with \"The Movie DB\" account",
                    textAlign = TextAlign.Center
                )
            }

            ElevatedButton(
                onClick = {
                    handleIntent(GuestLogin())
                }
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Login as guest",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    DaggerRoomRetrofitPagingTestingTheme() {
        LoginScreen(
            modifier = Modifier.fillMaxSize(),
            handleIntent = {},
        )
    }
}