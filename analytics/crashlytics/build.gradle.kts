plugins {
    id("com.michiganlabs.hoverboard.android-library")
}

android {
    namespace = "com.michiganlabs.hoverboard.analytics.crashlytics"
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = project.property("publishingGroupId").toString()
            version = project.property("publishingVersionNumber").toString()
            artifactId = "analytics-crashlytics"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

dependencies {
    implementation(libs.timber)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
}
