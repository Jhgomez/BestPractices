import com.android.build.api.dsl.CommonExtension
import com.demo.buildlogic._libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

interface BPComposePlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = _libs
            apply(plugin = libs.findPlugin("kotlin.compose").get().get().pluginId)

            val pluginAppliedInsideCorrectSetup =
                pluginManager.hasPlugin(libs.findPlugin("bp.android.library").get().get().pluginId) ||
                        pluginManager.hasPlugin(libs.findPlugin("bp.application").get().get().pluginId)


            if (pluginAppliedInsideCorrectSetup) {
                configure<CommonExtension> {
                    configureCompose(libs)
                }
            } else {
                throw IllegalStateException("Apply Application or Library plugin before compose plugin")
            }
        }
    }

    context(commonExtension: CommonExtension)
    fun Project.configureCompose(libs: VersionCatalog) {
        commonExtension.apply {
            dependencies {
                "implementation"(platform(libs.findLibrary("androidx.compose.bom").get()))
                "implementation"(libs.findLibrary("androidx.compose.ui").get())
                "implementation"(libs.findLibrary("androidx.compose.ui.graphics").get())
                "implementation"(libs.findLibrary("androidx.compose.ui.tooling").get())
                "implementation"(libs.findLibrary("androidx.compose.ui.tooling.preview").get())
                "implementation"(libs.findLibrary("androidx.compose.material3").get())

            }
        }
    }
}