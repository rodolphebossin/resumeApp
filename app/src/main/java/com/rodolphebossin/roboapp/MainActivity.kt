package com.rodolphebossin.roboapp

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rodolphebossin.roboapp.data.*
import com.rodolphebossin.roboapp.ui.Screens
import com.rodolphebossin.roboapp.ui.components.ResumeTopAppBar
import com.rodolphebossin.roboapp.ui.components.ScrollableTabRow
import com.rodolphebossin.roboapp.ui.components.sendIntent
import com.rodolphebossin.roboapp.ui.screens.*
import com.rodolphebossin.roboapp.ui.theme.RoboAppTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ResumeViewModel>()


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoboAppTheme {
                window?.statusBarColor = MaterialTheme.colors.primaryVariant.toArgb()
                ResumeApp(viewModel)
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ResumeApp(viewModel: ResumeViewModel) {
    RoboAppTheme {
        // TODO : find a way to get the list automatically
        val allScreens = viewModel.allScreens
        val navController = rememberNavController()
        val backstackEntry =
            navController.currentBackStackEntryAsState() // provide with the current backStack entry as a State
        val currentScreen = Screens.fromRoute( // Update current screen
            backstackEntry.value?.destination?.route // query the current back stack entry for its route
        )
        val context = LocalContext.current
        Scaffold(
            topBar = {
                Column() { // App name + links to LinkedIn and mail
                    ResumeTopAppBar(
                        onMailClick = { sendIntent(emailIntent, context) },
                        onLinkedInClick = { sendIntent(linkedInIntent, context) }
                    )
                    if (currentScreen != Screens.Home) {
                        ScrollableTabRow( // TopBar with icons allowing navigation
                            viewModel = viewModel,
                            allScreens = allScreens,
                            onTabSelected = { screen -> navController.navigate(screen.route) }, // Navigates to selected screen
                            currentScreen = currentScreen
                        )
                    }
                }
            }
        ) { innerPadding ->
            ResumeNavHost(
                navController = navController,
                allScreens = allScreens,
                viewModel = viewModel,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ResumeNavHost(
    navController: NavHostController,
    allScreens: List<Screens>,
    viewModel: ResumeViewModel,
    modifier: Modifier = Modifier
) {
    NavHost( // NavHost will receive all screens/destinations
        navController = navController,
        startDestination = Screens.Home.route, // default destination
        modifier = modifier
    ) { // List all possible destinations
        composable(route = Screens.Home.route) {  // Overview destination route (default)
            HomeScreen(
                navController,
                allScreens,
                viewModel = viewModel
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
            FormationsCardsScreen(viewModel = viewModel)
        }
        composable(route = Screens.Loisirs.route) {
            HobbiesScreen(hobbies = DataHobbies.hobbies, viewModel = viewModel)
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

val linkedInIntent: Intent =
    Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/rodolphe-bossin/"))





