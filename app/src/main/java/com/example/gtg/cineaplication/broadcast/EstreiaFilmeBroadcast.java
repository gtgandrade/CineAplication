package com.example.gtg.cineaplication.broadcast;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.activity.FilmeActivity;


public class EstreiaFilmeBroadcast extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder notificacaoBuilder = new NotificationCompat.Builder(context);
        Intent intentFilmes = new Intent(context, FilmeActivity.class);

        PendingIntent pendingIntentFilmes = PendingIntent.getActivity(context, 0, intentFilmes, PendingIntent.FLAG_UPDATE_CURRENT);
        notificacaoBuilder.setContentIntent(pendingIntentFilmes);
        notificacaoBuilder.setDefaults(Notification.DEFAULT_ALL);
        notificacaoBuilder.setContentTitle("CineApplication");
        notificacaoBuilder.setSmallIcon(R.drawable.ic_local_movies_black_24dp);
        notificacaoBuilder.setContentText("Veja as estreias da semana.");
        notificacaoBuilder.setAutoCancel(true);
        NotificationManagerCompat notificacaoManager = NotificationManagerCompat.from(context);
        notificacaoManager.notify(1, notificacaoBuilder.build());
    }
}
