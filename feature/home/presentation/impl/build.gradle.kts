plugins {
    alias(libs.plugins.bp.feature.impl)
}

android {
    namespace = "com.demo.feature.home.presentation.impl"
}

dependencies {
    implementation(project(":feature:home:presentation:api"))
    implementation(project(":feature:home:domain"))
}
