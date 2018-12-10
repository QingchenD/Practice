package com.mycompany.mymaintestactivity;

import android.app.Activity;
//import java.lang.instrument.Instrumentation;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Debug;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mycompany.eventbus.EventBusTestActivity;
import com.mycompany.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

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
        final Button start = (Button) findViewById(R.id.btn_start_second);
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
                Bundle bundle = new Bundle();
                bundle.putString("one", "Start me again!!");
                intent.putExtras(bundle);
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

        Button btnEventbus = (Button) findViewById(R.id.btn_start_event_bus);
        btnEventbus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(StartOneActitity.this, EventBusTestActivity.class);
                startActivity(intent);
            }
        });

        tv = (TextView) findViewById(R.id.tv_test);


        iTmp = new String[100];
        String str = iTmp.length > 10 ? "Good" : "no";

        Debug.startMethodTracing();
        EventBus.getDefault().register(this);
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
        EventBus.getDefault().unregister(this);
        Debug.stopMethodTracing();
    }

    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onEvent (MessageEvent messageEvent) {
        tv.setText(messageEvent.getMessage());
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

        Bundle data = intent.getExtras();
        if (data != null) {
            Log.i(TAG, "onNewIntent()  intent Data:" + data.getString("one"));
            tv.setText(data.getString("one"));
        }

        Intent tmpIntent = getIntent();
        Bundle tmpData = tmpIntent.getExtras();
        if (tmpData != null) {
            Log.i(TAG, "onNewIntent()  intent Data:" + tmpData.getString("one"));
            tv.setText(tmpData.getString("one"));
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.i(TAG, "onWindowFocusChanged()  hasFocus:" + hasFocus);
        super.onWindowFocusChanged(hasFocus);
    }

    public void OnFinish(View view) {
        finish();
    }


    private void myTest() {
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("hello");
        arrayList.add("how are you");
        arrayList.add("I'm ok");

        Iterator<String> iterator = arrayList.iterator();
        boolean shouldDelete=true;
        while (iterator.hasNext()){
            String string=iterator.next();
            if(shouldDelete){
                shouldDelete=!shouldDelete;
                arrayList.remove(2);
                iterator.remove();
            }

        }
    }
}
