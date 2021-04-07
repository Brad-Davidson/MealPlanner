package com.main.mealplanner.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.main.mealplanner.MainActivity
import com.main.mealplanner.R

class NotificationService{
    private lateinit var appContext: Context
    constructor(context: Context){
        appContext = context
    }
    fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, name: String, description: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // 2
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            // 3
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createNotification(title: String, message: String, autoCancel: Boolean){
        val channelId = "${appContext.packageName}-${appContext.getString(R.string.app_name)}"
        val notificationBuilder = NotificationCompat.Builder(appContext, channelId).apply {
            setSmallIcon(android.R.drawable.ic_dialog_alert) // 3
            setContentTitle(title) // 4
            setContentText(message) // 5
            priority = NotificationCompat.PRIORITY_DEFAULT // 7
            setAutoCancel(autoCancel) // 8

            val intent = Intent(appContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            val pendingIntent = PendingIntent.getActivity(appContext, 0, intent, 0)
            setContentIntent(pendingIntent)
        }
        val notificationManager = NotificationManagerCompat.from(appContext)
        notificationManager.notify(1001, notificationBuilder.build())
    }
}