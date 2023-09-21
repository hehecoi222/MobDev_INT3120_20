package com.github.hehecoi222.week4slideimpl.presentation.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.hehecoi222.week4slideimpl.presentation.screens.SubScreen
import com.github.hehecoi222.week4slideimpl.ui.theme.Week4SlideImplTheme

class SubActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week4SlideImplTheme {
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