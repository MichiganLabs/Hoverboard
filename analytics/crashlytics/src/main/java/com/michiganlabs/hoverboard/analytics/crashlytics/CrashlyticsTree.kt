package com.michiganlabs.hoverboard.analytics.crashlytics


import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class CrashlyticsTree : Timber.Tree() {
    override fun isLoggable(tag: String?, priority: Int) = priority >= Log.DEBUG

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        val fc = FirebaseCrashlytics.getInstance()
        val priorityStr = when (priority) {
            Log.VERBOSE -> "V"
            Log.DEBUG -> "D"
            Log.INFO -> "I"
            Log.WARN -> "W"
            Log.ERROR -> "E"
            Log.ASSERT -> "WTF"
            else -> "?"
        }

        val prefix = when (tag) {
            null -> priorityStr
            else -> "$priorityStr/$tag"
        }

        fc.log("$prefix: $message")

        if (t != null) {
            fc.recordException(t) // test comment
        }
    }
}
