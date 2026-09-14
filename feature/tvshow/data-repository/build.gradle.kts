plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.demo.data.api.tvshow.repository"
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
    implementation(project(":core:data-model-common"))
    implementation(project(":feature:tvshow:data-model"))
    implementation(project(":feature:tvshow:data-api"))
    implementation(project(":feature:tvshow:domain"))
    implementation(project(":core:domain:common"))
    implementation(libs.ktx.serialization.json)

    "daggerImplementation"(libs.dagger)
    "kspDagger"(libs.dagger.compiler)
}