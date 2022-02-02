package com.rodolphebossin.resumeapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodolphebossin.resumeapp.R
import com.rodolphebossin.resumeapp.data.DataExperiences
import com.rodolphebossin.resumeapp.data.WorkExperience
import com.rodolphebossin.resumeapp.ui.components.MissionRow

/**
 * Created by Rodolphe Bossin on 31/01/2022.
 */

/**
 * Handles the recyclerView of all ExperienceCards
 * @param list of WorkExperiences
 */
@Composable
fun WorkExperiencesScreen(experiences: List<WorkExperience>) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 8.dp),
    ) { // LazyColumn takes a list(items) as parameter
        items(items = experiences) { experience -> // for each of the items
            ExperienceCard(experience = experience)
        }
    }
}

@Composable
private fun ExperienceCard(experience: WorkExperience) {
    Card(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        elevation = 4.dp
    ) {
        ExperienceCardContent(experience)
    }
}

@Composable
fun ExperienceCardContent(experience: WorkExperience) {
    var expanded by remember { mutableStateOf(false) } // Remembers if column is expanded por not
    Column(
        modifier = Modifier.animateContentSize( // animates the deployment of the content and adjusts to it
            animationSpec = spring( // specifies type of animation with parameters
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f) // giving weight to the first element
                    // makes it fill the portion of the space available defined by the value,
                    // pushing trailing elements in the remaining available space,
                    // so here the column will fill ALL available space,
                    // pushing the button at the end
                    .padding(12.dp)
            ) {
                Text(text = experience.dates, style = MaterialTheme.typography.subtitle2)
                Text(
                    text = experience.title,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Surface(
                        modifier = Modifier.size(60.dp).padding(start = 1.dp),
                    ) {
                        Image(
                            painter = if (isSystemInDarkTheme()) {
                                painterResource(experience.icons[1])
                            } else {
                                painterResource(experience.icons[0])
                            },
                            contentDescription = "company logo",
                        )
                    }

                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = experience.address)
                }
            }
            if (experience.missions != null) {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon( // conditional icon that toggles look and content depending on expanded
                        imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                        contentDescription = if (expanded) stringResource(R.string.show_less) else stringResource(
                            R.string.show_more
                        )
                    )
                }
            }
        }
        if (expanded) { // only shows this text if expanded
            if (experience.missions != null) { // only shows if there are missions described
                Column(
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp) // .verticalScroll(rememberScrollState())
                ) { // LazyColumn takes a list(items) as parameter
                    experience.missions.forEach { mission -> // for each of the items
                        MissionRow(mission = mission)
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true, name = "Expérience")
@Composable
fun ProfessionalExperiencePreview() {
    ExperienceCard(DataExperiences.workExperiences[0])
}