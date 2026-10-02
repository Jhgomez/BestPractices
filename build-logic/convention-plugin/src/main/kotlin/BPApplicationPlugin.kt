import com.demo.buildlogic._libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

interface BPApplicationPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            // this will avoid us constantly looking for the catalog since it is retrieved using
            // a backing field, e.i the extension property value is calculated everytime we
            // consume it
            val libs = _libs

            apply(plugin = libs.findPlugin("android.application").get().get().pluginId)
        }
    }
}