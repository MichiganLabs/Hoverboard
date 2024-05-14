package com.michiganlabs.hoverboard.upgrader

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.ConfigUpdate
import com.google.firebase.remoteconfig.ConfigUpdateListener
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings

class Upgrader(private val currentVersion: Long) {

    val shouldUpdate: Boolean
        get() = currentVersion < remoteConfig.getLong(requiredVersionKey)

    private val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
    private val requiredVersionKey = "required_version"
    private var requiredVersion: Long = 0

    init {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 60 // TODO: Alter this between debug and release
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
    }

    fun onAppLaunch() {
        remoteConfig.fetch()
        remoteConfig.addOnConfigUpdateListener(
            object : ConfigUpdateListener {
                override fun onUpdate(configUpdate: ConfigUpdate) {
                    requiredVersion = configUpdate.updatedKeys.contains(requiredVersionKey)
                        .let { remoteConfig.getLong(requiredVersionKey) }
                }

                override fun onError(error: FirebaseRemoteConfigException) {
                    TODO("Not yet implemented")
                }
            }
        )
    }

    fun checkForUpgrade() {
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                when (task.isSuccessful) {
                    true -> {
                    }

                    else -> {

                    }
                }
            }
    }

    /**
     * Send the user to the Play Store to update the app or vendor that you're using.
     */
    fun doUpgrade(upgradeCallback: () -> Unit) {
        upgradeCallback()
    }
}