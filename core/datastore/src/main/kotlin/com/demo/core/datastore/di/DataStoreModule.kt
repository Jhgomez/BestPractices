package com.demo.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import com.demo.core.datastore.AuthSerializer
import com.demo.core.datastore.proto.Auth
import com.demo.di.AppContext

internal interface DataStoreModule {
    fun providesAuhSerializer(): Serializer<Auth>
    fun providesAuthDataStore(serial: Serializer<Auth>, context: Context): DataStore<Auth>
}