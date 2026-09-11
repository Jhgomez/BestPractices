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

include(":core:data-client")
include(":core:data-api-common")
include(":core:domain:common")

include(":feature:tvshow:data")
include(":feature:tvshow:domain")
include(":feature:tvshow:presentation")

includeBuild("build-logic")
 