package com.rodolphebossin.resumeapp.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.rodolphebossin.resumeapp.R
import com.rodolphebossin.resumeapp.ResumeViewModel
import com.rodolphebossin.resumeapp.data.Hobby
import com.rodolphebossin.resumeapp.ui.components.VideoPlayer

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 * A screen that displays my hobbies
 */

/**
 * Handles the recyclerView of all HobbiesCards
 * @param list of Hobbies Icons
 */
@Composable
fun HobbiesScreen(hobbies: List<Hobby>, viewModel: ResumeViewModel) {
    val state = rememberLazyListState()
    LazyColumn(
        state = state,
        modifier = Modifier
            .padding(vertical = 8.dp)
            .semantics {
                contentDescription = "Hobbies Screen"
            },
    ) { // LazyColumn takes a list(items) as parameter
        items(items = hobbies) { hobby -> // for each of the items
            HobbyCard(hobby = hobby, viewModel)
        }
    }
}

/**
 * An expandable hobby card
 */
@Composable
private fun HobbyCard(hobby: Hobby, viewModel: ResumeViewModel) {
    Card(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        elevation = 4.dp
    ) {
        HobbyCardContent(hobby, viewModel)
    }
}

/**
 * Hobby card content
 * Shows a video describing the hobby when the card is expanded
 */
@Composable
fun HobbyCardContent(hobby: Hobby, viewModel: ResumeViewModel) {
    var expanded by rememberSaveable { mutableStateOf(false) } // Remembers if column is expanded or not

    // The icon size is animated and changes when the card is expanded
    val small = Size(width = 50f, height = 50f)
    val default = Size(width = 100f, height = 100f)
    val iconSize by animateSizeAsState(
        targetValue = if (expanded) small else default,
        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearOutSlowInEasing
        )
    )
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
                .fillMaxWidth(),
            horizontalArrangement = if (expanded) Arrangement.SpaceBetween else Arrangement.End
        ) {
            Icon(
                painter = painterResource(hobby.icon), contentDescription = null,
                modifier = Modifier
                    .padding(12.dp)
                    .size(iconSize.width.dp, iconSize.height.dp)
                    .then(if (!expanded) Modifier.weight(1f) else Modifier),
            )

            IconButton(onClick = {
                expanded = !expanded
                viewModel.onExpandedChange(expanded)
            }) {
                Icon( // conditional icon that toggles look and content depending on expanded
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) stringResource(R.string.show_less) else stringResource(
                        R.string.show_more
                    )
                )
            }

        }
        if (expanded) { // only shows this if expanded
            Column(
                modifier = Modifier
                    .fillMaxWidth(),

                ) {
                VideoPlayer(hobby.videoUrl, viewModel)
            }

        }
    }

}


