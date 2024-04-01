pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    versionCatalogs {
        create("deps") {
            // Versions
            version("moshi", "1.15.0")

            // Networking
            library("moshi", "com.squareup.moshi", "moshi").versionRef("moshi")
            library("moshi.codegen", "com.squareup.moshi", "moshi-kotlin-codegen").versionRef("moshi")
        }

        create("testDeps") {
            library("junit", "junit", "junit").version("4.13.2")
        }
    }

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "hoverboard"
includeBuild("convention-plugins")
include(":networking")
include(":compose")
include(":analytics")
