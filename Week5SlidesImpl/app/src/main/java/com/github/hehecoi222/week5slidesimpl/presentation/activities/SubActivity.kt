package com.github.hehecoi222.week5slidesimpl.presentation.activities

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.hehecoi222.week5slidesimpl.presentation.screens.SubScreen
import com.github.hehecoi222.week5slidesimpl.repository.services.MyBroadcastReceiver
import com.github.hehecoi222.week5slidesimpl.ui.theme.Week5SlidesImplTheme

class SubActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val receiver = MyBroadcastReceiver()
//        val intentFilter = IntentFilter("com.github.hehecoi222.week5slidesimpl.ACTION_MY_EVENT")
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
//            registerReceiver(receiver, intentFilter)
//        } else {
//            registerReceiver(receiver, intentFilter, RECEIVER_NOT_EXPORTED)
//        }

        setContent {
            Week5SlidesImplTheme {
                SubScreen()
            }
        }
    }

    override fun finish() {
        val intent = Intent()
        val message = "I have received ${this.intent.getStringExtra("message")}"
        intent.putExtra("return", message)
        this.setResult(RESULT_OK, intent)
        super.finish()
    }
}