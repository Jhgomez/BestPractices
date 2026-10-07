plugins {
    alias(libs.plugins.bp.android.library)
    alias(libs.plugins.secrets)
    alias(libs.plugins.ktx.serialization)
}

android {
    namespace = "com.demo.data.client.api"

    defaultConfig {
        buildConfigField(
            "String",
            "BASE_URL",
            "\"https://api.themoviedb.org/3\""
        )
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.coroutines)

    implementation(libs.ktx.serialization.json)
}