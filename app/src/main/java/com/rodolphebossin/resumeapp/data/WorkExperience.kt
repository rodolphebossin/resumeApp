package com.rodolphebossin.resumeapp.data

import androidx.compose.runtime.Immutable
import com.rodolphebossin.resumeapp.R

/**
 * Created by Rodolphe Bossin on 31/01/2022.
 */
@Immutable
data class WorkExperience(
    val dates: String,
    val title: String,
    val icons: List<Int>,
    val address: String,
    val missions: List<String>?
)

/**
 * Pretend repository for data.
 */
object DataExperiences {
    val workExperiences: List<WorkExperience> = listOf(
        WorkExperience(
            "Mai - Septembre 2021",
            "Développeur d'applications - Stagiaire",
            listOf(R.drawable.logoexotic_bleu___cropped, R.drawable.logoexotic_blanc___cropped),
            "Clermont-Ferrand, France",
            listOf(
                "Développement d’une application Android de paramétrage de balises en NFC.",
                "Création d’une web-app de géolocalisation de balises en JS.",
                "Utilisation du protocole MQTT, des bases de données time-series (Influx-db)."
            )
        ),
        WorkExperience(
            "2014 - 2019",
            "Responsable design et développement global chaussures",
            listOf(R.drawable.logorip_black, R.drawable.logorip_white),
            "Costa Mesa, Californie, USA",
            listOf(
                "Coordination, élaboration et optimisation de l’offre mondiale :\n" +
                        "4 millions de \$ de CA, +30% de croissance annuelle 2016-18.",
                "Création des modèles, planification et suivi du développement.",
                "Responsable prix, sourcing et veille technologique."
            )
        ),
        WorkExperience(
            "2007 - 2014",
            "Designer et Développeur Chaussures Senior",
            listOf(R.drawable.logorip_black, R.drawable.logorip_white),
            "Hossegor, France",
            null
        ),
        WorkExperience(
            "1999 - 2007",
            "Designer et Développeur Chaussures - Freelance",
            listOf(R.drawable.logorobo_black, R.drawable.logorobo_white),
            "Marseille, France",
            null
        ),
        WorkExperience(
            "1997 - 1999",
            "Designer produit",
            listOf(R.drawable.logozebra_black, R.drawable.logozebra_white),
            "BARRÉ & ASSOCIÉS, Lyon",
            null
        ),
        WorkExperience(
            "1996 -1997",
            "Designer junior",
            listOf(R.drawable.logorossignol_black, R.drawable.logorossignol_white),
            "Grenoble",
            null
        ),
    )
}