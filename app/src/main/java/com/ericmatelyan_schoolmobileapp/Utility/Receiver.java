package com.ericmatelyan_schoolmobileapp.Utility;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.ericmatelyan_schoolmobileapp.R;

public class Receiver extends BroadcastReceiver {

    String channelId = "test";
    static int notificationId;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("AlertMessage"), Toast.LENGTH_LONG).show();
        createNotificationChannel(context, channelId);
        Notification notification = new NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_baseline_school_24)
                .setContentText(intent.getStringExtra("AlertMessage"))
                .setContentTitle(intent.getStringExtra("AlertTitle")).build();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId++, notification);
        }

    private void createNotificationChannel(Context context, String CHANNEL_ID) {
        CharSequence name = context.getResources().getString(R.string.channel_name);
        String description = context.getResources().getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        notificationChannel.setDescription(description);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);
    }
}