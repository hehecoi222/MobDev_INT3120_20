package com.github.hehecoi222.week5slidesimpl.presentation.screens

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SubScreen() {
    val context = LocalContext.current
    val intentMessage = (context as Activity).intent?.getStringExtra("message")

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column {
            Text(text = intentMessage.toString(), Modifier.padding(16.dp))
            Button(onClick = {
                context.finish()
            }) {
                Text(text = "Back")
            }
        }
    }
}