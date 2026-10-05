plugins {
    alias(libs.plugins.bp.data)
}

android {
    namespace = "com.demo.data.api.home"
}

dependencies {
    implementation(project(":feature:home:domain"))
}