package com.rodolphebossin.resumeapp.ui

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 */
sealed class Screens(val route: String) {
    object Landing : Screens("landing")
    object Bio : Screens("bio")
}