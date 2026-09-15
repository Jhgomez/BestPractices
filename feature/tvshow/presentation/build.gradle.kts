plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.demo.feature.tvshow.presentation"

    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 28
    }

    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }

    buildFeatures {
        compose = true
    }

    flavorDimensions += listOf("httpclient", "di")

    productFlavors {
        create("retrofit") {
            dimension = "httpclient"
        }

        create("okhttp") {
            dimension = "httpclient"
        }

        create("dagger") {
            dimension = "di"
        }

        create("hilt") {
            dimension = "di"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(project(":core:domain:common"))
    implementation(project(":core:di"))
    implementation(project(":feature:tvshow:domain"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.viewmodel)

    "daggerImplementation"(libs.dagger)
    "kspDagger"(libs.dagger.compiler)
}