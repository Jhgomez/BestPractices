package com.demo.data.client

import kotlin.reflect.KType

interface AppHttpClient {
    /**
     * We use an array of Pairs since putting them in a Map is an overkill just to pass parameters
     * it would waste to many resources without any purpose, an Array is much cheaper here`
     */
    suspend fun <T> get(
        path: String,
        vararg params: Pair<String, String>,
        kType: KType
    ): T
}