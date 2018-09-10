package com.mycompany.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApplication extends Application {

    private  String TAG = "MyApplication";

    public MyApplication() {
        super();
        Log.i(TAG, " GouZao function");
    }

    @Override
    protected void attachBaseContext(Context base) {
        Log.i(TAG, " attachBaseContext()");
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        Log.i(TAG, " onCreate()");
        super.onCreate();
    }
}
