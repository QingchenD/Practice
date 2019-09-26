package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Qingweid on 2016/5/6.
 */
public class MyServiceActivity extends Activity  implements View.OnClickListener{

    private Button startService;
    private Button stopService;
    private Button bindService;
    private Button unbindService;
    private Button startIntentService;
    private Button useServieFunction;

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
            Log.i("MyService","Activity Bind Service Success");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myservice_layout);
        startService = (Button) findViewById(R.id.btn_start_service);
        stopService = (Button) findViewById(R.id.btn_stop_service);
        bindService = (Button) findViewById(R.id.btn_bind_service);
        unbindService = (Button) findViewById(R.id.btn_unbind_service);
        startIntentService = (Button) findViewById(R.id.btn_start_intent_service);
        useServieFunction = (Button) findViewById(R.id.btn_call_service);

        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
        startIntentService.setOnClickListener(this);
        useServieFunction.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                Intent startIntent = new Intent(this, MyService.class);
                startService(startIntent);
                break;

            case R.id.btn_stop_service:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;

            case R.id.btn_bind_service:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE); //绑定服务
                break;

            case R.id.btn_unbind_service:
                unbindService(connection);
                break;

            case R.id.btn_start_intent_service:
                //打印主线程的id
                Log.d("MyService", "Activity:Thread id is " + Thread.currentThread().getId());
                Intent intentService = new Intent(this,MyIntentService.class);
                startService(intentService);
                break;

            case R.id.btn_call_service:
                downloadBinder.startDownload();
                downloadBinder.getProgress();
                break;

            default:
                break;
        }
    }
}
