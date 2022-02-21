package com.rodolphebossin.resumeapp

import android.content.res.Resources

/**
 * Created by Rodolphe Bossin on 21/02/2022.
 */

fun Float.dp(): Float = this * density + 0.5f

val density: Float
    get() = Resources.getSystem().displayMetrics.density