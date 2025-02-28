package com.example.notificationwork.Notification

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkerNotification(context: Context,workerParameters: WorkerParameters):Worker(context,workerParameters) {
    override fun doWork(): Result {
        NotificationDef(applicationContext)
        return  Result.success()
    }

}