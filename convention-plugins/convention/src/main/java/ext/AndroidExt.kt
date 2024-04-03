import com.android.build.api.dsl.CommonExtension
import ext.libs
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

/**
 * Sets common values for Android Applications and Libraries
 */
fun org.gradle.api.Project.commonConfiguration(
    extension: CommonExtension<*, *, *, *, *, *>
) = extension.apply {
    compileSdk = libs.findVersion("compile-sdk").get().requiredVersion.toInt()

    defaultConfig {
        minSdk = libs.findVersion("min-sdk").get().requiredVersion.toInt()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        buildConfig = true
    }
}

fun org.gradle.api.Project.composeConfiguration(
    extension: CommonExtension<*, *, *, *, *, *>
) = extension.apply {
    buildFeatures {
        compose = true
    }
    composeOptions {
        val composeCompilerVersion = libs
            .findVersion("androidx-compose-compiler")
            .get()
            .requiredVersion
        println("USING VERSION $composeCompilerVersion")
        kotlinCompilerExtensionVersion = composeCompilerVersion
    }
}

fun org.gradle.api.Project.configureKotlinAndroid(
    kotlinAndroidProjectExtension: KotlinAndroidProjectExtension
) {
    val jvmToolchainVersion = libs.findVersion("jvmTarget").get().requiredVersion.toInt()
    kotlinAndroidProjectExtension.apply {
        jvmToolchain(jvmToolchainVersion)
    }
}
