// learn how to extend your build with custom gradle plugins here
// https://developer.android.com/build/extend-agp
plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradle.plugin.api)
}