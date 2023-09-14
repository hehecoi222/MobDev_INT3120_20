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
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.view_pager)

        tabLayout.setupWithViewPager(viewPager)

        val adapter = MainAdapter(supportFragmentManager)

        val fragment1 = Fragment1()
        var bundle = Bundle()
        bundle.putString("title", "Fragment1")
        fragment1.arguments = bundle
        adapter.addFragment(fragment1, "Fragment1")

        val fragment2 = Fragment2()
        bundle = Bundle()
        bundle.putString("title","Fragment2")
        fragment2.arguments = bundle
        adapter.addFragment(fragment2, "Fragment2")

        viewPager.adapter = adapter
    }

    private class MainAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        private val fragmentList = MutableList(0) { _ -> Fragment() }
        private val stringList = MutableList(0) { _ -> ""}

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getPageTitle(position: Int): CharSequence {
            return stringList[position]
        }

        fun addFragment(fragment: Fragment, title: String) {
            fragmentList.add(fragment)
            stringList.add(title)
        }
    }
}