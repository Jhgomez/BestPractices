package com.demo

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.view.ActionMode
import androidx.browser.auth.AuthTabIntent
import androidx.browser.customtabs.CustomTabsClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.demo.core.presentation.LocalAppViewModelFactory
import com.demo.core.presentation.LocalLoginActivityResultLauncher
import com.demo.core.presentation.R
import com.demo.ui.DemoAppScreen
import com.demo.ui.theme.DaggerRoomRetrofitPagingTestingTheme
import javax.inject.Inject

class MainActivity : ComponentActivity(), AppCompatCallback {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val vm: MainViewModel by viewModels { viewModelFactory }

    private lateinit var mDelegate: AppCompatDelegate

    private val mLauncher: ActivityResultLauncher<Intent> =
        AuthTabIntent.registerActivityResultLauncher(
            this,
            { result ->
                handleAuthResult(
                    result = result,
                    onSuccess = vm::onTokenAuthorization,
                    onFail = { message ->
                        Log.d("User Auth", "token unauthorized $message")
                        // 2DO show Snackbar
                    }
                )
            }
        )

    inline fun handleAuthResult(
        result: AuthTabIntent.AuthResult,
        onSuccess: () -> Unit,
        onFail: (String) -> Unit
    ) {
        if (result.resultCode == AuthTabIntent.RESULT_OK) {
            onSuccess()
        } else {
            onFail(
                when (result.resultCode) {
                    AuthTabIntent.RESULT_CANCELED -> "AuthTab canceled."
                    AuthTabIntent.RESULT_VERIFICATION_FAILED -> "Verification failed.";
                    AuthTabIntent.RESULT_VERIFICATION_TIMED_OUT -> "Verification timed out.";
                    else -> "Unknown error."
                }
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        mDelegate = AppCompatDelegate.create(this, this)
//        mDelegate.installViewFactory()
        mDelegate.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        (applicationContext as DemoApplication).getAppComponent().inject(this)

        setContent {
            DaggerRoomRetrofitPagingTestingTheme {
//                Column(modifier = Modifier.fillMaxSize()) {
//                    Count(
//                        count = vm.state.value.count,
//                        add = { vm.addCounter() },
//                        modifier = Modifier
//                            .fillMaxHeight(0.5f)
//                            .fillMaxWidth()
//                    )
//
//                    Greeting(
//                        name = vm.state.value.message,
//                        modifier = Modifier
//                            .fillMaxHeight(1f)
//                            .fillMaxWidth()
//                    )
//
//                }

                CompositionLocalProvider(
                    LocalAppViewModelFactory provides viewModelFactory,
                            LocalLoginActivityResultLauncher provides mLauncher
                ) {
                    DemoAppScreen(
                        modifier = Modifier.fillMaxSize(),
                        isUserAuthenticated =
                            vm.authenticationFlow.collectAsStateWithLifecycle().value
                    )
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)

        val redirectUri = getString(R.string.redirect_uri)

        Log.d("User Auth", "Auth tabs not supported, $redirectUri uri")
        Log.d("User Auth", "Auth tabs not supported, ${intent.dataString} received")

        if (intent.dataString?.contains(redirectUri) == true) {
            Log.d("User Auth", "Auth tabs not supported, intent match")
            val browser = CustomTabsClient.getPackageName(this@MainActivity, null)

            // only process these intents if we know custom tabs are not supported
            if (browser == null) {
                Log.d("User Auth", "Auth tabs not supported, intent processed")
                vm.onTokenAuthorization()
            }
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        mDelegate.onPostCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        mDelegate.onStart()
    }

    override fun onStop() {
        super.onStop()
        mDelegate.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDelegate.onDestroy()
    }

    // I probably don't need this because we are not working with Views
    override fun setContentView(layoutResID: Int) {
        mDelegate.setContentView(layoutResID)
    }

    override fun onSupportActionModeStarted(mode: ActionMode?) {
    }

    override fun onSupportActionModeFinished(mode: ActionMode?) {
    }

    override fun onWindowStartingSupportActionMode(callback: ActionMode.Callback?) = null
}

@Composable
private fun Count(count: Int, add: () -> Unit, modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val appLocales = AppCompatDelegate.getApplicationLocales()
        val currentLocaleTag = appLocales.get(0)?.toLanguageTag() ?: "en"

        Text(text = count.toString())

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            val newLanguageTag = if (currentLocaleTag == "en") "es" else "en"
            val localeList = LocaleListCompat.forLanguageTags(newLanguageTag)

            // Setting the locale re-creates the Activity by default,
            // which automatically applies the new configuration to Compose.
            AppCompatDelegate.setApplicationLocales(localeList)
        }) {
            Text(text = "chagne Local")
        }
    }
}