package com.github.hehecoi222.week3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {
    lateinit var radioGroup: RadioGroup
    lateinit var picker: NumberPicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        radioGroup = findViewById(R.id.method_slt)

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = findViewById<RadioButton>(checkedId)
        }

        picker = findViewById(R.id.amount_slt)
        picker.minValue = 0
        picker.maxValue = 1000
        picker.wrapSelectorWheel = true
        picker.setOnValueChangedListener { picker, oldVal, newVal ->

        }


    }
}