package com.demo.buildlogic

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project._libs: VersionCatalog
    get() = extensions.getByType(VersionCatalogsExtension::class).named("libs")