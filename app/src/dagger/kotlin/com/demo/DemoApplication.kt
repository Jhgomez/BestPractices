package com.demo

import android.app.Application
import com.demo.di.ApplicationComponent
import com.demo.di.DaggerApplicationComponent

class DemoApplication: Application() {
    private var appComponent: ApplicationComponent? = null

    fun getAppComponent(): ApplicationComponent = if (appComponent == null)
        DaggerApplicationComponent.factory().create(applicationContext).also {
            appComponent = it
        } else appComponent!!
}