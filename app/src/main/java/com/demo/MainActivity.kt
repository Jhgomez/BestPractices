package com.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.view.ActionMode
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.core.feature.common.AppViewModelFactory
import com.demo.ui.DemoAppScreen
import com.demo.ui.theme.DaggerRoomRetrofitPagingTestingTheme
import javax.inject.Inject

class MainActivity : ComponentActivity(), AppCompatCallback {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val vm: MainViewModel by viewModels { viewModelFactory }

    private lateinit var mDelegate: AppCompatDelegate

    override fun onCreate(savedInstanceState: Bundle?) {
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

                CompositionLocalProvider(AppViewModelFactory provides viewModelFactory) {
                    DemoAppScreen(modifier = Modifier.fillMaxSize())
                }
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.profile))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DaggerRoomRetrofitPagingTestingTheme {
        Greeting("Android")
    }
}