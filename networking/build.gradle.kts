plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("maven-publish")
}

android {
    namespace = "com.michiganlabs.hoverboard.data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.michiganlabs.hoverboard"
            artifactId = "networking"
            version = "0.0.0-alpha1"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/michiganlabs/hoverboard")
            credentials {
                username = ""
                password = ""
            }
        }
    }
}

dependencies {
    implementation(deps.moshi)
    ksp(deps.moshi.codegen)

    testImplementation(testDeps.junit)
}
