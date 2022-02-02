package com.rodolphebossin.resumeapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rodolphebossin.resumeapp.data.DataTechnos
import com.rodolphebossin.resumeapp.data.Techno

/**
 * Created by Rodolphe Bossin on 01/02/2022.
 */

@Composable
fun TechnosScreen(technosList: List<List<Techno>>) {
    LazyColumn(modifier = Modifier.padding(12.dp)) {
        items(items = DataTechnos.technos) {
            TechnosRow(it)
        }
    }
}

@Composable
fun TechnosRow(technos: List<Techno>) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(technos) { item ->
            Card(
                elevation = 6.dp,
                modifier = Modifier
                    .padding(12.dp)
                    .size(150.dp)
                /*backgroundColor = Color(
                    red = Random.nextInt(0, 255),
                    green = Random.nextInt(0, 255),
                    blue = Random.nextInt(0, 255)
                )*/
            ) {
                Image(
                    painter = painterResource(item.icon),
                    contentDescription = null,
                    modifier = Modifier.padding(24.dp)
                )
            }
        }
    }
}




