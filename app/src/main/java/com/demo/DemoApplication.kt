package com.demo

import android.app.Application
import di.ApplicationComponent
import di.DaggerApplicationComponent

class DemoApplication: Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}