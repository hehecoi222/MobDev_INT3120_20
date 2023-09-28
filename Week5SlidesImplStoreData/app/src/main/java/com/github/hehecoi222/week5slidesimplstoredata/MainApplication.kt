package com.github.hehecoi222.week5slidesimplstoredata

import android.app.Application
import com.github.hehecoi222.week5slidesimplstoredata.repository.AppContainer
import com.github.hehecoi222.week5slidesimplstoredata.repository.DefaultAppContainer

class MainApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this.applicationContext)
    }
}