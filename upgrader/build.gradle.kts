plugins {
    id("com.michiganlabs.hoverboard.android-library")
}

android {
    namespace = "com.michiganlabs.hoverboard.upgrader"
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = project.property("publishingGroupId").toString()
            version = project.property("publishingVersionNumber").toString()
            artifactId = "upgrader"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.config)
}
