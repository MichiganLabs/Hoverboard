package com.michiganlabs.hoverboard.analytics

/**
 * Interface for Tracking Screen Navigation
 */
interface ScreenTrackingAnalytics {

    /**
     * Log that the User has navigated to a screen.
     */
    fun destinationChanged(route: String?)
}
