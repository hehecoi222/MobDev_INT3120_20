package com.github.hehecoi222.week5slidesimpl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.hehecoi222.week5slidesimpl.presentation.screens.MainScreen
import com.github.hehecoi222.week5slidesimpl.ui.theme.Week5SlidesImplTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week5SlidesImplTheme {
                MainScreen()
            }
        }
    }
}