package com.example.week6slidesimpl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week6slidesimpl.presentation.screens.MainScreen
import com.example.week6slidesimpl.presentation.viewmodels.MainViewModel
import com.example.week6slidesimpl.ui.theme.Week6SlidesImplTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week6SlidesImplTheme {
                MainScreen(viewModel(factory = MainViewModel.Factory) as MainViewModel)
            }
        }
    }
}
