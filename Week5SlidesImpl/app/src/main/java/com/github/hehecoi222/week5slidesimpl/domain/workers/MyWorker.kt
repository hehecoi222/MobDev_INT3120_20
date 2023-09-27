package com.github.hehecoi222.week5slidesimpl.domain.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.github.hehecoi222.week5slidesimpl.repository.utils.makeStatusNotification

private const val TAG = "MyWorker"
class MyWorker(context : Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        val appContext = this.applicationContext
        makeStatusNotification("My Worker Start", appContext)
        return try {
            Thread.sleep(3000)
            makeStatusNotification("My Worker End", appContext)
            Result.success()
        } catch (throwable : Throwable) {
            makeStatusNotification("My Worker has error", appContext)
            Log.d(TAG, throwable.message ?: "Unknown error")
            Result.failure()
        }
    }

}