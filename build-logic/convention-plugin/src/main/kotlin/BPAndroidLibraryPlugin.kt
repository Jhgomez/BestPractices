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
import com.demo.buildlogic.configureKotlinAndAndroidSourceCodeCompilation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

interface BPAndroidLibraryPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            val libs = _libs

            apply(plugin = libs.findPlugin("android.library").get().get().pluginId)

            // this would be the "android" DSL block
            extensions.configure<LibraryExtension> {
                compileSdk {
                    version = release(COMPILE_SDK) {
                        minorApiLevel = COMPILE_SDK_MINOR_API_LEVEL
                    }
                }

                defaultConfig {
                    minSdk = DEFAULT_MIN_SDK

                    testInstrumentationRunner = DEFAULT_TEST_INSTRUMENTATION_RUNNER
                }

                buildTypes {
                    release {
                        isMinifyEnabled = true

                        proguardFiles(
                            getDefaultProguardFile(DEFAULT_RELEASE_PROGUARD_FILES[0]),
                            *DEFAULT_RELEASE_PROGUARD_FILES.copyOfRange(1, DEFAULT_RELEASE_PROGUARD_FILES.size)
                        )
                    }
                }

                compileOptions {
                    sourceCompatibility = DEFAULT_SOURCE_COMPATIBILITY
                    targetCompatibility = DEFAULT_TARGET_COMPATIBILITY
                }

                flavorDimensions += APP_DIMENSIONS

                productFlavors {
                    APP_DIMENSIONS_WITH_FLAVORS.forEach { (dimension, flavors) ->
                        flavors.forEach { flavor ->
                            register(flavor) {
                                this.dimension = dimension
                            }
                        }
                    }
                }

                configureKotlinAndAndroidSourceCodeCompilation()
            }
        }
    }
}