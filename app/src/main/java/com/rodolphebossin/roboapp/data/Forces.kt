package com.rodolphebossin.roboapp.data

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight


/**
 * Created by Rodolphe Bossin on 31/01/2022.
 */

object DataForces {
val forces: List<AnnotatedString> = listOf(
        firstLine,
        secondLine,
        thirdLine,
        fourthLine,
        finalLine
    )
}

val firstLine = with(AnnotatedString.Builder()) {
    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
    append("Bilingue français/anglais\n")
    pop()
    append("6 ans de vie et travail aux USA.")
    toAnnotatedString()
}

val secondLine = with(AnnotatedString.Builder()) {
    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
    append("Double cursus créatif et scientifique\n")
    pop()
    append("facilité à assurer la liaison entre les univers créatifs et techniques.")
    toAnnotatedString()
}

val thirdLine = with(AnnotatedString.Builder()) {
    append("20 ans d’expérience ")
    pushStyle(SpanStyle(fontWeight = FontWeight.Bold))
    append("internationale")
    pop()
    append(" dans la création et le développement produit.")
    toAnnotatedString()
}

val fourthLine = with(AnnotatedString.Builder()) {
    append("Enthousiaste,\n")
    append("curieux,\n")
    append("fiable,\n")
    append("polyvalent.")
    toAnnotatedString()
}

val finalLine = with(AnnotatedString.Builder()) {
    append("Certifié PSM I")
    toAnnotatedString()
}

