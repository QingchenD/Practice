package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;

import java.util.Objects;

/**
 * Created by Qingweid on 2016/4/6.
 */
public class ThreadSyncActivity extends Activity {
    private Object testLock = new Object();
    private String test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        synchronized (testLock) {
            test = "123";
        }

//        testLock = null;
        synchronized (testLock) {
            test = "345";
        }
    }

}
