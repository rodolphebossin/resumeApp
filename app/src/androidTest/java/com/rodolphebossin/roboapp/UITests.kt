package com.rodolphebossin.roboapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rodolphebossin.roboapp.ui.Screens
import com.rodolphebossin.roboapp.ui.components.ScrollableTabRow
import com.rodolphebossin.roboapp.ui.theme.RoboAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by Rodolphe Bossin on 19/02/2022.
 */
class UITests {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: NavHostController

    @Before
    fun setupResumeNavHost() {
        composeTestRule.setContent {
            val viewModel = ResumeViewModel()
            navController = rememberNavController()
            val backstackEntry =
                navController.currentBackStackEntryAsState() // provide with the current backStack entry as a State
            val currentScreen = Screens.fromRoute( // Update current screen
                backstackEntry.value?.destination?.route // query the current back stack entry for its route
            )
            RoboAppTheme {
                ResumeNavHost(
                    navController = navController,
                    viewModel = viewModel,
                    allScreens = viewModel.allScreens,
                )
                if (currentScreen != Screens.Home) {
                    ScrollableTabRow( // TopBar with icons allowing navigation
                        viewModel = viewModel,
                        allScreens = viewModel.allScreens,
                        onTabSelected = { screen -> navController.navigate(screen.route) }, // Navigates to selected screen
                        currentScreen = currentScreen
                    )
                }
            }
        }
    }

    @Test
    fun resumeNavHost() {
        composeTestRule
            .onNode(hasText("Rodolphe Bossin"))
            .assertIsDisplayed()
    }

    @Test
    fun resumeNavHost_NavigateToBio() {
        composeTestRule
            .onNode(hasText("BIO"))
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("Bio Screen")
            .assertIsDisplayed()
    }

    @Test
    fun resumeNavHost_NavigateToHobbies() {
        composeTestRule.onNode(hasText("LOISIRS")).apply {
            performScrollTo()
            performClick()
        }
        composeTestRule
            .onNodeWithContentDescription("Hobbies Screen")
            .assertIsDisplayed()

    }

    @Test
    fun scrollableTabRowIsDisplayed() {
        composeTestRule
            .onNode(hasText("FORCES"))
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("ScrollableTabRow")
            .assertIsDisplayed()

    }

    @Test
    fun scrollableTabRowNotDisplayedOnHomeScreen() {
        composeTestRule
            .onNode(hasText("FORCES"))
            .performClick()
        composeTestRule
            .onNode(hasText("HOME")).apply {
                performScrollTo()
                performClick()
            }
        composeTestRule
            .onNodeWithContentDescription("ScrollableTabRow")
            .assertDoesNotExist()
    }
}