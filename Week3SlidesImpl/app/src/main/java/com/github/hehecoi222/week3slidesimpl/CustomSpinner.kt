package com.github.hehecoi222.week3slidesimpl

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSpinner

class CustomSpinner(context: Context, attributeSet: AttributeSet) : AppCompatSpinner(context, attributeSet) {
    interface OnSpinnerEventsListener {
        fun onSpinnerOpenned(spinner: AppCompatSpinner)
        fun onSpinnerClosed(spinner: AppCompatSpinner)
    }

    private lateinit var onSpinnerEventsListener : OnSpinnerEventsListener
    private var openInitiated = false

    override fun performClick(): Boolean {
        openInitiated = true
        if (onSpinnerEventsListener === null) {

        }
        return super.performClick()
    }
}