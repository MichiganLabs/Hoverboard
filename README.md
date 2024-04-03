# Hoverboard

Hoverboard contains common functionality, basic extensions, and quality of life improvements that MichiganLabs recommends for its Android projects. The primary focus of this project is to improve startup time and provide consistent implementations of common actions across all projects.  


## Packages
To provide for a more flexible "opt-in" approach, packages are intended to be focused to a specific area of need. Each package should aim to have the fewest number of dependencies possible to provide easier maintainability and prevent the bloating of applications that depend on the package.

* [Analytics](./analytics/README.md)
  * [Firebase](./analytics/firebase/README.md)
* [Compose](./compose/README.md)
* [Networking](./networking/README.md)

## Installation
All packages are currently being hosted exclusively in GitHub Packages. Setting up your application to be able to ingest the library requires setting up a GitHub token within your root gradle properties (this prevents accidentally leaking or sharing of a GitHub token) and then adding the GitHub Packages to your project. This is done in three overall steps.

### 1. Setup a GitHub Token
1. Login to GitHub and navigate to your personal [GitHub account and create a new access token.](https://github.com/settings/tokens)
2. Save user username and token to your root gradle.properties `~/.gradle/gradle.properties` and insert your username and token.
```
gitHubHoverboardUsername = YOUR_GITHUB_USERNAME
gitHubHoverboardPassword = YOUR_GITHUB_TOKEN
```

### 2. Add GitHub Packages to your `settings.gradle.kts`
Identify the `repositories` section in your `settings.gradle.kts`. **Be sure not to add it to the pluginManagement section!**

```kotlin
repositories {
  ...
    
  maven {
    url = uri("https://maven.pkg.github.com/michiganlabs/hoverboard")
    credentials {
      val gitHubHoverboardUsername: String? by settings
      val gitHubHoverboardPassword: String? by settings

      username = gitHubHoverboardUsername ?: System.getenv("GITHUB_USERNAME")
      password = gitHubHoverboardPassword ?: System.getenv("GITHUB_TOKEN")
    }
  }
}
```

### 3. Add the dependency
Each package is intentionally split out to provide opt-in functionality by general architecture. For the specific implementation string refer to the specific project. In general your `build.gradle.kts` file will add the following line.
```kotlin
dependencies {
  ...

  implementation("com.michiganlabs.hoverboard:THE_PACKAGE_NAME:THE_VERSION_NUMBER")
}
```


## Publishing
To publish a new package to GitHub Packages, perform the following steps.
1. Bump the version number in the `gradle.properties`
1. Tag the release `git tag vRELEASE_NUMBER`
1. Go to the [Releases](https://github.com/MichiganLabs/Hoverboard/releases) and create a new release with the tag
1. GitHub Action will immediately begin creating the new packages.
