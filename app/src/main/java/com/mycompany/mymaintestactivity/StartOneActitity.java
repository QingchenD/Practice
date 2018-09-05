package com.mycompany.mymaintestactivity;

import android.app.Activity;
//import java.lang.instrument.Instrumentation;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Qingweid on 2015/12/10.
 */
public class StartOneActitity extends Activity {

    private final String TAG = "StartOneActitity";
    private TextView tv;
    private Button btn;

    private String[] iTmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "mydebug onCreate()");

        setContentView(R.layout.start_one_activity_layout);
        System.out.println("[Mydebug] " + getClass().getSimpleName() + " taskID:" + getTaskId());
        Button start = (Button) findViewById(R.id.btn_start_second);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(StartOneActitity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        Button btn1 = (Button) findViewById(R.id.btn_start_myself);
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(StartOneActitity.this, StartOneActitity.class);
                startActivity(intent);
            }
        });


        Button btn = (Button) findViewById(R.id.intent_test_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intentSizeTest();
            }
        });

        tv = (TextView) findViewById(R.id.tv_test);


        iTmp = new String[100];
        String str = iTmp.length > 10 ? "Good" : "no";
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "mydebug onStart()");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "mydebug onRestoreInstanceState()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "mydebug onResume()");


        String ssid = "O_12345678";

        byte[] ssidbytes = ssid.getBytes();

        Log.i(TAG, "length:" + ssidbytes.length + " ssidbytes:" + ssidbytes[0] + ssidbytes[1] + ssidbytes[2] + ssidbytes[3]);

        new Thread() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2 * 1000);
                } catch (Exception e) {
                    Log.i("Mydebug", e.getStackTrace().toString());
                }
                //tv.setText("This is a test for no in ui Thread");
            }
        }.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "mydebug onPause()");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

        switch (level) {
            case TRIM_MEMORY_UI_HIDDEN:
                Log.i(TAG, "onTrimMemory() TRIM_MEMORY_UI_HIDDEN");
                break;

            case TRIM_MEMORY_BACKGROUND:
                Log.i(TAG, "onTrimMemory() TRIM_MEMORY_BACKGROUND");
                break;

            case TRIM_MEMORY_COMPLETE:
                Log.i(TAG, "onTrimMemory() TRIM_MEMORY_COMPLETE");
                break;

            case TRIM_MEMORY_MODERATE:
                Log.i(TAG, "onTrimMemory() TRIM_MEMORY_MODERATE");
                break;

            case TRIM_MEMORY_RUNNING_CRITICAL:
                Log.i(TAG, "onTrimMemory() TRIM_MEMORY_RUNNING_CRITICAL");
                break;

            case TRIM_MEMORY_RUNNING_LOW:
                Log.i(TAG, "onTrimMemory() TRIM_MEMORY_RUNNING_LOW");
                break;

            case TRIM_MEMORY_RUNNING_MODERATE:
                Log.i(TAG, "onTrimMemory() TRIM_MEMORY_RUNNING_MODERATE");
                break;

            default:
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "mydebug onStop()");
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "mydebug onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "mydebug onSaveInstanceState()");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG, "mydebug onConfigurationChanged()");
    }

    @Override
    public void finish() {
        super.finish();
        Log.i(TAG, "mydebug finish()");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i(TAG, "mydebug onPostCreate()");
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        Log.i(TAG, "mydebug onPostCreate() with persistentState");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        Log.i(TAG, "mydebug onRestoreInstanceState()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "mydebug onRestart()");
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG, "mydebug onPostResume()");
    }


    @Override
    protected void onNewIntent(Intent intent) {
        Log.i(TAG, "mydebug onNewIntent()");
        super.onNewIntent(intent);
    }

    public void OnFinish(View view) {
        finish();
    }
}
