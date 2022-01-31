package com.rodolphebossin.resumeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.rodolphebossin.resumeapp.data.DataExperiences
import com.rodolphebossin.resumeapp.ui.parcours.WorkExperiences
import com.rodolphebossin.resumeapp.ui.theme.ResumeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResumeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    WorkExperiences(DataExperiences.workExperiences)
                }
            }
        }
    }
}

