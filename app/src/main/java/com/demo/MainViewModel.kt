package com.demo

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class MainState(
    val count: Int = 0,
    val message: String = "Just a message"
)

class MainViewModel @Inject constructor(): ViewModel() {
    val state = mutableStateOf(MainState())

    fun addCounter() {
        state.value = state.value.copy(count = state.value.count + 1)
    }
}