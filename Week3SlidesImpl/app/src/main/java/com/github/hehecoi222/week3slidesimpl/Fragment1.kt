package com.github.hehecoi222.week3slidesimpl

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.GridView
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment

class Fragment1 : Fragment() {
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
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val context = requireContext()
        val view = inflater.inflate(R.layout.fragment1, container, false)

        val adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, mobileArray)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        listView = view.findViewById(R.id.mobile_list)
        gridView = view.findViewById(R.id.mobile_grid)
        textView = view.findViewById(R.id.text_selection)
        spinner = view.findViewById(R.id.mobile_spinner)
        autoCompleteTextView = view.findViewById(R.id.mobile_autocomplete)
        pickDateBtn = view.findViewById(R.id.pick_date_btn)
        pickTimeBtn = view.findViewById(R.id.pick_time_btn)

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
                context,
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
                context, { _, hourOfDay, minute ->
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false
            ).show()
        }

        listView.adapter = adapter
        gridView.adapter = adapter
        spinner.adapter = adapter
        autoCompleteTextView.setAdapter(adapter)
        return view
    }
}