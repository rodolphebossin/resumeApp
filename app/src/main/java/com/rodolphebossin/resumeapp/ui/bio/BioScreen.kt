package com.rodolphebossin.resumeapp.ui.bio

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodolphebossin.resumeapp.data.DataBio

/**
 * Created by Rodolphe Bossin on 31/01/2022.
 */

@Composable
fun BioPage(paragraphs: List<String>) {
    Column(modifier = Modifier.padding(12.dp)) {
        paragraphs.forEach { item ->
            BioParagraph(item)
        }
    }
}

@Composable
fun BioParagraph(paragraph: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Text(
                text = paragraph,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
        }
        BioDivider()
    }
}

/**
 * A vertical colored line that is used in a [BioParagraph] to separate paragraphs.
 */
@Composable
fun BioDivider() {
    Divider(
        color = MaterialTheme.colors.onBackground, thickness = 1.dp,
        modifier = Modifier.width(50.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun BioParagraphPreview() {
    BioParagraph(paragraph = "Fort de la solide formation suivie chez HUMANBooster, d’une première expérience professionnelle dans le monde de l’IoT et de 20 ans d’expérience internationale dans la création et le développement produit, je recherche un poste de développeur junior.")
}

@Preview(showBackground = true)
@Composable
fun BioScreenPreview() {
    BioPage(DataBio.bio)
}