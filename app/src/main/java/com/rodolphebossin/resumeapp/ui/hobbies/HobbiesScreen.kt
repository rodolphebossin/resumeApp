package com.rodolphebossin.resumeapp.ui.hobbies

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 */

@ExperimentalFoundationApi
@Composable
fun HobbiesScreen(hobbies: List<Int>) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(16.dp),
        cells = GridCells.Fixed(2)
    ) {
        itemsIndexed(hobbies) { _, item ->
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp),
            ) {
                Image(painter = painterResource(id = item), contentDescription = null)
            }
        }
    }
}


