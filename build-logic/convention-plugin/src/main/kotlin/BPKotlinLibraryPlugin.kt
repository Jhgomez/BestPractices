import com.demo.buildlogic.DEFAULT_SOURCE_COMPATIBILITY
import com.demo.buildlogic.DEFAULT_TARGET_COMPATIBILITY
import com.demo.buildlogic._libs
import com.demo.buildlogic.configureKotlinAndJavaSourceCodeCompilation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

interface BPKotlinLibraryPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            // java's library core plugin that which tells gradle that this is not a
            // kotlin app module, instead is a kotlin library
            apply(plugin = "java-library")
            apply(plugin = _libs.findPlugin("etbrains-kotlin-jvm").get().get().pluginId)

            configureKotlinAndJavaSourceCodeCompilation()
        }
    }
}