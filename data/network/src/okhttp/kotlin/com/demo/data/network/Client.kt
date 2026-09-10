package com.demo.data.network

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File

var appContext: Context? = null

val client =  OkHttpClient.Builder()
    .cache(
        Cache(
            directory = File(appContext?.cacheDir, "http_cache"),
            // $0.05 worth of phone storage in 2020
            maxSize = 50L * 1024L * 1024L // 50 MiB
        )
    )
    .build()