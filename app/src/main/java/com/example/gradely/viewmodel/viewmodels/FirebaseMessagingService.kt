package com.example.gradely.viewmodel.viewmodels

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import com.example.gradely.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {
            showNotification(remoteMessage)
        }

        remoteMessage.notification?.let {
            showNotification(remoteMessage)
        }
    }
    private fun showNotification(remoteMessage: RemoteMessage) {
        val data = remoteMessage.data

        val title = data["title"] ?: remoteMessage.notification?.title ?: "Title"
        val message = data["body"] ?: remoteMessage.notification?.body ?: "Message"

        val channelId = "grades_channel"
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            "Academic Updates",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        manager.createNotificationChannel(channel)

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)
            .build()

        manager.notify(0, notification)
    }
}