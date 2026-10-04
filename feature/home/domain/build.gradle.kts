import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.bp.domain)
}

dependencies {
    implementation(project(":core:domain:common"))
}
