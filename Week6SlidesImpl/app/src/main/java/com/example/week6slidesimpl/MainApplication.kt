package com.example.week6slidesimpl

import android.app.Application
import com.example.week6slidesimpl.repository.AppContainer
import com.example.week6slidesimpl.repository.DefaultAppContainer

class MainApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this.applicationContext)
    }
}