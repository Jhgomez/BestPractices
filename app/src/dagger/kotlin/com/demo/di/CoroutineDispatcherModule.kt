package com.demo.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
object CoroutineDispatcherModule {

    @Provides
    @ContextDispatcher(Dispatcher.IO)
    fun providesIoDispatcher() = Dispatchers.IO

    @Provides
    @ContextDispatcher(Dispatcher.DEFAULT)
    fun providesDefaultDispatcher() = Dispatchers.IO
}