import com.android.build.api.dsl.CommonExtension
import com.demo.buildlogic._libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

interface BPDependencyInjectionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = _libs
            apply(plugin = libs.findPlugin("ksp").get().get().pluginId)

            val pluginAppliedInsideCorrectSetup =
                pluginManager.hasPlugin(libs.findPlugin("bp.android.library").get().get().pluginId) ||
                        pluginManager.hasPlugin(libs.findPlugin("bp.application").get().get().pluginId)

            if (pluginAppliedInsideCorrectSetup) {
                configure<CommonExtension> {
                    dependencies {
                        "daggerImplementation"(project(":core:di"))
                        "daggerImplementation"(libs.findLibrary("dagger"))
                        "kspDagger"(libs.findLibrary("dagger.compiler"))
                    }
                }
            } else {
                throw IllegalStateException("Apply Application or Library plugin before DI plugin")
            }
        }
    }
}