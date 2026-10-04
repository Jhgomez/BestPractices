import com.android.build.api.dsl.ApplicationExtension
import com.demo.buildlogic.APP_DIMENSIONS
import com.demo.buildlogic.APP_DIMENSIONS_WITH_FLAVORS
import com.demo.buildlogic.APP_VERSION_CODE
import com.demo.buildlogic.APP_VERSION_NAME
import com.demo.buildlogic.BASE_APPLICATION_ID
import com.demo.buildlogic.COMPILE_SDK
import com.demo.buildlogic.COMPILE_SDK_MINOR_API_LEVEL
import com.demo.buildlogic.DEFAULT_MIN_SDK
import com.demo.buildlogic.DEFAULT_RELEASE_PROGUARD_FILES
import com.demo.buildlogic.DEFAULT_TARGET_SDK
import com.demo.buildlogic.DEFAULT_TEST_INSTRUMENTATION_RUNNER
import com.demo.buildlogic._libs
import com.demo.buildlogic.configureKotlinAndAndroidSourceCodeCompilation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

interface BPApplicationPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            // this will avoid us constantly looking for the catalog since it is retrieved using
            // a backing field, e.i the extension property value is calculated everytime we
            // consume it
            val libs = _libs

            apply(plugin = libs.findPlugin("android.application").get().get().pluginId)

            // this would be the "android" DSL block
            extensions.configure<ApplicationExtension> {
                compileSdk {
                    version = release(COMPILE_SDK) {
                        minorApiLevel = COMPILE_SDK_MINOR_API_LEVEL
                    }
                }

                defaultConfig {
                    applicationId = BASE_APPLICATION_ID
                    minSdk = DEFAULT_MIN_SDK
                    targetSdk = DEFAULT_TARGET_SDK
                    versionCode = APP_VERSION_CODE
                    versionName = APP_VERSION_NAME

                    testInstrumentationRunner = DEFAULT_TEST_INSTRUMENTATION_RUNNER
                }

                buildTypes {
                    release {
                        optimization {
                            enable = false
                        }

                        isMinifyEnabled = true
                        isShrinkResources = true

                        proguardFiles(
                            getDefaultProguardFile(DEFAULT_RELEASE_PROGUARD_FILES[0]),
                            *DEFAULT_RELEASE_PROGUARD_FILES.copyOfRange(1, DEFAULT_RELEASE_PROGUARD_FILES.size)
                        )
                    }
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

                // enable localization
                androidResources {
                    generateLocaleConfig = true
                    localeFilters.add("en")
                    localeFilters.add("es")
                }

                configureKotlinAndAndroidSourceCodeCompilation()
            }

//            val components = extensions.getByType(AndroidComponentsExtension::class.java)
//
//            components.
        }
    }
}