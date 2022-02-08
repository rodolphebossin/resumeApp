package com.rodolphebossin.resumeapp.data

import androidx.compose.runtime.Immutable
import com.rodolphebossin.resumeapp.R

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 */

@Immutable
data class Hobby(
    val videoUrl: String,
    val icon: Int,
)

object DataHobbies {
    val hobbies: List<Hobby> = listOf(
        Hobby(
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            R.drawable.running,
        ),
        Hobby(
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            R.drawable.surfing,
        ),
        Hobby(
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            R.drawable.swimming,
        ),
        Hobby(
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            R.drawable.fishing,
        ),
        Hobby(
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            R.drawable.musique,
        ),
        Hobby(
            "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
            R.drawable.yoga
        ),
    )
}