package com.mycompany.mymaintestactivity;


import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;


public class SecondActivity extends Activity {

    private String TAG = "SecondActivity";
    private Button btnStartActivity;
    private Button btnImplicitStartActivity;
    private ProgressBar progressBar;

    ArrayList list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity_layout);
        Log.i(TAG, "mydebug onCreate()");

        btnStartActivity = (Button) findViewById(R.id.btn_start_activity);
        btnImplicitStartActivity = (Button) findViewById(R.id.btn_implicit_start_activity);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        System.out.println("[Mydebug] " + getClass().getSimpleName() + " taskID:" + getTaskId());

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);

        btnStartActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(v);
            }
        });
    }

    public void startActivity( View view) {
        Intent intent = new Intent(this,MainActivity.class); //StartOneActitity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void implicitStartActivity(View view) {
        Intent intent = new Intent("com.mycompany.mymaintestactivity.ACTION_START");
        intent.addCategory("android.intent.category.MY_CATEGORY");
        startActivity(intent);
    }

    public void implicitStartOhterActivity(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }

    public void implicitStartDialActivity(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

    public void setProgressBar(View view) {
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.INVISIBLE);
        } else if (progressBar.getVisibility() == View.INVISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
        }

        int progress = progressBar.getProgress();
        progressBar.setProgress(progress + 10);
    }


    public void OnFinish (View view) {
        finish();
    }

    @Override
    public void finish() {
        Log.i(TAG, "mydebug finish()");
        super.finish();
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
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "mydebug onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "mydebug onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "mydebug onDestroy()");
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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}

class Test1<K> {
    private K instance;

    public Test1(K instance) {
        this.instance = instance;
    }
}

class Test<K, V extends List<?>> {


}
