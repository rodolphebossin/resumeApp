package com.rodolphebossin.resumeapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
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
            ResumeNavHost(
                navController = navController,
                viewModel = viewModel,
                allScreens = viewModel.allScreens,
            )
        }
    }

    @Test
    fun resumeNavHost(){
        composeTestRule
            .onNode(hasText("Rodolphe Bossin"))
            .assertIsDisplayed()
    }

    @Test
    fun resumeNavHost_NavigateToBio(){
        composeTestRule
            .onNode(hasText("BIO"))
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("Bio Screen")
            .assertIsDisplayed()
    }

    @Test
    fun resumeNavHost_NavigateToHobbies(){
        composeTestRule.onNode(hasText("LOISIRS")).apply {
            performScrollTo()
            performClick()
        }
        composeTestRule
            .onNodeWithContentDescription("Hobbies Screen")
            .assertIsDisplayed()

    }
}