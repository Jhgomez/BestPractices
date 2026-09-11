plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.secrets)
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

        buildConfigField(
            "String",
            "BASE_URL",
            "\"https://api.themoviedb.org/3\""
        )
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

    "okhttpApi"(platform(libs.okhttp.bom))
    "okhttpApi"(libs.okhttp)
    "okhttpImplementation"(okhttpLoggingInterceptor)
    "okhttpImplementation"(libs.okhttp.coroutines)

    "retrofitApi"(libs.retrofit)
    "retrofitImplementation"( "$okhttpLoggingInterceptor:$okhttpLoggingInterceptorVersion")

    implementation(libs.ktx.serialization.json)
}