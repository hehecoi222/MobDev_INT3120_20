package com.github.hehecoi222.week4onclass.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.hehecoi222.week4onclass.presentation.screens.EssentialsOfMobDevScreen

class Assignment2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EssentialsOfMobDevScreen()
        }
    }
}