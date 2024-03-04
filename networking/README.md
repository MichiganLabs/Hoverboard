# Hoverboard Networking

* [Friendly Moshi Extensions](./documentation/moshi_ext.md)

## Installation

### GitHub Packages
Setting up to install from GitHub Packages requires three steps.

### Setup GitHub Token
1. First navigate to your personal [GitHub account and create a new access token.](https://github.com/settings/tokens)
2. Save user username and token to your root `gradle.properties`
```bash
code ~/.gradle/gradle.properties
```

```
gitHubHoverboardUsername = YOUR_GITHUB_USERNAME
gitHubHoverboardPassword = YOUR_GITHUB_TOKEN
```


### Update settings.gradle.kts
Now we need to inform Gradle that we would like to add the GitHub location to our list of valid repositories. In your `settings.gradle.kts` location the `repositories` section and add the following maven entry
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

### Import in build.gradle.kts
```kotlin
dependencies {
  ...

  implementation("com.michiganlabs.hoverboard:networking:0.0.0-alpha1")
}
```

## Deploying Package
Deployment of the package is currently a manual process. This should be connected to a GitHub action on a Tag/Release.
```
./gradlew networking:publish
```