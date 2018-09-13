package com.mycompany.threadLocal;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTestActivity extends Activity {

    private String TAG = "ThreadLocalTestActivity";

    private List<String> myStrings = new ArrayList<>();

    private MyThreadLocal myThreadLocal ;
    private ThreadLocal<List<String>> threadLocal = new ThreadLocal<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init1();
        init2();
    }

    public void init1() {

        myStrings.add("Main");
        threadLocal.set(myStrings);

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> params = new ArrayList<>(3);
                params.add("张三");
                params.add("李四");
                params.add("王五");
                threadLocal.set(params);
                Log.i(TAG, Thread.currentThread().getName());

                List<String> strings = threadLocal.get();
                for(String value : strings) {
                    Log.i(TAG, value);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    List<String> params = new ArrayList<>(2);
                    params.add("Chinese");
                    params.add("English");
                    threadLocal.set(params);
                    Log.i(TAG, Thread.currentThread().getName());

                    List<String> strings = threadLocal.get();
                    for(String value : strings) {
                        Log.i(TAG, value);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
       ).start();
    }

    private void init2() {
        //TimeUnit.SECONDS.sleep(2);
        myThreadLocal = new MyThreadLocal();
        Log.i(TAG, " init2() " + Thread.currentThread().getName() + " default:" + myThreadLocal.get());
    }


    class MyThreadLocal extends  ThreadLocal {

        @Override
        protected String initialValue() {
            return "nihao";
        }
    }

}
