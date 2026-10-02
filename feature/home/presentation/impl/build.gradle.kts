plugins {
    alias(libs.plugins.bp.android.library)
    alias(libs.plugins.kotlin.compose)
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

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.viewmodel)
    implementation(libs.androidx.navigation3.runtime)

    "daggerImplementation"(libs.dagger)
    "daggerImplementation"(project(":core:di"))
    "kspDagger"(libs.dagger.compiler)
}