package com.rodolphebossin.resumeapp.ui.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.rodolphebossin.resumeapp.R

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 */

@Composable
fun LandingScreen(
    onClickSeeBio: () -> Unit = {},
    onClickSeeForces: () -> Unit = {},
    onClickSeeParcours: () -> Unit = {},
    onClickSeeCompetences: () -> Unit = {},
    onClickSeeTechnos: () -> Unit = {},
    onClickSeeFormation: () -> Unit = {},
    onClickSeeLoisirs: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "Landing Screen" },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile pic
        Surface(
            modifier = Modifier.size(100.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(painter = painterResource(id = R.drawable.perso), contentDescription = "profile pic")
        }

        // Name
        Text(text ="Rodolphe Bossin")
        // Position
        Text(text ="Développeur Android")
        // Buttons
        TextButton(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary
            ),
            onClick = onClickSeeBio,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Bio")
        }
    }
}