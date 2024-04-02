plugins {
    `kotlin-dsl`
    `maven-publish`
}


group = "com.michiganlabs.hoverboard.convention-plugins"
version = "1.0"

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.com.android.gradle.plugin)
    implementation(libs.org.jetbrains.kotlin.android.gradle.plugin)
}
