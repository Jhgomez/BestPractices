package com.demo.core.datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import com.demo.core.datastore.AuthSerializer
import com.demo.core.datastore.proto.Auth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal interface DataStoreModuleImpl: DataStoreModule {

    @Provides
    override fun providesAuhSerializer(serializer: AuthSerializer): Serializer<Auth>

    @Provides
    @Singleton
    override fun providesAuthDataStore(serializer: Serializer<Auth>): DataStore<Auth>
}

@Module(includes = [DataStoreModuleImpl::class])
interface PublicDataStoreModule
