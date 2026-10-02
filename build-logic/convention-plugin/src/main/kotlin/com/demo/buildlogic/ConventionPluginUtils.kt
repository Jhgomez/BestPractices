package com.demo.buildlogic

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

private var _libs: VersionCatalog? = null

val Project.libs: VersionCatalog
    get() = _libs ?:
        extensions.getByType(VersionCatalogsExtension::class).named("libs").also { catalog ->
            _libs = catalog
        }