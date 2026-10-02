import com.demo.buildlogic._libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

interface BPAndroidLibraryPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = _libs

            apply(plugin = libs.findPlugin("android.library").get().get().pluginId)
        }
    }
}