import com.android.build.api.dsl.CommonExtension
import com.demo.buildlogic._libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

interface BPFeatureImplPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = _libs

            apply(plugin = libs.findPlugin("bp.android.library").get().get().pluginId)
            apply(plugin = libs.findPlugin("bp.compose").get().get().pluginId)
            apply(plugin = libs.findPlugin("bp.di").get().get().pluginId)

            configure<CommonExtension> {
                dependencies {
                    "implementation"(project(":core:domain:common"))
                    "implementation"(project(":core:navigation"))

                    "implementation"(libs.findLibrary("androidx.compose.viewmodel").get())
                    "implementation"(libs.findLibrary("androidx.navigation3.runtime").get())
                }
            }
        }
    }
}