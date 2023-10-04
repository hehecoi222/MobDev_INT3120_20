package com.github.hehecoi222.week5slidesimplstoredata

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.hehecoi222.week5slidesimplstoredata.domain.viewmodel.MainViewModel
import com.github.hehecoi222.week5slidesimplstoredata.presentation.screens.MainScreen
import com.github.hehecoi222.week5slidesimplstoredata.ui.theme.Week5SlidesImplStoreDataTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        setContent {
            Week5SlidesImplStoreDataTheme {
                val viewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                MainScreen(viewModel)
            }
        }
    }
}