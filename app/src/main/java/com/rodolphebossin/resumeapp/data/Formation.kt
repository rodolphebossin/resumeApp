package com.rodolphebossin.resumeapp.data

import androidx.compose.runtime.Immutable

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 */

@Immutable
data class Formation(
    val dates: String,
    val title: String,
    val subtitle: String?,
    val school: String,
    val city: String,
)

/**
 * Pretend repository for data.
 */
object DataFormation {
    val formations: List<Formation> = listOf(
        Formation(
            "2020-2021",
            "Concepteur, développeur d’applications",
            "RNCP de Niveau 6\n" +
                    "(équivalent Bac+3/4)",
            "HUMANBooster",
            "Clermont-Ferrand"
        ),
        Formation(
            "1994-1996",
            "Maîtrise de design produit",
            null,
            "ÉCOLE D’ARCHITECTURE",
            "Grenoble"
        ),
        Formation(
            "1993-1994",
            "1ère année d’Ecole d’Ingénieur",
            null,
            "INSTITUT SUPÉRIEUR DES TECHNIQUES DU GLOBE",
            "Grenoble"
        ),
        Formation(
            "1991-1993",
            "Classes Préparatoires Scientifiques",
            null,
            "LYCÉE LAFAYETTE",
            "Clermont Ferrand"
        )
    )
}