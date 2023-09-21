package com.github.hehecoi222.week4slideimpl

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.hehecoi222.week4slideimpl.presentation.screens.MainScreen
import com.github.hehecoi222.week4slideimpl.ui.theme.Week4SlideImplTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week4SlideImplTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }
}