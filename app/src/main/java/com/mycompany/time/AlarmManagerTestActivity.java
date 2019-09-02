package com.mycompany.time;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mycompany.mymaintestactivity.R;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class AlarmManagerTestActivity extends Activity {

    private String TAG = "AlarmManagerTestActivity";
    private AlarmManager alarmManager ;
    private PendingIntent pi;

    private Timer timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_manager_layout);

        Intent intent = new Intent(AlarmManagerTestActivity.this, ClockActivity.class);
        pi = PendingIntent.getActivity(AlarmManagerTestActivity.this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //设置当前时间
//        Calendar c = Calendar.getInstance();
//        c.setTimeInMillis(System.currentTimeMillis());
//        // 根据用户选择的时间来设置Calendar对象
//        c.set(Calendar.HOUR, 16);
//        c.set(Calendar.MINUTE, 20);
//        c.set(Calendar.SECOND, 0);

        long currentMillisSeconds = System.currentTimeMillis();
        long time = currentMillisSeconds + 30 * 1000;
        // ②设置AlarmManager在Calendar对应的时间启动Activity
        //alarmManager.set(AlarmManager.RTC, time, pi);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 20*1000, pi);
        Log.e(TAG, " currentTime" + currentMillisSeconds + " timer:" + time);   //这里的时间是一个unix时间戳
        // 提示闹钟设置完毕:


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        }, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
