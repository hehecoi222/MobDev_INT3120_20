package com.github.hehecoi222.week5slidesimpl.repository.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.work.WorkManager
import com.github.hehecoi222.week5slidesimpl.repository.utils.makeStatusNotification

private const val TAG = "MyBroadcastReceiver"
class MyBroadcastReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive: Received")
        val message = intent?.getStringExtra("message")
        makeStatusNotification(message.toString(), context!!)
    }
}