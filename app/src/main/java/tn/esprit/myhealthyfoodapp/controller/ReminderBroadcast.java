package tn.esprit.myhealthyfoodapp.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import tn.esprit.myhealthyfoodapp.R;

public class ReminderBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = "It's time!! drink a glass of water!!";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context, "water_notification")
        .setSmallIcon(R.drawable.ic_alert)
        .setContentTitle("New Notification")
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(200, builder.build());
    }
}
