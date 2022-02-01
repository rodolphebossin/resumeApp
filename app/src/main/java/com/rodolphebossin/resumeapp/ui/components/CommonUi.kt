package com.rodolphebossin.resumeapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 */

@Composable
fun MissionRow(mission: String) {
    Row(modifier = Modifier.padding(bottom = 8.dp)) {
        Icon(
            imageVector = Icons.Filled.ArrowRight,
            contentDescription = "arrow right"
        )
        Text(
            text = mission,
            style = MaterialTheme.typography.body1
        )
    }
}