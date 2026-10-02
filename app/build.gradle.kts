import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.bp.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.demo"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }

    androidResources {
        generateLocaleConfig = true
        localeFilters.add("en")
        localeFilters.add("es")
    }
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
    implementation(project(":core:data-client"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:home:data"))
    implementation(project(":feature:home:domain"))
    implementation(project(":feature:home:presentation:api"))
    implementation(project(":feature:home:presentation:impl"))

    "daggerImplementation"(project(":core:di"))
    "daggerImplementation"(libs.dagger)
    "kspDagger"(libs.dagger.compiler)

    "okhttpImplementation"(platform(libs.okhttp.bom))
    "okhttpImplementation"(libs.okhttp)

    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material3.adaptive.navigation.suite)
    implementation(libs.androidx.compose.ui.tooling.preview)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)
    implementation(libs.androidx.material3.adaptive.navigation3)


    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}