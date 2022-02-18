package com.rodolphebossin.resumeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rodolphebossin.resumeapp.R
import com.rodolphebossin.resumeapp.ui.components.AnimatedCircleOutline
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
    onClickSeeLoisirs: () -> Unit = {},
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
        // Box containing profile pic and animated border
        Box(Modifier.padding(16.dp)) {
            // animated border
            AnimatedCircleOutline(
                color = MaterialTheme.colors.secondary,
                Modifier
                    .height(210.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth()
            )
            // Profile pic
            Surface(
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.Center),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.perso),
                    contentDescription = "profile pic"
                )
            }
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
        LandingScreenGradientBtn(onClickSeeBio, "Bio")
        LandingScreenGradientBtn(onClickSeeForces, "Forces")
        LandingScreenGradientBtn(onClickSeeParcours, "Parcours")
        LandingScreenGradientBtn(onClickSeeCompetences, "Compétences")
        LandingScreenGradientBtn(onClickSeeTechnos, "Technos")
        LandingScreenGradientBtn(onClickSeeFormation, "Formation")
        LandingScreenGradientBtn(onClickSeeLoisirs, "Loisirs")
    }
}

/**
 * Builds a full width button
 */
@Composable
fun LandingScreenGradientBtn(
    onClick: () -> Unit,
    text: String,
) {
    val gradient = Brush.horizontalGradient(
        colors = listOf(
            MaterialTheme.colors.primary,
            MaterialTheme.colors.primaryVariant
        )
    )
    Button( // Use Button to build our custom btn
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent // suppress default background color
        ),
        contentPadding = PaddingValues(),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = ButtonDefaults.elevation()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradient)
                .padding(vertical = 12.dp),
        ) {
            Text(
                color = MaterialTheme.colors.onPrimary.copy(alpha = 0.90f),
                text = text.uppercase(Locale.getDefault()),
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "•••",
                color = MaterialTheme.colors.secondary,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterEnd),
                style = MaterialTheme.typography.h6
            )
        }
    }
}

/*@Composable
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
}*/

@Preview(showBackground = true)
@Composable
fun LandingScreenGradientBtnPreview() {
    LandingScreenGradientBtn(onClick = {}, text = "test")
}

/*
@Preview(showBackground = true)
@Composable
fun LandingScreenButtonPreview() {
    LandingScreenButton(onClick = {}, text = "test")
}*/
