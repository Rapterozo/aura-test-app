package com.example.auratestapp

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity

/**
 * TODO Total dismissals allowed: 5, Interval between dismissals: Dismissal count * 20 minutes
 */
object NotificationHelper {

    private const val CHANNEL_ID = "1"

    private var notificationId = 1231

    private fun notification(context: Context, boot: Boot) = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle(context.getString(R.string.app_name))
        .setContentText(bodyBuilder(context, boot))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .build()

    fun addMainChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel.
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system. You can't change the importance
            // or other notification behaviors after this.
            val notificationManager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    @SuppressLint("MissingPermission")
    fun publish(context: Context, boot: Boot) = with(NotificationManagerCompat.from(context)) {
        //TODO permissions
//        if (ActivityCompat.checkSelfPermission(
//                context,
//                Manifest.permission.POST_NOTIFICATIONS
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // TODO: Consider calling
//            // ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            // public fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
//            //                                        grantResults: IntArray)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//
//            return@with
//        }
        // notificationId is a unique int for each notification that you must define.
        notify(notificationId, notification(context, boot))
    }

    private fun bodyBuilder(context: Context, boot: Boot) = when (boot) {
        Boot.None -> context.getString(boot.stringRes)
        is Boot.One -> context.getString(boot.stringRes, boot.dateString)
        is Boot.Multiple -> context.getString(boot.stringRes, boot.delta)
    }
}

sealed class Boot(val stringRes: Int) {
    data object None : Boot(R.string.no_boots)
    data class One(val dateString: String) : Boot(R.string.one_boot)
    data class Multiple(val delta: Long) : Boot(R.string.multiple_boots)
}