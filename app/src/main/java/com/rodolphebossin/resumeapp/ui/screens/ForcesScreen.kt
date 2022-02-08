package com.rodolphebossin.resumeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodolphebossin.resumeapp.R
import com.rodolphebossin.resumeapp.data.DataForces

/**
 * Created by Rodolphe Bossin on 31/01/2022.
 * Displays my professional strengths
 */

@Composable
fun ForcesScreen(paragraphs: List<String>) {
    Column(
        modifier = Modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            paragraphs.forEach { item ->
                ForceParagraph(item)
            }
        }
        Image(painter = painterResource(R.drawable.psmi), contentDescription = "scrum logo")
    }
}

/**
 * Builds a paragraph for each strength
 * @param paragraph a string describing the strength
 */
@Composable
fun ForceParagraph(paragraph: String) {
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
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true, name = "Forces")
@Composable
fun ForcesScreenPreview() {
    ForcesScreen(DataForces.forces)
}