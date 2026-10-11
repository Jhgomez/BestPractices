package com.demo.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Qualifier
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewMapModelKey(val value: KClass<out ViewModel>)

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppContext

enum class Dispatcher {
    IO,
    DEFAULT
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ContextDispatcher(val dispatcher: Dispatcher)
