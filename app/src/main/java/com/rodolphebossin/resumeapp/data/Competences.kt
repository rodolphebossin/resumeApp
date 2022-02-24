package com.rodolphebossin.resumeapp.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 */
@Immutable
data class Competence(
    val title: String,
    val icon: ImageVector,
    val details: List<String>,
)

/**
 * Pretend repository for data.
 */
object DataCompetences {
    val competences: List<Competence> = listOf(
        Competence(
            "Savoir-Faire",
            Icons.Filled.Work,
            listOf(
                "Communiquer in situ ou à distance, animer des réunions en français et anglais.",
                "Coordonner un projet, fédérer et motiver une équipe autour d’un objectif.",
                "Rechercher des clients et fournisseurs : sourcing international, évaluation, audits.",
                "Négocier des prix, délais, quantités et conditions commerciales."
            )
        ),
        Competence(
            "Savoirs",
            Icons.Filled.School,
            listOf(
                "Titulaire d'un titre professionnel de concepteur développeur d'applications de niveau 6.",
                "Maitrise de toutes les étapes de la création et du développement, du besoin client jusqu’au produit fini.",
                "Connaissance de tous les acteurs : marketing, création, technique et commercial. 15+ années d’expérience en design et développement produit.",
            )
        ),
        Competence(
            "Savoir-Être",
            Icons.Filled.AccountCircle,
            listOf(
                "Remplir des missions et fournir des résultats en autonomie : 8 ans de travail en indépendant.",
                "Analyser et réagir face à l’imprévu, s’adapter aux changements.",
                "Enthousiaste et ouvert aux autres, je suis à l’aise dans tout environnement et m’intègre facilement à toute équipe : 15 ans de voyages de développement autour du monde.",
            )
        )
    )
}