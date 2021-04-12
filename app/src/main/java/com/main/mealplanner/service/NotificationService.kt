package com.main.mealplanner.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.main.mealplanner.MainActivity
import com.main.mealplanner.R
import java.util.*

class NotificationService (var context: Context, var params: WorkerParameters) : Worker(context, params){
    override fun doWork(): Result {
        val data = params.inputData
        val title = "Title"
        val body = data.getString("body")

        if (body != null) {
            TriggerNotification(context, title, body)
        }

        return Result.success()
    }

}

class TriggerNotification(context: Context, title: String, body: String){
    init{
        sendNotification(context, title, body)
    }

    private fun createNotificationChannel(context: Context, name: String, description: String): String {
        val chanelId = UUID.randomUUID().toString()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(chanelId, name, importance)
            channel.enableLights(true)
            channel.enableVibration(true)
            channel.description = description
            channel.lightColor = Color.BLUE
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }

        return chanelId
    }

    private fun sendNotification(context: Context, title: String, body: String) {

        val notificationManager = NotificationManagerCompat.from(context)
        val mBuilder = NotificationCompat.Builder(context, createNotificationChannel(context, title, body))
        val notificationId = (System.currentTimeMillis() and 0xfffffff).toInt()

        mBuilder.setDefaults(Notification.DEFAULT_ALL)
                .setTicker("MealPlanner")
                .setContentTitle(title)
                .setContentText(body)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.common_full_open_on_phone)
                .setContentInfo("Content Info")
                .setAutoCancel(true)

        notificationManager.notify(notificationId, mBuilder.build())
    }

}