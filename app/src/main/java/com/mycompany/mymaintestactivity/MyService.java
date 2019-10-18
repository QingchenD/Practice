package com.mycompany.mymaintestactivity;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.net.BindException;

/**
 * Created by Qingweid on 2016/5/6.
 */
public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Intent activityIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);
        Notification notification = new Notification.Builder(this)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher)
                .setTicker("前台Service启动")
                .setContentTitle("前台Service运行中")
                .setContentText("这是一个正在运行的前台Service")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);

        mediaPlayer = MediaPlayer.create(this, R.raw.caichaji);
        mediaPlayer.setOnCompletionListener(new CompletiondListener());

        Log.d("MyService", "service : onCreate executed");
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","service: onStartCommand executed");

//        Intent i = new Intent();
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        i.setClass(this, SecondActivity.class);
//        startActivity(i);

        //startForeground(0,  null);
        super.onStartCommand(intent, flags, startId);
        if (!mediaPlayer.isPlaying()) {
            // 开始播放
            mediaPlayer.start();
            // 允许循环播放
            //mediaPlayer.setLooping(true);
        } else {
            mediaPlayer.pause();
        }
        return START_STICKY;
    }




    @Override
    public void onDestroy() {
        Log.d("MyService","service:onDestroy executed");
        super.onDestroy();
    }

    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d("MyService", "service:StartDownload executed");
        }

        public int getProgress() {
            Log.d("MyService", "service:getProgress executed");
            return  0;
        }
    }

    class CompletiondListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Log.i("MyService", "Mediaplayer completion!!");
        }
    }
}
