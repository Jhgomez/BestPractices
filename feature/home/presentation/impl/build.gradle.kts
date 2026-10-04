plugins {
    alias(libs.plugins.bp.android.library)
    alias(libs.plugins.bp.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.demo.feature.home.presentation.impl"
}

dependencies {
    implementation(project(":core:domain:common"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:home:presentation:api"))
    implementation(project(":feature:home:domain"))

    implementation(libs.androidx.compose.viewmodel)

    implementation(libs.androidx.navigation3.runtime)

    "daggerImplementation"(project(":core:di"))
    "daggerImplementation"(libs.dagger)
    "kspDagger"(libs.dagger.compiler)
}