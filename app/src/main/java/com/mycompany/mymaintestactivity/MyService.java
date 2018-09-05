package com.mycompany.mymaintestactivity;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.net.BindException;

/**
 * Created by Qingweid on 2016/5/6.
 */
public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Notification notification = new Notification(R.drawable.bells_wavfile,"Notification comes", System.currentTimeMillis());
        Intent notificationIntent = new Intent(this,MyServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
//        notification.setLatestEventInfo(this,"This is title","This is content", pendingIntent);
        //startForeground(1, notification);

        Log.d("MyService", "onCreate executed");
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService","onStartCommand executed");

        Intent i = new Intent();
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setClass(this, SecondActivity.class);
        startActivity(i);

        startForeground(0,  null);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Log.d("MyService","onDestroy executed");
        super.onDestroy();
    }

    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d("Myservice", "StartDownload executed");
        }

        public int getProgress() {
            Log.d("Myservice", "getProgress executed");
            return  0;
        }
    }
}
