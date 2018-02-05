package com.example.gtg.cineaplication.broadcast;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.gtg.cineaplication.R;


public class EstreiaFilmeBroadcast extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder nb = new NotificationCompat.Builder(context);
        nb.setDefaults(Notification.DEFAULT_ALL);
        nb.setContentTitle("CineApplication");
        nb.setSmallIcon(R.drawable.ic_local_movies_black_24dp);
        nb.setContentText("Veja as estreias da semana.");
        nb.setAutoCancel(true);
        NotificationManagerCompat nmc = NotificationManagerCompat.from(context);
        nmc.notify(1, nb.build());
    }
}
