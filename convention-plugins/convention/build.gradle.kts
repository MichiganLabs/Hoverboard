plugins {
    `kotlin-dsl`
    `maven-publish`
}


group = "com.michiganlabs.hoverboard.convention-plugins"
version = "1.0"

publishing {
    repositories {
        maven {
            // change to point to your repo, e.g. http://my.org/repo
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}

tasks.publish {
    dependsOn("check")
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.com.android.gradle.plugin)
    implementation(libs.org.jetbrains.kotlin.android.gradle.plugin)
//    implementation(libs.com.google.devtools.ksp.plugin)
//    implementation(libs.hilt.gradle.plugin)
//    implementation(libs.androidx.room.gradle.plugin)
}