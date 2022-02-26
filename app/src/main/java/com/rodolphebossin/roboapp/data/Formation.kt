package com.rodolphebossin.roboapp.data

import androidx.compose.runtime.Immutable

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 */

@Immutable
data class Formation(
    val id: Int,
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
            1,
            "2020-2021",
            "Concepteur, développeur d’applications",
            "RNCP de Niveau 6\n" +
                    "(équivalent Bac+3/4)",
            "HUMANBooster",
            "Clermont-Ferrand"
        ),
        Formation(
            2,
            "1994-1996",
            "Maîtrise de design produit",
            null,
            "ÉCOLE D’ARCHITECTURE",
            "Grenoble"
        ),
        Formation(
            3,
            "1993-1994",
            "1ère année d’Ecole d’Ingénieur",
            null,
            "INSTITUT SUPÉRIEUR DES TECHNIQUES DU GLOBE",
            "Grenoble"
        ),
        Formation(
            4,
            "1991-1993",
            "Classes Préparatoires Scientifiques",
            null,
            "LYCÉE LAFAYETTE",
            "Clermont Ferrand"
        )
    )
}