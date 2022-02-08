package com.rodolphebossin.resumeapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rodolphebossin.resumeapp.data.Formation

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 * Screen displaying my education
 */

@Composable
fun FormationScreen(formations: List<Formation>) {
    LazyColumn(
        modifier = Modifier.padding(vertical = 8.dp)
    ) { // LazyColumn takes a list(items) as parameter
        items(items = formations) { formation -> // for each of the items
            FormationCard(formation = formation)
        }
    }
}

@Composable
private fun FormationCard(formation: Formation) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth()
    ) {
        FormationCardContent(formation)
    }
}

@Composable
private fun FormationCardContent(formation: Formation) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            text = formation.dates,
            style = MaterialTheme.typography.subtitle1
        )
        Text(text = formation.title,
            style = MaterialTheme.typography.h4)
        if (formation.subtitle != null) Text(text = formation.subtitle,
            style = MaterialTheme.typography.h6)
        Text(text = "${formation.school}, ${formation.city}",
            style = MaterialTheme.typography.body1)
    }
}