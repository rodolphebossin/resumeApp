package com.rodolphebossin.resumeapp.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rodolphebossin.resumeapp.ResumeViewModel
import com.rodolphebossin.resumeapp.ui.Screens
import kotlinx.coroutines.launch
import java.util.*

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 */

/**
 * Builds a row to display the details of a work experience
 * @param mission a string describing the mission
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

/**
 * Builds a scrollable row of the chips for all screens
 * @param allScreens the list of all Screens
 * @param onChipSelected the callback that gets called when one of the chips is selected
 * @param currentScreen remeber the current Screen selected
 */
@Composable
fun ScrollableTabRow(
    allScreens: List<Screens>,
    onChipSelected: (Screens) -> Unit,
    currentScreen: Screens
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val viewModel: ResumeViewModel = viewModel()
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .selectableGroup()
                .horizontalScroll(scrollState)
                .fillMaxWidth()
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp),
        ) {
            scope.launch { scrollState.scrollTo(viewModel.screenChipScrollableTabRowScrollPosition) }
            allScreens.forEach { screen ->
                ScrollableTabRowChip(
                    screen = screen,
                    onSelected = {
                        onChipSelected(screen) // callBack that will get called on click
                        viewModel.onChangeScreenChipScrollableTabRowScrollPosition(scrollState.value) // update the scrollValue in the viewModel
                                 },
                    isSelected = currentScreen == screen
                )
            }
        }
    }
}

/**
 * Builds an chip for a Screen to be displayed in the TabRow
 * @param screen: a Screen
 * @param isSelected a Boolean discribing is the chip is selected or not
 * @param onSelected the callBack to be called when this chip is selected
 * The chip and text change color when selected
 * color change is animated
 */
@Composable
fun ScrollableTabRowChip(
    screen: Screens,
    isSelected: Boolean = false,
    onSelected: () -> Unit,
) {

    val chipColor = MaterialTheme.colors.primary
    val textColor = MaterialTheme.colors.onPrimary
    val durationMillis = if (isSelected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val chipTintColor by animateColorAsState(
        targetValue = if (isSelected) chipColor else chipColor.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec
    )
    val chipTextColor by animateColorAsState(
        targetValue = if (isSelected) textColor else textColor.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec
    )
    Surface(
        modifier = Modifier.padding(end = 8.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = chipTintColor
    ) {
        Row(
            modifier = Modifier
                .selectable(
                    selected = isSelected,
                    role = Role.Tab,
                    onClick = onSelected,
                )
                .clearAndSetSemantics { contentDescription = screen.route }
        ) {
            Text(
                text = screen.route.uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.body2,
                color = chipTextColor,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

private const val InactiveTabOpacity = 0.60f

private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100