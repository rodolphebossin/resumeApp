package com.rodolphebossin.resumeapp.ui.competences

import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodolphebossin.resumeapp.data.Competence
import com.rodolphebossin.resumeapp.ui.components.MissionRow

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 */

@Composable
fun CompetencesScreen(competences: List<Competence>) {
    var currentlyShowing by remember { mutableStateOf(competences[0].details) }
    var button by remember { mutableStateOf(0) }
    Column(modifier = Modifier.padding(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (button == 0) MaterialTheme.colors.primary.copy(alpha = 0.5f) else MaterialTheme.colors.primary
                ),
                onClick = {
                    currentlyShowing = competences[0].details
                    button = 0
                }
            ) {
                Text(competences[0].title)
            }
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (button == 1) MaterialTheme.colors.primary.copy(alpha = 0.5f) else MaterialTheme.colors.primary
                ),
                onClick = {
                    currentlyShowing = competences[1].details
                    button = 1
                }
            ) {
                Text(competences[1].title)
            }
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (button == 2) MaterialTheme.colors.primary.copy(alpha = 0.5f) else MaterialTheme.colors.primary
                ),
                onClick = {
                    currentlyShowing = competences[2].details
                    button = 2
                }
            ) {
                Text(competences[2].title)
            }
        }
        Column(
            modifier = Modifier.padding(24.dp) // .verticalScroll(rememberScrollState())
        ) { // LazyColumn takes a list(items) as parameter
            currentlyShowing.forEach { mission -> // for each of the items
                MissionRow(mission = mission)
            }
        }
    }
}


