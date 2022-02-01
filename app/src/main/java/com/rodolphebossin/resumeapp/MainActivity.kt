package com.rodolphebossin.resumeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rodolphebossin.resumeapp.data.DataBio
import com.rodolphebossin.resumeapp.ui.Screens
import com.rodolphebossin.resumeapp.ui.bio.BioScreen
import com.rodolphebossin.resumeapp.ui.landing.LandingScreen
import com.rodolphebossin.resumeapp.ui.theme.ResumeAppTheme

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResumeAppTheme {
                ResumeApp()
            }
        }
    }
}

@Composable
fun ResumeApp() {
    ResumeAppTheme {
        val navController = rememberNavController()
        val backstackEntry =
            navController.currentBackStackEntryAsState() // provide with the current backStack entry as a State
        // var currentScreen = RallyScreen.fromRoute( // Update current screen
        //     backstackEntry.value?.destination?.route // query the current back stack entry for its route
        // )
        Scaffold(
            topBar = { // TopBar with icons allowing navigation between screens

            }
        ) { innerPadding ->
            NavHost( // NavHost will receive all screens/destinations
                navController = navController,
                startDestination = Screens.Landing.route, // default destination
                modifier = Modifier.padding(innerPadding)
            ) { // List all possible destinations
                composable(route = Screens.Landing.route) {  // Overview destination route (default)
                    LandingScreen( // add navigation actions for buttons in OverviewBody
                        onClickSeeBio = { navController.navigate(Screens.Bio.route) }
                    ) // screen content
                }
                composable(route = Screens.Bio.route) {
                    BioScreen(paragraphs = DataBio.bio)
                }
            }
        }
    }
}



