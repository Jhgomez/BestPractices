plugins {
    alias(libs.plugins.bp.data)
}

android {
    namespace = "com.demo.login.data"
}

dependencies {
    implementation(projects.feature.login.domain)
}