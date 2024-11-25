package com.ssafy.rentalfit.data.remote

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ssafy.rentalfit.activity.MainActivity

private const val TAG = "MyFirebaseMsgSvc_싸피"

class MyFirebaseMessageService : FirebaseMessagingService() {
    // 새로운 토큰이 생성될 때 마다 해당 콜백이 호출된다.
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "onNewToken: $token")
        // 새로운 토큰 수신 시 서버로 전송
        MainActivity.uploadToken(token)
    }

    // Foreground에서 Push Service를 받기 위해 Notification 설정
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Log.d(TAG, "onMessageReceived: ${remoteMessage.notification}")

        remoteMessage.notification?.let { message ->
            Log.d(TAG, "onMessageReceived: $message")
            val messageTitle = message.title
            val messageContent = message.body

            val mainIntent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }

            val mainPendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, mainIntent, PendingIntent.FLAG_IMMUTABLE)

            val builder1 = NotificationCompat.Builder(this, MainActivity.channel_id)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(messageTitle)
                .setContentText(messageContent)
                .setAutoCancel(true)
                .setContentIntent(mainPendingIntent)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)      // head up message

            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(101, builder1.build())

        }


    }


}