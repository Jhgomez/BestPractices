plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ktx.serialization)
}

android {
    namespace = "com.demo.data.api.tvshow"
    compileSdk {
        version = release(37) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
//            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    flavorDimensions += "di"

    productFlavors {
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
    implementation(libs.ktx.serialization.json)
}