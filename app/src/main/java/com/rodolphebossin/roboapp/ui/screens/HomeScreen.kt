package com.rodolphebossin.roboapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rodolphebossin.roboapp.R
import com.rodolphebossin.roboapp.ResumeViewModel
import com.rodolphebossin.roboapp.ui.Screens
import com.rodolphebossin.roboapp.ui.components.AnimatedCircleOutline
import com.rodolphebossin.roboapp.ui.theme.DancingScript
import java.util.*

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 */

@Composable
fun HomeScreen(
    navController: NavController,
    allScreens: List<Screens>,
    viewModel: ResumeViewModel
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
        Box(Modifier.padding(top = 16.dp, bottom = 8.dp)) {
            // animated border
            AnimatedCircleOutline(
                color = MaterialTheme.colors.primary,
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
            fontFamily = DancingScript,
            style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(8.dp)
        )
        // Position
        Text(
            text = "Développeur Android".uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.subtitle2.copy(fontSize = 20.sp, fontWeight = FontWeight.Light),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Creates Buttons for all screens except Home
        for (i in 1 until allScreens.size) {
            HomeScreenButton(navController, i, allScreens[i], viewModel)
        }
    }
}

/**
 * Builds a full width Button
 * Remembers if button already clicked
 * Clicked Buttons show cut corner and medium alpha
 */
@Composable
fun HomeScreenButton(
    navController: NavController,
    index: Int,
    screen: Screens,
    viewModel: ResumeViewModel
) {
    var clicked by rememberSaveable { mutableStateOf(viewModel.clicked) } // remembers if button has already been clicked
    Button(
        onClick = {
            navController.navigate(screen.route)
            if (!clicked[index]) clicked[index] = !clicked[index]
            viewModel.onClickedChange(clicked)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .then(if (clicked[index]) Modifier.alpha(ContentAlpha.medium) else Modifier),
        shape = if (clicked[index]) CutCornerShape(topEnd = 20.dp) else MaterialTheme.shapes.medium,
    ) {
        Text(
            text = screen.route.uppercase(Locale.getDefault()),
            modifier = Modifier.padding(vertical = 6.dp),
            style = MaterialTheme.typography.button
        )
    }
}


