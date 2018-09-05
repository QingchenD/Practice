package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created by Qingweid on 2016/6/27.
 */
public class HandlerTestActivity extends Activity {
    private Button buttonstart;
    private Button buttonstart2;
    private Button buttonremove;
    private Button buttonremove2;

    private Handler handler = new Handler();

    private int count = 0;

    private Runnable runnableRef = new Runnable() {
        public void run() {
            Log.i("2", Thread.currentThread().getName());
            count++;
            Log.i("!", "count=" + count);
            handler.postDelayed(runnableRef, 1000);
        }
    };


    private Runnable runnableRef2 = new Runnable() {
        public void run() {
            Log.i("2", Thread.currentThread().getName());
            count++;
            Log.i("!", "count=" + count);
            handler.postDelayed(runnableRef2, 1000);
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_test_layout);

        Log.i("1", Thread.currentThread().getName());

        buttonstart = (Button) this.findViewById(R.id.btn_thread_start);
        buttonstart2 = (Button) this.findViewById(R.id.btn_thread_start_2);
        buttonremove = (Button) this.findViewById(R.id.btn_remove_callback);
        buttonremove2 = (Button) this.findViewById(R.id.btn_remove_callback_2);

        buttonstart.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Thread thread = new Thread(runnableRef);
                thread.start();
                Log.v("!!!!!!!!!!!!!", "end");
            }
        });

        buttonstart2.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                Thread thread = new Thread(runnableRef2);
                thread.start();
                Log.v("!!!!!!!!!!!!!", "end");
            }
        });

        buttonremove.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                handler.removeCallbacks(runnableRef);
            }
        });

        buttonremove2.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                handler.removeCallbacks(runnableRef2);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        buttonremove.removeCallbacks(runnableRef);
        buttonremove.removeCallbacks(runnableRef2);
    }
}
