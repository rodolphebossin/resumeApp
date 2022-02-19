package com.rodolphebossin.resumeapp.ui.components

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Mail
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.rodolphebossin.resumeapp.R
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
 * Builds a scrollable row of tabs for all screens
 * @param allScreens the list of all Screens
 * @param onTabSelected the callback that gets called when one of the tabs is selected
 * @param currentScreen remember the current Screen selected
 */
@Composable
fun ScrollableTabRow(
    viewModel: ResumeViewModel,
    allScreens: List<Screens>,
    onTabSelected: (Screens) -> Unit,
    currentScreen: Screens
) {
    var selectedScreen by rememberSaveable { mutableStateOf(allScreens.indexOf(currentScreen)) }
    Surface(elevation = 8.dp) {
        ScrollableTabRow(
            selectedTabIndex = selectedScreen,
            divider = {}
        ) {
            allScreens.forEachIndexed { index, screen ->
                ScrollableTab(
                    screen = screen,
                    onSelected = {
                        selectedScreen = index
                        viewModel.onScreenChange(selectedScreen)
                        onTabSelected(screen)
                    },
                    isSelected = currentScreen == screen
                )
            }
        }
    }
}

@Composable
private fun ScrollableTab(
    screen: Screens,
    isSelected: Boolean = false,
    onSelected: () -> Unit,
) {
    Tab(selected = isSelected, onClick = onSelected) {
        Column(
            modifier = Modifier.height(50.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = screen.route.uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

/**
 * Builds the appBar
 * @param onMailClick callBack to automatically send email to my personal address
 * @param onLinkedInClick callBack to redirect to my personal LinkedIn profile
 */
@Composable
fun ResumeTopAppBar(
    onMailClick: () -> Unit,
    onLinkedInClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.height(48.dp),
        title = {
            Text(text = "Personal Resume")
        },
        actions = {
            IconButton(onClick = onLinkedInClick) {
                Icon(painterResource(id = R.drawable.linkedin), contentDescription = null)
                Modifier.width(20.dp)
            }
            IconButton(onClick = onMailClick) {
                Icon(imageVector = Icons.Filled.Mail, contentDescription = null)
            }

        }
    )
}

/**
 * Builds on embedded videoPlayer
 * @param url a string that provides url for the video to be played
 * player is automatically destroyed when UI is destroyed
 */
@Composable
fun VideoPlayer(url: String, viewModel: ResumeViewModel) {
    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }
    val playerView = PlayerView(context)
    val mediaItem = MediaItem.fromUri(url)
    var playbackPosition by rememberSaveable { mutableStateOf(0L)}

    playerView.player = player
    playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
    LaunchedEffect(player) {
        player.prepare()
        player.setMediaItem(mediaItem, playbackPosition)
        player.playWhenReady = true
        player.repeatMode = Player.REPEAT_MODE_ALL
    }

    // Disposable view that hosts the player
    DisposableEffect(
        AndroidView(
            modifier = Modifier
                .height(250.dp),
                // .then(if (player.isPlaying) Modifier.height(250.dp) else Modifier), // adjust player size when video starts playing
            factory = {
                playerView
            })
    ) {
        onDispose {
            playbackPosition = player.currentPosition
            // viewModel.onPositionChanged(playbackPosition)
            player.release() // Release player when view is destroyed
        }
    }
}


/**
 * Launch implicit intent from context
 * @param intent the implicit Intent
 * @param context the context
 * shows toast if no app can handle the intent
 */
fun sendIntent(intent: Intent, context: Context) {
    try {
        ContextCompat.startActivity(context, intent, null)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(
            context,
            "Désolé mais vous ne possédez aucune application capable d'effectuer cette action...",
            Toast.LENGTH_SHORT
        ).show()
    }
}


/**
 * Builds a scrollable row of the chips for all screens
 * @param allScreens the list of all Screens
 * @param onChipSelected the callback that gets called when one of the chips is selected
 * @param currentScreen remember the current Screen selected
 * current scrollState is saved to viewModel and restored on configuration changes
 */
/*@Composable
fun ScrollableTabRow(
    viewModel: ResumeViewModel,
    allScreens: List<Screens>,
    onChipSelected: (Screens) -> Unit,
    currentScreen: Screens
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .selectableGroup()
                .horizontalScroll(scrollState)
                .fillMaxWidth()
                .padding(start = 8.dp),
        ) {
            scope.launch { scrollState.scrollTo(viewModel.screenChipScrollableTabRowScrollPosition) }
            allScreens.forEach { screen ->
                ScrollableTab(
                    screen = screen,
                    onSelected = {
                        onChipSelected(screen) // callBack that will get called on click
                        viewModel.onChangeScreenChipScrollableTabRowScrollPosition(scrollState.value) // update the scrollValue in the viewModel
                    },
                    isSelected = currentScreen == screen,
                )
            }
        }
    }
}*/



/**
 * Builds an chip for a Screen to be displayed in the TabRow
 * @param screen: a Screen
 * @param isSelected a Boolean describing is the chip is selected or not
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

    val chipColor = MaterialTheme.colors.onPrimary
    val textColor = MaterialTheme.colors.primaryVariant
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

