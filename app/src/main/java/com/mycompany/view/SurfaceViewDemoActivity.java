package com.mycompany.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.mycompany.mymaintestactivity.R;

public class SurfaceViewDemoActivity extends Activity {
    Button button;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    Thread thread;
    private final String TAG = "MainActivity.java";
    private boolean isStarted = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surfaceview_demo);
        init();
    }

    private void init() {
        button = (Button) findViewById(R.id.button1);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView1);
        surfaceHolder = surfaceView.getHolder();
        thread = new MyRunnable(this);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (!isStarted) {
                    isStarted = true;
                    thread.start();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    class MyRunnable extends Thread {

        private Context context;
        public MyRunnable(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            if (surfaceHolder == null) {
                Log.i(TAG, "surfaceHolder==null");
                return;
            }
            int i = 0;
            Paint mPaint = new Paint();
            mPaint.setColor(Color.WHITE);// 画笔为绿色
            mPaint.setStrokeWidth(2);// 设置画笔粗细
            mPaint.setTextSize(50);
            while (i < 20) {
                Canvas canvas = surfaceHolder.lockCanvas();
                canvas.drawText("" + i, 100, 50 + 50 * i, mPaint);

                surfaceHolder.unlockCanvasAndPost(canvas);// 解锁画布，提交画好的图像
                i++;

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}
