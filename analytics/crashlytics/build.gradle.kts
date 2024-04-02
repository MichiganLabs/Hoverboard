plugins {
    id("com.michiganlabs.hoverboard.android-library")
    id("com.michiganlabs.hoverboard.crashlytics")
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
}