package com.fatih.dersasistan;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationManager extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {



        String dersadi=intent.getStringExtra("dersadi");
        String dersaciklama=intent.getStringExtra("dersaciklama");



       bildirim(dersadi,dersaciklama,context);


    }



    public void bildirim(String dersadi,String dersaciklama,Context context){
        NotificationCompat.Builder builder;

        android.app.NotificationManager bildirimyoneticisi=(android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent=new Intent(context,SplashScreen.class);

        PendingIntent gidilecekintent=PendingIntent.getActivity(context,1,intent,PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            String kanalId="kanalId";
            String kanalAd="kanalAd";
            String kanalTanım="kanalTanım";
            int kanalOnceligi= android.app.NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel kanal=bildirimyoneticisi.getNotificationChannel(kanalId);

            if (kanal==null){
                kanal=new NotificationChannel(kanalId,kanalAd,kanalOnceligi);
                kanal.setDescription(kanalTanım);
                bildirimyoneticisi.createNotificationChannel(kanal);
            }
            builder=new NotificationCompat.Builder(context,kanalId);
            builder.setContentTitle("Ders Vakti");
            builder.setContentText(dersadi+" : "+dersaciklama);
            builder.setSmallIcon(R.drawable.ic_baseline_menu_book_24);
            builder.setColor(Color.rgb(236,93,93));
            builder.setAutoCancel(true);
            builder.setContentIntent(gidilecekintent);
            builder.setVibrate(new long[]{100,300,300,300});
            builder.setLights(Color.RED, 3000, 3000);


        }
        else {

            builder=new NotificationCompat.Builder(context);
            builder.setContentTitle("Ders Vakti");
            builder.setContentText(dersadi+" : "+dersaciklama);
            builder.setSmallIcon(R.drawable.ic_baseline_menu_book_24);
            builder.setAutoCancel(true);
            builder.setContentIntent(gidilecekintent);
            builder.setPriority(Notification.PRIORITY_HIGH);


        }

        bildirimyoneticisi.notify(1,builder.build());
    }
}
