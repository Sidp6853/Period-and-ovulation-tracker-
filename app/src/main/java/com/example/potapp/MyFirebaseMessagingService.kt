package com.example.potapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import android.widget.RemoteViews.RemoteView
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


const val channelId = "notification channel"
const val channelname = "com.example.potapp"
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if(remoteMessage.notification != null){
            remoteMessage.notification!!.title?.let { remoteMessage.notification!!.body?.let { it1 ->
                generaeNotification(it,
                    it1
                )
            }
            }
        }
    }

    fun getRemoteView(title: String , message: String): RemoteViews {

        val remoteView = RemoteViews("com.example.potapp" , R.layout.notification)
        remoteView.setTextViewText(R.id.title , title)
        remoteView.setTextViewText(R.id.desc , message)
        remoteView.setImageViewResource(R.id.app_logo , R.drawable.cramps)
        return remoteView

    }

    fun generaeNotification(title: String , message: String ){
        val intent = Intent(this, Cycle_Tracker::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this , 0 , intent , PendingIntent.FLAG_ONE_SHOT)
        var builder: NotificationCompat.Builder = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.cramps)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000 , 1000 , 1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        builder = builder.setContent(getRemoteView(title , message))

        val notificationManger = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannel = NotificationChannel(channelId , channelname, NotificationManager.IMPORTANCE_HIGH)
            notificationManger.createNotificationChannel(notificationChannel)

        }
        notificationManger.notify(0 , builder.build())
    }

}