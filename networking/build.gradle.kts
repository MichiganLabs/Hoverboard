plugins {
    alias(libs.plugins.com.michiganlabs.hoverboard.android.library)
    id("com.google.devtools.ksp")
    id("maven-publish")
}

android {
    namespace = "com.michiganlabs.hoverboard.data"
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = project.property("publishingGroupId").toString()
            version = project.property("publishingVersionNumber").toString()
            artifactId = "networking"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

dependencies {
    implementation(deps.moshi)
    ksp(deps.moshi.codegen)

    testImplementation(testDeps.junit)
}
