package com.demo

import android.app.Application
import com.demo.di.ApplicationComponent
import com.demo.di.DaggerApplicationComponent

class DemoApplication: Application() {
    var appComponent: ApplicationComponent? = null

    fun getAppComponent(): ApplicationComponent = if (appComponent == null)
        DaggerApplicationComponent.create().also {
            appComponent = it
        } else appComponent!!
}