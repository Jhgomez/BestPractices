plugins {
    alias(libs.plugins.bp.android.library)
    alias(libs.plugins.bp.compose)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "com.demo.core.navigation"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)

    "daggerImplementation"(libs.androidx.compose.viewmodel)
}