package com.ericmatelyan_schoolmobileapp.Utility;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.ericmatelyan_schoolmobileapp.UI.CourseDetailsActivity;

public abstract class AlertManager {

    public static void createAlert(Context context, Long triggerDate, String alertTitle, String alertMessage) {
        Intent intent = new Intent(context, Receiver.class);
        intent.putExtra("AlertTitle", alertTitle);
        intent.putExtra("AlertMessage", alertMessage);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, IdManager.getNextRequestId(), intent, 0);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerDate, pendingIntent);
    }


}
