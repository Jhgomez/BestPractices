plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.demo.core.navigation"

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
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    "daggerImplementation"(libs.androidx.compose.viewmodel)
}