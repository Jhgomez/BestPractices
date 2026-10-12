plugins {
    alias(libs.plugins.bp.feature.api)
}

android {
    namespace = "com.demo.feature.login.presentation.impl"
}

dependencies {
    implementation(projects.feature.login.domain)
    implementation(projects.feature.login.presentation.api)
    implementation(projects.feature.login.domain)
}