import com.android.build.api.dsl.LibraryExtension
import com.demo.buildlogic.APP_DIMENSIONS
import com.demo.buildlogic.APP_DIMENSIONS_WITH_FLAVORS
import com.demo.buildlogic.COMPILE_SDK
import com.demo.buildlogic.COMPILE_SDK_MINOR_API_LEVEL
import com.demo.buildlogic.DEFAULT_MIN_SDK
import com.demo.buildlogic.DEFAULT_RELEASE_PROGUARD_FILES
import com.demo.buildlogic.DEFAULT_SOURCE_COMPATIBILITY
import com.demo.buildlogic.DEFAULT_TARGET_COMPATIBILITY
import com.demo.buildlogic.DEFAULT_TEST_INSTRUMENTATION_RUNNER
import com.demo.buildlogic._libs
import com.demo.buildlogic.configureAndroidKotlinAndJavaSourceCodeCompilation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

interface BPDataPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = _libs

            apply(plugin = libs.findPlugin("bp.android.library").get().get().pluginId)
            apply(plugin = libs.findPlugin("ktx.serialization").get().get().pluginId)
            apply(plugin = libs.findPlugin("ksp").get().get().pluginId)

            dependencies {
                "implementation"(project(":core:data-client-common"))
                "implementation"(project(":core:data-model-common"))
                "implementation"(project(":core:domain:common"))

                "okhttpImplementation"(platform(libs.findLibrary("okhttp.bom").get()))
                "okhttpImplementation"(libs.findLibrary("okhttp").get())

                "daggerImplementation"(libs.findLibrary("dagger").get())
                "kspDagger"(libs.findLibrary("dagger.compiler").get())

                "implementation"(libs.findLibrary("ktx.serialization.json").get())
            }
        }
    }
}