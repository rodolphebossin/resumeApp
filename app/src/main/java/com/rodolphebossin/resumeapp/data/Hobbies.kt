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
            "https://www.youtube.com/watch?v=RaMj29X6z2o",
            R.drawable.running,
        ),
        Hobby(
            "https://www.youtube.com/watch?v=dY0wbG17bvI",
            R.drawable.surfing,
        ),
        Hobby(
            "https://www.youtube.com/watch?v=NRkWMbOeDmQ",
            R.drawable.swimming,
        ),
        Hobby(
            "https://www.youtube.com/watch?v=U7ozusp6_Ls",
            R.drawable.fishing,
        ),
        Hobby(
            "https://www.youtube.com/watch?v=AZ53E9Y-9Ys",
            R.drawable.musique,
        ),
        Hobby(
            "https://www.youtube.com/watch?v=iUKjuni-6l8",
            R.drawable.yoga
        ),
    )
}