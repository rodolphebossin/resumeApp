package com.rodolphebossin.resumeapp

import android.content.res.Resources

/**
 * Created by Rodolphe Bossin on 21/02/2022.
 */

// allows to adopt a value in float to the equivalent value in dp depending on screen density
fun Float.dp(): Float = this * density + 0.5f

val density: Float
    get() = Resources.getSystem().displayMetrics.density