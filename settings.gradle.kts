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
include(":core:data-client-common")
include(":core:data-model-common")
include(":core:domain:common")
include(":core:navigation")
include(":core:feature-common")

include(":feature:home:data")
include(":feature:home:domain")
include(":feature:home:presentation:impl")
include(":feature:home:presentation:api")

includeBuild("build-logic")
 