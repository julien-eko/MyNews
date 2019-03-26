package com.darcos.julie.mynews.Utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.darcos.julie.mynews.Activities.NotificationsActivity;
import com.darcos.julie.mynews.Models.Search.Search;
import com.darcos.julie.mynews.R;

import java.util.Calendar;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static android.content.Context.MODE_PRIVATE;

public class MyAlarmReceiver extends BroadcastReceiver {

    private Disposable disposable;
    private String querySearch;
    private String newsDesk;
    private String notificationText;
    private Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context=context;
        this.querySearch=intent.getStringExtra("queryShearch");
        this.newsDesk=intent.getStringExtra("newsDesk");
        this.executeHttpRequestWithRetrofit();




        //Toast.makeText(context, intent.getStringExtra("queryShearch"), Toast.LENGTH_SHORT).show();

    }

    private void executeHttpRequestWithRetrofit() {

        this.disposable = TimesStreams.streamSearch(querySearch,newsDesk,dateToday(), dateToday()).subscribeWith(new DisposableObserver<Search>() {
            @Override
            public void onNext(Search articles) {
                int i = articles.getResponse().getMeta().getHits();

                notificationText=Integer.toString(i)+" articles have been published today";


            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
                createNotificationChannel();

            }
        });
    }



    public String dateToday() {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        String y = Integer.toString(year);
        String m = Integer.toString(month + 1);
        String d = Integer.toString(day);

        if (d.length() == 1) {
            d = "0" + d;
        }
        if (m.length() == 1) {
            m = "0" + m;
        }

        return (y+m+d);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel";
            String description = "description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("channel", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager =  context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(context,"channel")
                    .setSmallIcon(R.drawable.news)
                    .setContentText(notificationText)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build();
            notificationManager.notify(1,notification);
        }
        else{
            Notification notification = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.news)
                    .setContentText(notificationText)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build();

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, notification);
        }
    }
}
