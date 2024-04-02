import ext.libs

plugins {
    id("com.google.firebase.crashlytics")
}

dependencies {
    // Import the BoM for the Firebase platform
    "implementation"(platform(libs.findLibrary("firebase-bom").get()))

    // Add the dependencies for the Crashlytics and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    "implementation"(libs.findLibrary("firebase-crashlytics").get())
    "implementation"(libs.findLibrary("firebase-analytics").get())
}