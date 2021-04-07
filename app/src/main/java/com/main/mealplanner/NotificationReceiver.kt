package com.main.mealplanner

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings.Global.getString
import androidx.core.app.NotificationManagerCompat
import com.main.mealplanner.service.NotificationService


class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (context != null && intent != null && intent.action != null) {
            // 1
            val notificationService = NotificationService(context!!)
            notificationService.createNotificationChannel(context!!, NotificationManagerCompat.IMPORTANCE_DEFAULT, false, context.getString(R.string.app_name), "App notification channel.")
            notificationService.createNotification("Test", "test", false)
        }
    }

}