package com.rodolphebossin.resumeapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rodolphebossin.resumeapp.data.*
import com.rodolphebossin.resumeapp.ui.Screens
import com.rodolphebossin.resumeapp.ui.components.ResumeTopAppBar
import com.rodolphebossin.resumeapp.ui.components.ScrollableTabRow
import com.rodolphebossin.resumeapp.ui.components.sendIntent
import com.rodolphebossin.resumeapp.ui.screens.*
import com.rodolphebossin.resumeapp.ui.theme.ResumeAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ResumeViewModel>()

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResumeAppTheme {
                ResumeApp(viewModel)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun ResumeApp(viewModel: ResumeViewModel) {
    ResumeAppTheme {
        // TODO : find a way to get the list automatically
        val allScreens = listOf(
            Screens.Home,
            Screens.Bio,
            Screens.Forces,
            Screens.Parcours,
            Screens.Competences,
            Screens.Technos,
            Screens.Formation,
            Screens.Loisirs
        )
        val navController = rememberNavController()
        val backstackEntry =
            navController.currentBackStackEntryAsState() // provide with the current backStack entry as a State
        val currentScreen = Screens.fromRoute( // Update current screen
            backstackEntry.value?.destination?.route // query the current back stack entry for its route
        )
        val context = LocalContext.current
        Scaffold(
            topBar = {
                Column() {
                    ResumeTopAppBar(
                        onMailClick = { sendIntent(emailIntent, context) },
                        onLinkedInClick = { sendIntent(linkedInIntent, context) }
                    )
                    // App name with robo icon + links to LinkedIn and mail
                    if (currentScreen != Screens.Home) {
                        ScrollableTabRow( // TopBar with icons allowing navigation
                            viewModel = viewModel,
                            allScreens = allScreens,
                            onChipSelected = { screen -> navController.navigate(screen.route) }, // Navigates to selected screen
                            currentScreen = currentScreen
                        )
                    }
                }

            }
        ) { innerPadding ->
            NavHost( // NavHost will receive all screens/destinations
                navController = navController,
                startDestination = Screens.Home.route, // default destination
                modifier = Modifier.padding(innerPadding)
            ) { // List all possible destinations
                composable(route = Screens.Home.route) {  // Overview destination route (default)
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
                    WorkExperiencesScreen(
                        experiences = DataExperiences.workExperiences,
                        viewModel = viewModel
                    )
                }
                composable(route = Screens.Competences.route) {
                    CompetencesScreen(
                        competences = DataCompetences.competences,
                        viewModel = viewModel
                    )
                }
                composable(route = Screens.Technos.route) {
                    TechnosScreen(technosList = DataTechnos.technos)
                }
                composable(route = Screens.Formation.route) {
                    FormationScreen(formations = DataFormation.formations)
                }
                composable(route = Screens.Loisirs.route) {
                    HobbiesScreen(hobbies = DataHobbies.hobbies, viewModel = viewModel)
                }
            }
        }
    }
}


val emailIntent = Intent(Intent.ACTION_SEND).apply {
    // The intent does not have a URI, so declare the "text/plain" MIME type
    type = "text/plain"
    putExtra(Intent.EXTRA_EMAIL, arrayOf("rodolphebossin@gmail.com")) // recipients
    putExtra(Intent.EXTRA_SUBJECT, "Demande de renseignement")
    putExtra(Intent.EXTRA_TEXT, "Bonjour Rodolphe,")
}

val linkedInIntent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/rodolphe-bossin/"))





