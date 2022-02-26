package com.rodolphebossin.roboapp.ui.components

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.util.SparseArray
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Mail
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.rodolphebossin.roboapp.R
import com.rodolphebossin.roboapp.ResumeViewModel
import com.rodolphebossin.roboapp.ui.Screens
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
    Surface(
        elevation = 8.dp,
        modifier = Modifier.semantics {
            contentDescription = "ScrollableTabRow"
        },
    ) {
        ScrollableTabRow(
            selectedTabIndex = selectedScreen,
            divider = {}
        ) {
            allScreens.forEachIndexed { index, screen ->
                var clicked by rememberSaveable { mutableStateOf(viewModel.clicked) }
                ScrollableTab(
                    screen = screen,
                    onSelected = {
                        selectedScreen = index
                        viewModel.onScreenChange(selectedScreen)
                        onTabSelected(screen)
                        if (!clicked[index]) clicked[index] = !clicked[index]
                        viewModel.onClickedChange(clicked)
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
            Icon(
                painterResource(
                    id = R.drawable.logorobo_white
                ),
                contentDescription = "logo robo white",
                Modifier
                    .width(80.dp)
                    .padding(start = 16.dp)
            )
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
fun VideoPlayer(url: String) {
    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }
    val playerView = PlayerView(context)
    val playWhenReady = true
    var playbackPosition by rememberSaveable { mutableStateOf(0L) }
    var currentWindow by rememberSaveable { mutableStateOf(0) }

    playerView.player = player
    playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM


    LaunchedEffect(player) {
        object : YouTubeExtractor(context) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                if (ytFiles != null) {
                    val itag = 18 // Tag of video 1080p, debug ytFiles to know it
                    val audioTag = 140 // Tag of m4a audio
                    val videoUrl = ytFiles[itag].url
                    val audioUrl = ytFiles[audioTag].url

                    val audioSource: MediaSource = ProgressiveMediaSource
                        .Factory(DefaultHttpDataSource.Factory())
                        .createMediaSource(MediaItem.fromUri(audioUrl))
                    val videoSource: MediaSource = ProgressiveMediaSource
                        .Factory(DefaultHttpDataSource.Factory())
                        .createMediaSource(MediaItem.fromUri(videoUrl))

                    player.setMediaSource(
                        MergingMediaSource(
                            true, videoSource, audioSource
                        ), true
                    )
                    player.prepare()
                    player.playWhenReady = playWhenReady
                    player.seekTo(currentWindow, playbackPosition)
                    player.repeatMode = Player.REPEAT_MODE_ALL
                }
            }
        }.extract(url)
    }

    // Disposable view that hosts the player
    DisposableEffect(
        AndroidView(
            modifier = Modifier
                .height(IntrinsicSize.Max), // Adjusts view height on configuration change
            factory = {
                playerView
            })
    ) {
        onDispose {
            playbackPosition = player.currentPosition
            currentWindow = player.currentMediaItemIndex
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

