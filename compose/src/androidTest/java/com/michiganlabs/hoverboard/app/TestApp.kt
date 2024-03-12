package com.michiganlabs.hoverboard.app

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navigation
import com.michiganlabs.hoverboard.compose.ext.switchTabs


@Composable
fun TestApp(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Tabs.Content.root,
            modifier = modifier.padding(innerPadding)
        ) {
            navigation(
                route = Tabs.Content.root,
                startDestination = Tabs.Content.destinationRoute
            ) {
                composable(TestScreen.Home.route) {
                    LazyColumn {
                        item { Text(text = TestScreen.Home.title) }
                        item {
                            Button(onClick = { navController.navigate(TestScreen.Details.route) }) {
                                Text(TestScreen.Details.title)
                            }
                        }
                    }
                }

                composable(TestScreen.Details.route) {
                    Text(text = TestScreen.Details.title)
                }
            }

            navigation(
                route = Tabs.Settings.root,
                startDestination = Tabs.Settings.destinationRoute
            ) {
                composable(TestScreen.Settings.route) {
                    LazyColumn {
                        item { Text(text = TestScreen.Home.title) }
                        item {
                            Button(onClick = { navController.navigate(TestScreen.Details.route) }) {
                                Text(TestScreen.Details.title)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomNavigation(navController: NavHostController) {
    val backStack by navController.currentBackStackEntryAsState()
    val currentDestination = backStack?.destination

    NavigationBar {
        Tabs.entries.forEach { navItem ->
            NavigationBarItem(
                icon = { },
                label = { Text(navItem.tabName) },
                selected = currentDestination?.hierarchy?.any { it.route == navItem.root } == true,
                onClick = {
                    when (currentDestination?.hierarchy?.any { it.route == navItem.root }) {
                        true -> {
                            if (currentDestination.route != navItem.destinationRoute) {
                                navController.navigate(route = navItem.root) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        inclusive = false
                                    }
                                }
                            }
                        }
                        else -> navController.switchTabs(navItem.root)
                    }
                }
            )
        }
    }
}
