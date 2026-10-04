import com.android.build.api.dsl.CommonExtension
import com.demo.buildlogic._libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

interface BPFeatureApiPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = _libs

            apply(plugin = libs.findPlugin("bp.android.library").get().get().pluginId)
            apply(plugin = libs.findPlugin("bp.compose").get().get().pluginId)

            dependencies {
                "implementation"(project(":core:navigation"))
            }
        }
    }
}