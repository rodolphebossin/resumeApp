package com.rodolphebossin.resumeapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodolphebossin.resumeapp.ResumeViewModel
import com.rodolphebossin.resumeapp.data.Competence
import com.rodolphebossin.resumeapp.ui.components.MissionRow

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 * Screen that displays my professional and personal skills
 */


@Composable
fun CompetencesScreen(competences: List<Competence>, viewModel: ResumeViewModel) {
    var skill by rememberSaveable { mutableStateOf(0) }
    Column {
        Surface(elevation = 8.dp) {
            TabRow(selectedTabIndex = skill) {
                competences.forEachIndexed { index, competence ->
                    FancyTab(
                        competence = competence,
                        onClick = {
                            skill = index
                            viewModel.onSkillChange(skill)
                        },
                        selected = (index == skill)
                    )
                }
            }
        }
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            val currentlyShowing = competences[skill].details
            currentlyShowing.forEach { mission ->
                MissionRow(mission = mission)
            }
        }
    }
}

/**
 * Builds a tab for the tabRow of the screen
 */
@Composable
fun FancyTab(
    competence: Competence,
    selected: Boolean,
    onClick: () -> Unit
) {
    Tab(selected, onClick) {
        Column(
            Modifier
                .padding(10.dp)
                .height(50.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = competence.icon, contentDescription = "null")
            Text(
                text = competence.title,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}


