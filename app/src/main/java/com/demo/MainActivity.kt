package com.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.demo.ui.theme.DaggerRoomRetrofitPagingTestingTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val vm: MainViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        (applicationContext as DemoApplication).getAppComponent().inject(this)

        setContent {
            DaggerRoomRetrofitPagingTestingTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    Count(
                        count = vm.state.value.count,
                        add = { vm.addCounter() },
                        modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .fillMaxWidth()
                    )

                    Greeting(
                        name = vm.state.value.message,
                        modifier = Modifier
                            .fillMaxHeight(1f)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }


}

@Composable
private fun Count(count: Int, add: () -> Unit, modifier: Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = count.toString())

        Spacer(Modifier.height(8.dp))

        Button(onClick = add) {
            Text(text = "Add")
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
        Text(text = name)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DaggerRoomRetrofitPagingTestingTheme {
        Greeting("Android")
    }
}