import com.android.build.api.dsl.CommonExtension
import com.demo.buildlogic.KOTLIN_JVM_TARGET
import com.demo.buildlogic._libs
import com.demo.buildlogic.configureKotlinAndJavaSourceCodeCompilation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

interface BPDomainPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = _libs

            apply(plugin = libs.findPlugin("jetbrains.kotlin.jvm").get().get().pluginId)

            configureKotlinAndJavaSourceCodeCompilation()

            dependencies {
                "implementation"(project(":core:domain-api"))
            }
        }
    }
}
