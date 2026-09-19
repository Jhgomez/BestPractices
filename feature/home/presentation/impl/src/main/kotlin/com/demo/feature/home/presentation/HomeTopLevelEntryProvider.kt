package com.demo.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.EntryProviderScope
import com.demo.core.navigation.AppNavKey
import com.demo.feature.home.presentation.api.navigation.HomeTopLevel

fun EntryProviderScope<AppNavKey>.addHomeTopLevel() {
    entry<HomeTopLevel> {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeTopLevelScreen(modifier = Modifier.fillMaxSize())
        }
    }
}