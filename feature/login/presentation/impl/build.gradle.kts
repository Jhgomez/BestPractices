plugins {
    alias(libs.plugins.bp.feature.impl)
}

android {
    namespace = "com.demo.feature.login.presentation.impl"
}

dependencies {
    implementation(projects.feature.login.domain)
    implementation(projects.feature.login.presentation.api)
}