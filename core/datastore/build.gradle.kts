plugins {
    alias(libs.plugins.bp.android.library)
    alias(libs.plugins.bp.di)
}

android {
    namespace = "com.demo.core.datastore"
}

dependencies {
    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.kotlin.lite)

    implementation(projects.core.datastoreProto)
}