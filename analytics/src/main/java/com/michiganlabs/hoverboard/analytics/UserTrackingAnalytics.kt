package com.michiganlabs.hoverboard.analytics

/**
 * Interface for logging user information.
 */
interface UserTrackingAnalytics {
    /**
     * Set an Id for the user to allow for linking all related analytics together
     */
    fun setUserId(id: String)

    /**
     * Set a property for the user
     */
    fun setUserProperty(key: String, value: String)

    /**
     * Set a list of properties for the user in the form of key value pairs
     */
    fun setUserProperties(values: List<Pair<String, String>>)
}