plugins {
    alias(libs.plugins.bp.feature.impl)
}

android {
    namespace = "com.demo.feature.home.presentation.impl"
}

dependencies {
    implementation(projects.feature.home.presentation.api)
    implementation(projects.feature.home.domain)
}
