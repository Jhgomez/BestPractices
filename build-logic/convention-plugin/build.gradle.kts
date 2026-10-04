// learn how to extend your build with custom gradle plugins here
// https://developer.android.com/build/extend-agp
// This gradle plugins/Introduction to plugins documentation is also helpful
// https://docs.gradle.org/current/userguide/plugins.html
plugins {
    `kotlin-dsl`
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xcontext-parameters")
    }
}

gradlePlugin {
    plugins {
        register("BPApplicationPlugin") {
            id = libs.plugins.bp.application.get().pluginId
            implementationClass = "BPApplicationPlugin"
        }

        register("BPAndroidLibraryPlugin") {
            id = libs.plugins.bp.android.library.get().pluginId
            implementationClass = "BPAndroidLibraryPlugin"
        }

        register("BPComposePlugin") {
            id = libs.plugins.bp.compose.get().pluginId
            implementationClass = "BPComposePlugin"
        }

        register("BPDependencyInjectionPlugin") {
            id = libs.plugins.bp.di.get().pluginId
            implementationClass = "BPDependencyInjectionPlugin"
        }

        register("BPFeatureImplPlugin") {
            id = libs.plugins.bp.feature.impl.get().pluginId
            implementationClass = "BPFeatureImplPlugin"
        }

        register("BPFeatureApiPlugin") {
            id = libs.plugins.bp.feature.api.get().pluginId
            implementationClass = "BPFeatureApiPlugin"
        }
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin.api)
    compileOnly(libs.kotlin.android.plugin)
}

////////////////////////////////////////////
//configurations.create("") {
//    this.dependencies.add()
//}
//
//pluginManager.apply("somePlugin")
//
//// this only applied configs if the plugin exists
//pluginManager.withPlugin("java") {
//    val sourceSets =
//        project.extensions.getByType(SourceSetContainer::class.java)
//    val main = sourceSets.getByName(SourceSet.MAIN_SOURCE_SET_NAME)
//    main.java.setSrcDirs(listOf("src"))
//}


/////////////////////////////////////////////