package com.demo.buildlogic

// I prefer to declare it here instead of the version catalog as if I declare it on the version
// catalog and for any reason these values change then the entire project(e.i all subprojects)
// will run all Gradle phases again, while if I do it here in theory it should only invalidate
// the modules that consume the convention plugin that is consuming the value that has changed,
// and since these are just android configurations it means that all java and kotlin only
// modules should be able to use the gradle cache

val APP_FLAVORS = listOf("httpclient", "di")
val COMPILE_SDK = 37
val COMPILE_SDK_MINOR_API_LEVEL ="1"

val DEFAULT_MIN_SDK = 28
val DEFAULT_TARGET_SDK = 37

val DEFAULT_TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

// Below two are not common to all android modules(app and libraries) but I declare it here for
// readability purposes, so we have full view of the configurations across all the app
val APP_VERSION_CODE = 1
val APP_VERSION_NAME = "1.0"

