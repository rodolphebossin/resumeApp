package com.rodolphebossin.resumeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodolphebossin.resumeapp.R
import java.util.*

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
            // .padding(top = 32.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "Landing Screen" },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile pic
        Surface(
            modifier = Modifier.size(200.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.perso),
                contentDescription = "profile pic"
            )
        }

        // Name
        Text(
            text = "Rodolphe Bossin",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(8.dp)
        )
        // Position
        Text(
            text = "Développeur Android".uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Buttons
        LandingScreenButton(onClickSeeBio, "Bio")
        LandingScreenButton(onClickSeeForces, "Forces")
        LandingScreenButton(onClickSeeParcours, "Parcours")
        LandingScreenButton(onClickSeeCompetences, "Compétences")
        LandingScreenButton(onClickSeeTechnos, "Technos")
        LandingScreenButton(onClickSeeFormation, "Formation")
        LandingScreenButton(onClickSeeLoisirs, "Loisirs")
    }
}

@Composable
fun LandingScreenButton(
    onClick: () -> Unit = {},
    text: String
) {
    TextButton(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = ButtonDefaults.elevation()
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            ) {
            Text(
                text = text.uppercase(Locale.getDefault()),
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "•••",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterEnd)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LandingScreenButtonPreview() {
    LandingScreenButton(onClick = {}, text = "test")
}