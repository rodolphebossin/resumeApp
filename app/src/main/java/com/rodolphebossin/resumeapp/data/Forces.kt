package com.rodolphebossin.resumeapp.data

import android.text.SpannableStringBuilder
import androidx.core.text.bold

/**
 * Created by Rodolphe Bossin on 31/01/2022.
 */

object DataForces {
    val forces:List<String> = listOf(
        firstLine.toString(),
        secondLine.toString(),
        "Autonome et fiable",
        "Adaptable et réactif",
        "Orienté résultats",
        "À l’aise dans la prise de décisions",
        finalLine.toString()
    )
}

val firstLine = SpannableStringBuilder()
    .bold { append("Bilingue français/anglais\n") }
    .append("6 ans de vie et travail aux USA.")

val secondLine = SpannableStringBuilder()
    .bold { append("Double cursus créatif et scientifique\n") }
    .append("acilité à assurer la liaison entre les univers créatifs et techniques.")

val finalLine = SpannableStringBuilder()
    .append("Certifié ")
    .bold { append("PSM I")  }