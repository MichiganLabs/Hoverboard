# Hoverboard Analytics

Hoverboard Analytics provides interfaces for type safe calls to provide consistency in basic analytics across projects. In the initial state this includes logging user information and screen tracking.

## Installation

Refer to [Hoverboard Installation](../README.md) for adding GitHub packages to your project. Then add the following to your `build.gradle.kts`


```kotlin
dependencies {
  ...

  implementation("com.michiganlabs.hoverboard:analytics:THE_VERSION_NUMBER")
}
```