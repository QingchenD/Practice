package com.mycompany.time;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;


public class ClockActivity extends Activity {

    private String TAG = "ClockActivity";
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_clock);

        Log.i(TAG, "onCreate() show Dialog");

        //创建一个闹钟提醒的对话框,点击确定关闭铃声与页面
        new AlertDialog.Builder(ClockActivity.this).setTitle("闹钟").setMessage("小猪小猪快起床~")
                .setPositiveButton("关闭闹铃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ClockActivity.this.finish();
                    }
                }).show();

    }
}
