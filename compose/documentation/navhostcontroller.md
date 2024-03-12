# NavHostController

### Navigating Into Another Tab
The `NavHostController` may have wonky results when navigating into a screen into another tab. To correct this, all calls to navigate across tabs **should use the same technique** within the bottom navigation and on other button clicks. A generic option is provided like below. For more information, refer to the [Cross Tab Navigation in Jetpack Compose Blog Post](`https://michiganlabs.com/news/cross-tab-navigation-in-jetpack-compose)

```kotlin
NavigationBar {
    navigationItems.forEach { item ->
        NavigationBarItem(
            icon = { Icon(item.image, contentDescription = stringResource(item.stringId)) },
            label = { Text(stringResource(item.stringId)) },
            selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
            onClick = {
                when (currentDestination?.hierarchy?.any { it.route == item.route } == true) {
                    true -> {
                        // if already on the tab but not at the root, and tap on the tab, pop up to root
                        if (currentDestination?.route != item.startDestinationRoute) {
                            navController.navigate(route = item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    inclusive = false
                                }
                            }
                        }
                    }
                    else -> navController.switchTabs(item.route)
                }
            }
        )
    }
}
```

### Screen Tracking
Although Compose makes screen creation much more quick, it does prevent standard screen tracking from being effective as there are no Fragment/Activity changes. As a result, logging a "screen view" is up to the developer. A simple approach is provided here by hooking into destination changes within the NavHostController.

```kotlin
val navController = rememberNavController()

val screenTracker: ScreenTrackingAnalytics = MyScreenTracker()
navController.trackDestinationChanges(screenTracker)
```
