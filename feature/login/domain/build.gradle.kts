import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.bp.domain)
}

dependencies {
    implementation(projects.core.domain.common)
}
