package com.github.hehecoi222.week5slidesimpl.domain.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.github.hehecoi222.week5slidesimpl.domain.workers.MyWorker

class MyViewModel(application: Application) : ViewModel() {
    private val workerManager = WorkManager.getInstance(application)

    internal fun startWorker() {
        workerManager.enqueue(OneTimeWorkRequest.from(MyWorker::class.java))
    }

}

class MyViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            MyViewModel(this.application) as T
        } else throw IllegalArgumentException("ViewModel Not Found" + modelClass.name.toString())
    }
}