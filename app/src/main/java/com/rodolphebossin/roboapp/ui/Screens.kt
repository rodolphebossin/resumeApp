package com.rodolphebossin.roboapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 */
sealed class Screens(val route: String, val icon: ImageVector) {

    object Home : Screens("home", icon = Icons.Filled.Home)
    object Bio : Screens("bio", icon = Icons.Filled.AccountCircle)
    object Forces : Screens("forces", icon = Icons.Filled.PieChart)
    object Parcours : Screens("parcours", icon = Icons.Filled.List)
    object Competences : Screens("compétences", icon = Icons.Filled.Work)
    object Technos : Screens("technos", icon = Icons.Filled.Laptop)
    object Formation : Screens("formation", icon = Icons.Filled.School)
    object Loisirs : Screens("loisirs", icon = Icons.Filled.Favorite)

    companion object {
        fun fromRoute(route: String?): Screens =
            when (route) {
                "home" -> Home
                "bio" -> Bio
                "forces" -> Forces
                "parcours" -> Parcours
                "compétences" -> Competences
                "technos" -> Technos
                "formation" -> Formation
                "loisirs" -> Loisirs
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }

}