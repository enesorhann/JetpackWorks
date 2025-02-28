package com.example.notificationwork.Notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.core.app.NotificationCompat
import com.example.notificationwork.R

fun NotificationDef(context: Context) {
    val notification:NotificationCompat.Builder
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (VERSION.SDK_INT>= VERSION_CODES.O){
        val channelId="channelId"
        var channel:NotificationChannel? = notificationManager.getNotificationChannel(channelId)

        if (channel == null){
            channel = NotificationChannel(channelId,"ChannelName",NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        notification = NotificationCompat.Builder(context,channelId)
            .setContentTitle("HEAD")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("title")
            .setAutoCancel(true)


    }else{
        notification = NotificationCompat.Builder(context)
            .setContentTitle("HEAD")
            .setContentTitle("title")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
    }

    notificationManager.notify(1,notification.build())
}