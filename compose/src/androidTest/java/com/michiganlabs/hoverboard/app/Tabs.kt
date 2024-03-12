package com.michiganlabs.hoverboard.app

enum class Tabs(
    val root: String,
    val destinationRoute: String,
    val tabName: String
) {
    Content("content", TestScreen.Home.route, "Content Tab"),
    Settings("settings", TestScreen.Settings.route, "Settings Tab")
}
