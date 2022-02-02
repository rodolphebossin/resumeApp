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
import androidx.navigation.compose.rememberNavController
import com.rodolphebossin.resumeapp.data.*
import com.rodolphebossin.resumeapp.ui.Screens
import com.rodolphebossin.resumeapp.ui.screens.BioScreen
import com.rodolphebossin.resumeapp.ui.screens.CompetencesScreen
import com.rodolphebossin.resumeapp.ui.components.ResumeTabRow
import com.rodolphebossin.resumeapp.ui.screens.ForcesScreen
import com.rodolphebossin.resumeapp.ui.screens.FormationScreen
import com.rodolphebossin.resumeapp.ui.screens.HobbiesScreen
import com.rodolphebossin.resumeapp.ui.screens.LandingScreen
import com.rodolphebossin.resumeapp.ui.screens.WorkExperiencesScreen
import com.rodolphebossin.resumeapp.ui.screens.TechnosScreen
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

@ExperimentalFoundationApi
@Composable
fun ResumeApp() {
    ResumeAppTheme {
        // TODO : find a way to get the list automatically
        val allScreens = listOf(Screens.Landing, Screens.Bio, Screens.Forces, Screens.Parcours, Screens.Competences, Screens.Technos, Screens.Formation, Screens.Loisirs)
        val navController = rememberNavController()
        val currentScreen = Screens.fromRoute(navController.currentBackStackEntry?.destination?.route) // update current screen
        Scaffold(
            topBar = { // TopBar with icons allowing navigation + links to LinkedIn and mail
                ResumeTabRow(
                    allScreens = allScreens,
                    onTabSelected = { screen -> navController.navigate(screen.route) }, // Navigates to selected screen
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            NavHost( // NavHost will receive all screens/destinations
                navController = navController,
                startDestination = Screens.Landing.route, // default destination
                modifier = Modifier.padding(innerPadding)
            ) { // List all possible destinations
                composable(route = Screens.Landing.route) {  // Overview destination route (default)
                    LandingScreen( // add navigation actions for buttons in LandingScreen
                        onClickSeeBio = { navController.navigate(Screens.Bio.route) },
                        onClickSeeForces = { navController.navigate(Screens.Forces.route) },
                        onClickSeeParcours = { navController.navigate(Screens.Parcours.route) },
                        onClickSeeCompetences = { navController.navigate(Screens.Competences.route) },
                        onClickSeeTechnos = { navController.navigate(Screens.Technos.route) },
                        onClickSeeFormation = { navController.navigate(Screens.Formation.route) },
                        onClickSeeLoisirs = { navController.navigate(Screens.Loisirs.route) }
                    ) // screen content
                }
                composable(route = Screens.Bio.route) {
                    BioScreen(paragraphs = DataBio.bio)
                }
                composable(route = Screens.Forces.route) {
                    ForcesScreen(paragraphs = DataForces.forces)
                }
                composable(route = Screens.Parcours.route) {
                    WorkExperiencesScreen(experiences = DataExperiences.workExperiences)
                }
                composable(route = Screens.Competences.route) {
                    CompetencesScreen(competences = DataCompetences.competences)
                }
                composable(route = Screens.Technos.route) {
                    TechnosScreen(technosList = DataTechnos.technos)
                }
                composable(route = Screens.Formation.route) {
                    FormationScreen(formations = DataFormation.formations)
                }
                composable(route = Screens.Loisirs.route) {
                    HobbiesScreen(hobbies = DataHobbies.hobbies)
                }
            }
        }
    }
}



