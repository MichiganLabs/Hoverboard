package com.michiganlabs.hoverboard.app

enum class TestScreen(val route: String, val title: String) {
    Home("content/home", "Home"),
    Details("content/home/details", "Home Details"),

    Settings("settings/home", "Settings")
}
