package com.rodolphebossin.resumeapp.data

import androidx.compose.runtime.Immutable
import com.rodolphebossin.resumeapp.R

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 */
@Immutable
data class Techno(
    val name: String,
    val icon: Int,
)

object DataTechnos {
    val technosAndroid: List<Techno> = listOf(
        Techno(
            "Android",
            R.drawable.android
        ),
        Techno(
            "Kotlin",
            R.drawable.kotlin_icon
        ),
        Techno(
            "Jetpack Compose",
            R.drawable.jetpack_compose
        )
    )
    val technosJava: List<Techno> = listOf(
        Techno(
            "Java",
            R.drawable.java_logo
        ),
        Techno(
            "Spring",
            R.drawable.spring
        ),
        Techno(
            "Hibernate",
            R.drawable.hibernate
        ),
        Techno(
            "Angular",
            R.drawable.angular_icon_1
        )
    )
    val technosFront: List<Techno> = listOf(
        Techno(
            "Bootstrap",
            R.drawable.bootstrap_logo
        ),
        Techno(
            "Html",
            R.drawable.html5_logo
        ),
        Techno(
            "CSS",
            R.drawable.css
        ),
        Techno(
            "JS",
            R.drawable.javascript_logo
        )
    )
    val technosAdobe: List<Techno> = listOf(
        Techno(
            "Illustrator",
            R.drawable.illustrator
        ),
        Techno(
            "Photoshop",
            R.drawable.photoshop
        ),
        Techno(
            "Indesign",
            R.drawable.indesign
        )
    )
    val technos: List<List<Techno>> = listOf(technosAndroid, technosJava, technosFront, technosAdobe)
}