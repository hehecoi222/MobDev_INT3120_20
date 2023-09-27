package com.github.hehecoi222.week5slidesimpl.repository.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.rememberCoroutineScope

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("message")
        val scope = rememberCoroutineScope()
    }
}