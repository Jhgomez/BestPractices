pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BestPractices"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

includeBuild("build-logic")

include(":app")
include(":core:datastore")
include(":core:datastore-proto")
include(":core:di")
include(":core:data-client-impl")
include(":core:data-client-api")
include(":core:data-model-common")
include(":core:domain-api")
include(":core:navigation")

include(":feature:home:data")
include(":feature:home:domain")
include(":feature:home:presentation:impl")
include(":feature:home:presentation:api")

include(":feature:login:data")
include(":feature:login:domain")
include(":feature:login:presentation:impl")
include(":feature:login:presentation:api")
