package com.mycompany.launchmodeTest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mycompany.mymaintestactivity.R;

/**
 * Created by Qingweid on 2018/3/30.
 */

public class LaunchModeBasicActivity extends Activity implements View.OnClickListener {
    private final String TAG = "LaunchModeBasicActivity";

    private TextView titleText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_mode_layout);
        titleText = (TextView) findViewById(R.id.launch_title);

        Button btn = (Button) findViewById(R.id.launch_standard);
        btn.setOnClickListener(this);

        btn = (Button) findViewById(R.id.launch_single_top);
        btn.setOnClickListener(this);

        btn = (Button) findViewById(R.id.launch_single_task);
        btn.setOnClickListener(this);

        btn = (Button) findViewById(R.id.launch_single_instance);
        btn.setOnClickListener(this);

        btn = (Button) findViewById(R.id.launch_application_start);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.launch_standard:
                launchStandardActivity();
                break;

            case R.id.launch_single_top:
                launchSingleTopActivity();
                break;

            case R.id.launch_single_task:
                launchSingleTaskActivity();
                break;

            case R.id.launch_single_instance:
                launchSingleInstanceActivity();
                break;

            case R.id.launch_application_start:
                applicationLaunchActivity();
                break;
        }
    }

    public void setTitle(String title) {
        titleText.setText(title);
    }

    void launchStandardActivity() {
        Intent intent = new Intent();
        intent.setClass(this, StandardActivity.class);
        Log.i(TAG, " launchStandardActivity() flag:" + intent.getFlags());
        startActivity(intent);
    }

    void launchSingleTopActivity() {
        Intent intent = new Intent();
        intent.setClass(this, SingleTopActivity.class);
        Log.i(TAG, " launchSingleTopActivity() flag:" + intent.getFlags());
        startActivity(intent);
    }

    void launchSingleTaskActivity() {
        Intent intent = new Intent();
        intent.setClass(this, SingleTaskActivity.class);
        Log.i(TAG, " launchSingleTaskActivity() flag:" + intent.getFlags());
        startActivity(intent);
    }

    void launchSingleInstanceActivity() {
        Intent intent = new Intent();
        intent.setClass(this, SingleInstanceActivity.class);
        Log.i(TAG, " launchSingleInstanceActivity() flag:" + intent.getFlags());
        startActivity(intent);
    }

    void applicationLaunchActivity() {
        Context context = this.getApplicationContext();
        Intent intent = new Intent();
        intent.setClass(this,ApplicationStartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.i(TAG, " applicationLaunchActivity() flag:" + intent.getFlags());
        context.startActivity(intent);
    }
}
