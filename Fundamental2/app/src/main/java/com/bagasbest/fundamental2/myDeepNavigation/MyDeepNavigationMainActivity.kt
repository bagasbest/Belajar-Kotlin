package com.bagasbest.fundamental2.myDeepNavigation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.bagasbest.fundamental2.R
import com.bagasbest.fundamental2.databinding.ActivityMainBinding
import com.bagasbest.fundamental2.databinding.ActivityMyDeepNavigationMainBinding

class MyDeepNavigationMainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyDeepNavigationMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyDeepNavigationMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showNotification(
            this,
            getString(R.string.notification_title),
            getString(R.string.notification_message),
            110
        )

        binding.btnOpenDetail.setOnClickListener {
            val detailIntent = Intent(this, MyDeepLinkDetailActivity::class.java)
            detailIntent.putExtra(
                MyDeepLinkDetailActivity.EXTRA_TITLE,
                getString(R.string.detail_title)
            )
            detailIntent.putExtra(
                MyDeepLinkDetailActivity.EXTRA_MESSAGE,
                getString(R.string.detail_message)
            )
            startActivity(detailIntent)
        }
    }

    private fun showNotification(
        context: Context,
        title: String,
        message: String,
        notifId: Int
    ) {
        val CHANNEL_ID = "channel_1"
        val CHANNEL_NAME = "Navigation channel"

        val notifDetailIntent = Intent(this, MyDeepLinkDetailActivity::class.java)
        notifDetailIntent.putExtra(MyDeepLinkDetailActivity.EXTRA_TITLE, title)
        notifDetailIntent.putExtra(MyDeepLinkDetailActivity.EXTRA_MESSAGE, message)

        val pendingIntent = TaskStackBuilder.create(this)
            .addParentStack(MyDeepLinkDetailActivity::class.java)
            .addNextIntent(notifDetailIntent)
            .getPendingIntent(110, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManagerCompat =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alatmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_baseline_replay_24)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.black))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alatmSound)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channel.enableVibration(true)
            channel.vibrationPattern = (longArrayOf(1000,1000,1000,1000,1000))

            builder.setChannelId(CHANNEL_ID)
            notificationManagerCompat.createNotificationChannel(channel)
        }

        val notification = builder.build()
        notificationManagerCompat.notify(notifId, notification)
    }
}