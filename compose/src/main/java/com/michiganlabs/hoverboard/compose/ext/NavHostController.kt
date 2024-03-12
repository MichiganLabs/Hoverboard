package com.michiganlabs.hoverboard.compose.ext

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.michiganlabs.hoverboard.analytics.ScreenTrackingAnalytics

/**
 * Handle state while pressing on a button that will navigate to a destination
 * inside of another tab.
 *
 * For more information refer to the blog post on Cross Tab Navigation
 * https://michiganlabs.com/news/cross-tab-navigation-in-jetpack-compose
 */
fun NavHostController.switchTabs(route: String) {
    navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // re-selecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
}


/**
 * On navigation within the NavHostController log to Analytics that
 * the screen is being viewed.
 */
fun NavHostController.trackDestinationChanges(tracker: ScreenTrackingAnalytics) {
    this.addOnDestinationChangedListener { _, destination, _ ->
        tracker.destinationChanged(destination.route)
    }
}
