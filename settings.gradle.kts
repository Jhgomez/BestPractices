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
include(":app")
include(":core:di")

include(":core:data-client")
include(":core:data-client-utils")
include(":core:data-model-common")
include(":core:domain:common")

include(":feature:home:data-api")
include(":feature:home:data-model")
include(":feature:home:domain")
include(":feature:home:presentation")

includeBuild("build-logic")
 