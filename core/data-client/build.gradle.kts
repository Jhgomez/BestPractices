plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.secrets)
    alias(libs.plugins.ktx.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.demo.data.client"
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

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    val okhttpLoggingInterceptor = libs.okhttp.logging.interceptor.get().toString()
    // only used in build variants using Retrofit
    val okhttpLoggingInterceptorVersion = libs.versions.okhttpLoggingInterceptor.get()

    "okhttpImplementation"(platform(libs.okhttp.bom))
    "okhttpImplementation"(libs.okhttp)
    "okhttpImplementation"(okhttpLoggingInterceptor)
    "okhttpImplementation"(libs.okhttp.coroutines)

    "retrofitImplementation"(platform(libs.retrofit.bom))
    "retrofitImplementation"(libs.retrofit)
    "retrofitImplementation"(libs.retrofit.kotlinx.serialization)
    "retrofitImplementation"( "$okhttpLoggingInterceptor:$okhttpLoggingInterceptorVersion")

    implementation(libs.ktx.serialization.json)

    "daggerImplementation"(libs.dagger)
    "kspDagger"(libs.dagger.compiler)
}