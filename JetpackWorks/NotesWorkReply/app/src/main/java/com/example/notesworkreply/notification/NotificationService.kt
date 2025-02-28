package com.example.notesworkreply.notification

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.notesworkreply.R
import kotlin.random.Random

class NotificationService(private val context: Context) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    fun showNotification(title:String,content:String){
        val notification = NotificationCompat.Builder(context,"note_channel")
            .setContentTitle(title)
            .setContentText(content)
            .setSmallIcon(R.drawable.baseline_message_24)
            .setAutoCancel(true)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)

        notificationManager.notify(Random.nextInt(),notification.build())
    }
}