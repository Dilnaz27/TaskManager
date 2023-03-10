package com.geektech.taskmanager.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.geektech.taskmanager.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class PushNotificationService : FirebaseMessagingService() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.e("di", "onMessageReceived: " + remoteMessage.notification?.title)
        Log.e("di", "onMessageReceived: " + remoteMessage.notification?.body)

        val channel = NotificationChannel(
            CHANNEL_ID,
            "Heads Up Notification",
            NotificationManager.IMPORTANCE_HIGH
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

        val notification = Notification.Builder(this, CHANNEL_ID)
        notification.setSmallIcon(R.mipmap.ic_launcher)
        notification.setContentText(remoteMessage.notification?.body)
        notification.setContentTitle(remoteMessage.notification?.title)
        notification.setAutoCancel(true)

        NotificationManagerCompat.from(this).notify(1, notification.build())
    }

    companion object{
        const val CHANNEL_ID = "channel_task"
    }
}