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
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin.api)
    compileOnly(libs.android.kotlin.plugin)
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