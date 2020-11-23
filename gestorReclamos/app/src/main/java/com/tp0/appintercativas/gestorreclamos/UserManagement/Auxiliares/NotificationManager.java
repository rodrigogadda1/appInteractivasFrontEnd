package com.tp0.appintercativas.gestorreclamos.UserManagement.Auxiliares;

import android.app.Application;
import android.app.NotificationChannel;
import android.os.Build;

public class NotificationManager extends Application {
    public static final String CHENNEL_1_ID = "channel1";

    public void CreateNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(CHENNEL_1_ID, "channel 1", android.app.NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("This is channel 1");
            android.app.NotificationManager managerNotification =  getSystemService(android.app.NotificationManager.class);
            managerNotification.createNotificationChannel(channel1);
        }
    }

}
