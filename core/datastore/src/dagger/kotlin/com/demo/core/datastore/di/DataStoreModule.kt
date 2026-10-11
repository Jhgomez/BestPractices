package com.demo.core.datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.demo.core.datastore.AuthSerializer
import com.demo.core.datastore.proto.Auth
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal interface DataStoreModuleImpl: DataStoreModule {

    @Provides
    override fun providesAuhSerializer(serializer: AuthSerializer): Serializer<Auth>

    @Binds
    @Singleton
    override fun providesAuthDataStore(serializer: Serializer<Auth>): DataStore<Auth> {
        dataStore(
            fileName = "auth_store.pb",
            serializer = serializer
        ) {

        }
    }
}

@Module(includes = [DataStoreModuleImpl::class])
interface PublicDataStoreModule
