plugins {
    alias(libs.plugins.bp.android.library)
    alias(libs.plugins.ktx.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.demo.data.api.home"
}

dependencies {
    implementation(project(":core:data-client-common"))
    implementation(project(":core:data-model-common"))
    implementation(project(":core:domain:common"))
    implementation(project(":feature:home:domain"))

    "okhttpImplementation"(platform(libs.okhttp.bom))
    "okhttpImplementation"(libs.okhttp)

    "daggerImplementation"(libs.dagger)
    "kspDagger"(libs.dagger.compiler)

    implementation(libs.ktx.serialization.json)
}