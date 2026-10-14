import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.bp.application)
    alias(libs.plugins.bp.compose)
    alias(libs.plugins.bp.di)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "com.demo"
}

tasks.withType<KotlinCompilationTask<*>>().configureEach {
    if (name.contains("dagger", ignoreCase = true)) {
        compilerOptions {
            // https://dagger.dev/dev-guide/compiler-options
            // Adagger.fullBindingGraphValidation=ERROR
            freeCompilerArgs.add("-Adagger.fullBindingGraphValidation=WARNING")
        }
    }
}

dependencies {
    implementation(projects.core.dataClientImpl)
    implementation(projects.core.navigation)
    implementation(projects.core.datastore)
    implementation(projects.core.datastoreProto)

    implementation(projects.feature.home.data)
    implementation(projects.feature.home.domain)
    implementation(projects.feature.home.presentation.api)
    implementation(projects.feature.home.presentation.impl)

    implementation(projects.feature.login.data)
    implementation(projects.feature.login.domain)
    implementation(projects.feature.login.presentation.api)
    implementation(projects.feature.login.presentation.impl)

    "okhttpImplementation"(platform(libs.okhttp.bom))
    "okhttpImplementation"(libs.okhttp)

    implementation(libs.androidx.appcompat)

    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.material3.adaptive.navigation3)
    implementation(libs.androidx.datastore)

    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}