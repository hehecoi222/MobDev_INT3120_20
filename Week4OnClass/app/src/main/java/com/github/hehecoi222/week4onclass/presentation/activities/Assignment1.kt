package com.github.hehecoi222.week4onclass.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.welcomehomer.WelcomeHomerScreen
import com.github.hehecoi222.week4onclass.ui.theme.Week4OnClassTheme

class Assignment1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week4OnClassTheme {
                WelcomeHomerScreen()
            }
        }
    }
}