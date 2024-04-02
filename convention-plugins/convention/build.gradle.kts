plugins {
    `kotlin-dsl`
    `maven-publish`
}


group = "com.michiganlabs.hoverboard.convention-plugins"
version = "1.0"

//publishing {
//    repositories {
//        maven {
//            // change to point to your repo, e.g. http://my.org/repo
//            url = uri(layout.buildDirectory.dir("repo"))
//        }
//    }
//}
//
//tasks.publish {
//    dependsOn("check")
//}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(libs.com.android.gradle.plugin)
    implementation(libs.org.jetbrains.kotlin.android.gradle.plugin)
    implementation(libs.firebase.crashlytics.gradle.plugin)
//    // Import the BoM for the Firebase platform
//    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
//
//    // Add the dependencies for the Crashlytics and Analytics libraries
//    // When using the BoM, you don't specify versions in Firebase library dependencies
//    implementation("com.google.firebase:firebase-crashlytics")
//    implementation("com.google.firebase:firebase-analytics")

//    implementation(libs.com.google.devtools.ksp.plugin)
//    implementation(libs.hilt.gradle.plugin)
//    implementation(libs.androidx.room.gradle.plugin)
}