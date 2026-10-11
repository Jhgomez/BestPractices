package com.demo.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import com.demo.core.datastore.AuthSerializer
import com.demo.core.datastore.proto.Auth
import com.demo.di.AppContext
import com.demo.di.ContextDispatcher
import com.demo.di.Dispatcher
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
internal interface DataStoreModuleImpl: DataStoreModule {

    @Provides
    override fun providesAuhSerializer(serializer: AuthSerializer): Serializer<Auth>

    @Binds
    @Singleton
    override fun providesAuthDataStore(
        serializer: Serializer<Auth>,
        @AppContext context: Context
    ): DataStore<Auth> =
        DataStoreFactory.create(
            serializer = serializer
        ) {
            context.dataStoreFile("auth_store.pb")
        }

}

@Module(includes = [DataStoreModuleImpl::class])
interface PublicDataStoreModule
