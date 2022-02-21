package com.rodolphebossin.resumeapp.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FormatColorFill
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.consumePositionChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.rodolphebossin.resumeapp.ResumeViewModel
import com.rodolphebossin.resumeapp.data.Formation
import kotlin.math.roundToInt

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 * Screen displaying my education
 */

const val ACTION_ITEM_SIZE = 100
const val CARD_OFFSET = 260f // we have 1 icon in the row, so that's 56

const val ANIMATION_DURATION = 500

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun FormationsCardsScreen(viewModel: ResumeViewModel) {
    val cards = viewModel.cards.collectAsState() // list of cards

    val coloredCardIds =
        viewModel.coloredFormationCardIdsList.collectAsState() // list of cards whose icon have been clicked -> color changed

    val revealedCardIds =
        viewModel.revealedFormationCardIdsList.collectAsState() // list of displayed cards
    LazyColumn(
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        itemsIndexed(cards.value) { _, card ->
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max),
                contentAlignment = Alignment.CenterStart
            ) { // Box that contains the card and the action icons underneath
                ActionRow(
                    // Icons displayed after swipe
                    actionIconSize = ACTION_ITEM_SIZE.dp,
                    onClick = { viewModel.onClickedChange(card.id) },

                )
                DraggableCard(
                    // card to be swiped
                    card = card,
                    isRevealed = revealedCardIds.value.contains(card.id),
                    isColored = coloredCardIds.value.contains(card.id),
                    cardOffset = CARD_OFFSET,
                    onExpand = { viewModel.onItemExpanded(card.id) },
                    onCollapse = { viewModel.onItemCollapsed(card.id) },
                )
            }
        }
    }
}

@Composable
fun ActionRow(
    actionIconSize: Dp,
    onClick: () -> Unit,
) {

    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxHeight(),
        backgroundColor = MaterialTheme.colors.error,
        shape = MaterialTheme.shapes.medium.copy(
            topEnd = CornerSize(0.dp),
            bottomEnd = CornerSize(0.dp)
        )
    ) {
        IconButton(
            modifier = Modifier.size(actionIconSize),
            onClick = {
                onClick()
            },
            content = {
                Icon(
                    imageVector = Icons.Filled.FormatColorFill,
                    modifier = Modifier.size(56.dp),
                    tint = MaterialTheme.colors.onError,
                    contentDescription = "delete action"
                )
            }
        )
    }

}

@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun DraggableCard(
    card: Formation,
    isRevealed: Boolean,
    isColored: Boolean,
    cardOffset: Float,
    onExpand: () -> Unit,
    onCollapse: () -> Unit,
) {
    val offsetX = remember { mutableStateOf(0f) } // real time provided offset value
    val transitionState = remember { // transitionState only applies if the card is displayed
        MutableTransitionState(isRevealed).apply {
            targetState = !isRevealed
        }
    }
    val transition = updateTransition(
        transitionState,
        "cardTransition"
    ) // we constantly monitor the transitionState
    val offsetTransition by transition.animateFloat(
        label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = ANIMATION_DURATION) },
        targetValueByState = { if (isRevealed) cardOffset - offsetX.value else -offsetX.value },
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .offset { IntOffset((offsetX.value + offsetTransition).roundToInt(), 0) }
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    val original = Offset(offsetX.value, 0f)
                    val summed = original + Offset(x = dragAmount, y = 0f)
                    val newValue = Offset(x = summed.x.coerceIn(0f, cardOffset), y = 0f)
                    if (newValue.x >= 10) {
                        onExpand()
                        return@detectHorizontalDragGestures
                    } else if (newValue.x <= 0) {
                        onCollapse()
                        return@detectHorizontalDragGestures
                    }
                    change.consumePositionChange()
                    offsetX.value = newValue.x
                }
            },
        backgroundColor = if (isColored) MaterialTheme.colors.secondary else MaterialTheme.colors.surface,
        content = { FormationCardContent(formation = card) }
    )
}

@Composable
private fun FormationCardContent(formation: Formation) {
    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            text = formation.dates,
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = formation.title,
            style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.ExtraBold)
        )
        if (formation.subtitle != null) Text(
            text = formation.subtitle,
            style = MaterialTheme.typography.h6
        )
        Text(
            text = "${formation.school}, ${formation.city}",
            style = MaterialTheme.typography.body1
        )
    }
}