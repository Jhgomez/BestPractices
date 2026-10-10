package com.demo.core.datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import com.demo.core.datastore.AuthSerializer
import com.demo.core.datastore.proto.Auth

internal interface DataStoreModule {
    fun providesAuhSerializer(serializer: AuthSerializer): Serializer<Auth>
    fun providesAuthDataStore(serializer: Serializer<Auth>): DataStore<Auth>
}