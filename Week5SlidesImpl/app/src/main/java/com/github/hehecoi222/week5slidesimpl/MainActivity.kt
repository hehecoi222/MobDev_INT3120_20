package com.github.hehecoi222.week5slidesimpl

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.github.hehecoi222.week5slidesimpl.presentation.screens.MainScreen
import com.github.hehecoi222.week5slidesimpl.repository.services.MyBroadcastReceiver
import com.github.hehecoi222.week5slidesimpl.ui.theme.Week5SlidesImplTheme

class MainActivity : ComponentActivity() {
    private lateinit var receiver: MyBroadcastReceiver
    private lateinit var intentFilter: IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiver = MyBroadcastReceiver()
        intentFilter = IntentFilter("com.github.hehecoi222.week5slidesimpl.ACTION_MY_EVENT")
        setContent {
            Week5SlidesImplTheme {
                MainScreen()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(receiver, intentFilter)
        } else {
            registerReceiver(receiver, intentFilter, RECEIVER_EXPORTED)
        }
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(receiver, intentFilter)
        } else {
            registerReceiver(receiver, intentFilter, RECEIVER_EXPORTED)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
        Log.d("MainActivity", "onStop unregisterReceiver")
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}