package com.example.notificationsexample

import android.Manifest
import android.app.*
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

const val PENDING_REQUEST=101
const val YES_EXTRA_KEY="YES_EXTRA_KEY"
const val NOTIFICATION_ID=102
class NotificationsViewModel(app: Application): AndroidViewModel(app) {
    private val context:Context = app

    init {
        createNotificationChannel()
    }

    fun sendNotification(){


        var builder = NotificationCompat.Builder(context, context.getString(R.string.CHANNEL_ID))
        builder.setContentTitle("My notification")
        builder.setContentText("Notification text that fits in one line...")

        builder.setStyle(NotificationCompat.BigTextStyle()
            .bigText("Much longer text that cannot fit one line..."))

        builder.priority = NotificationCompat.PRIORITY_DEFAULT

        builder.setSmallIcon(R.drawable.ic_notification)

        builder.color = Color.RED

        builder.setVibrate(longArrayOf(1000, 1000, 1000, 1000))

        builder.setLights(Color.MAGENTA, 1000, 1000)

        builder.setContentIntent(makePendingIntent())

        builder.addAction(R.drawable.ic_yes, context.getString(R.string.yes), makePendingIntentYes())
        builder.addAction(R.drawable.ic_no, context.getString(R.string.no), makePendingIntentNo())



        builder.setAutoCancel(true)

        val notificationManagerCompat = NotificationManagerCompat.from(context)

        //Launch the notification
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //TODO: RequestPermission
            return
        }else{
            //If we're granted then launch the notification
            notificationManagerCompat.notify(NOTIFICATION_ID , builder.build())
        }



    }


    private fun makePendingIntentYes():PendingIntent{
    //That is the real intent
        val intent = Intent(context, YesActivity::class.java).apply {
            putExtra(YES_EXTRA_KEY, "This text goes to Yes Activity")
        }


        //This makes the stack builder with the parent activity (optional)
        //If we don't set it when we press back button the app will exit
        val stackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(YesActivity::class.java)
        stackBuilder.addNextIntent(intent)

        return stackBuilder.getPendingIntent(PENDING_REQUEST,
            PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun makePendingIntent(): PendingIntent{
        val intent = Intent(context, NotificationActivity::class.java)

        return PendingIntent.getActivity(context, PENDING_REQUEST, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun makePendingIntentNo():PendingIntent{
        //This activity is only accessed by notifications, and what we want later is to close the app
        //So system creates a new task just for this activity instead of adding it to the stack
        val intent = Intent(context, NoActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }


        return PendingIntent.getActivity(context, 0, intent, 0)
    }

    private fun createNotificationChannel() {
        //Notifications channels are just available from Oreo version and above.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Features of channel
            val notificationChannel = NotificationChannel(
                context.getString(R.string.CHANNEL_ID), //Channel ID
                context.getString(R.string.CHANNEL_NAME),//Channel Name
                NotificationManager.IMPORTANCE_DEFAULT) //Priority

            notificationChannel.description = context.getString(R.string.CHANNEL_DESCRIPTION)

            //Notification is visible on the App's icon
            notificationChannel.setShowBadge(true)

            //We need the notification Manager to register the notification channel
            val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            //Creates the notification
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

}