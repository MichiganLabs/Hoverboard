// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.3.0" apply false
    id("com.android.library") version "8.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
}

subprojects {
    apply(plugin = "maven-publish")

    configure<PublishingExtension> {
        repositories {
            maven {
                url = uri("https://maven.pkg.github.com/michiganlabs/hoverboard")

                credentials(PasswordCredentials::class) {
                    val gitHubHoverboardUsername: String? by project
                    val gitHubHoverboardPassword: String? by project

                    username = gitHubHoverboardUsername ?: System.getenv("PUBLISH_USERNAME")
                    password = gitHubHoverboardPassword ?: System.getenv("PUBLISH_PASSWORD")
                }
            }
        }
    }
}
