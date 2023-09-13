package com.github.hehecoi222.week3slidesimpl

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.GridView
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val mobileArray = listOf(
        "Android",
        "IPhone",
        "WindowsMobile",
        "Blackberry",
        "WebOS",
        "Ubuntu",
        "Windows7",
        "Max OS X"
    )
    private lateinit var listView: ListView
    private lateinit var gridView: GridView
    private lateinit var textView: TextView
    private lateinit var spinner: Spinner
    private lateinit var autoCompleteTextView: AutoCompleteTextView
    private lateinit var pickDateBtn: Button
    private lateinit var pickTimeBtn: Button
    private val calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mobileArray)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        listView = findViewById(R.id.mobile_list)
        gridView = findViewById(R.id.mobile_grid)
        textView = findViewById(R.id.text_selection)
        spinner = findViewById(R.id.mobile_spinner)
        autoCompleteTextView = findViewById(R.id.mobile_autocomplete)
        pickDateBtn = findViewById(R.id.pick_date_btn)
        pickTimeBtn = findViewById(R.id.pick_time_btn)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                textView.text = ""
            }

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                textView.text = mobileArray[position]
            }
        }
        autoCompleteTextView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        pickDateBtn.setOnClickListener {
            DatePickerDialog(
                this@MainActivity,
                { _, year, month, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        pickTimeBtn.setOnClickListener {
            TimePickerDialog(
                this@MainActivity, { view, hourOfDay, minute ->
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false
            ).show()
        }

        listView.adapter = adapter
        gridView.adapter = adapter
        spinner.adapter = adapter
        autoCompleteTextView.setAdapter(adapter)
    }
}