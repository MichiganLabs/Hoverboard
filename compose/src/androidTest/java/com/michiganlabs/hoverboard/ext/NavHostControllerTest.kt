package com.michiganlabs.hoverboard.ext

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.michiganlabs.hoverboard.app.TestApp
import com.michiganlabs.hoverboard.analytics.ScreenTrackingAnalytics
import com.michiganlabs.hoverboard.app.Tabs
import com.michiganlabs.hoverboard.app.TestScreen
import com.michiganlabs.hoverboard.compose.ext.trackDestinationChanges
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavHostControllerTest {

    @get:Rule val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupCupcakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            TestApp(navController = navController)
        }
    }

    @Test
    fun navHostController_ScreenTracking_TabNavigationBackUpTree() {
        val testDestinationListener = object : ScreenTrackingAnalytics {
            val destinationChanges = mutableListOf<String?>()
            override fun destinationChanged(route: String?) {
                destinationChanges.add(route)
            }
        }
        navController.trackDestinationChanges(testDestinationListener)

        composeTestRule.onNodeWithText(TestScreen.Details.title).performClick()
        composeTestRule.onNodeWithText(Tabs.Content.tabName).performClick()

        assertEquals(testDestinationListener.destinationChanges.size, 3)
        assertEquals(testDestinationListener.destinationChanges[0], TestScreen.Home.route)
        assertEquals(testDestinationListener.destinationChanges[1], TestScreen.Details.route)
        assertEquals(testDestinationListener.destinationChanges[2], TestScreen.Home.route)
    }

    @Test
    fun navHostController_ScreenTracking_TabNavigationAcrossTabs() {
        val testDestinationListener = object : ScreenTrackingAnalytics {
            val destinationChanges = mutableListOf<String?>()
            override fun destinationChanged(route: String?) {
                destinationChanges.add(route)
            }
        }
        navController.trackDestinationChanges(testDestinationListener)

        composeTestRule.onNodeWithText(Tabs.Settings.tabName).performClick()
        composeTestRule.onNodeWithText(TestScreen.Details.title).performClick()
        composeTestRule.onNodeWithText(Tabs.Content.tabName).performClick()

        assertEquals(testDestinationListener.destinationChanges.size, 4)
        assertEquals(testDestinationListener.destinationChanges[0], TestScreen.Home.route)
        assertEquals(testDestinationListener.destinationChanges[1], TestScreen.Settings.route)
        assertEquals(testDestinationListener.destinationChanges[2], TestScreen.Details.route)
        assertEquals(testDestinationListener.destinationChanges[3], TestScreen.Home.route)
    }
}
