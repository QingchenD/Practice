package com.mycompany.mymaintestactivity;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Qingweid on 2016/5/6.
 */
public class MyIntentService extends IntentService {

    private String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
        Log.i(TAG, "threadId:" + Thread.currentThread().getId());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //打印当前线程的id
        Log.d("MyIntentService", "Thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestroy executed");
    }
}
