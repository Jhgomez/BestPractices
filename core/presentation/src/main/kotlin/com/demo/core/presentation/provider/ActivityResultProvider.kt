package com.demo.core.presentation

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.staticCompositionLocalOf

val LocalLoginActivityResultLauncher =
    staticCompositionLocalOf<ActivityResultLauncher<Intent>> {
        throw IllegalStateException("Pass value to LocalLoginActivityResultLauncher before consuming it")
    }