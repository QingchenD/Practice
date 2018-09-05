package com.mycompany.surface;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewTestActivty extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    class MyView extends SurfaceView implements SurfaceHolder.Callback {

        private SurfaceHolder holder;
        private MyThread myThread;

        public MyView(Context context) {
            super(context);
            holder = this.getHolder();
            holder.addCallback(this);
            myThread = new MyThread(holder);//创建一个绘图线程
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            myThread.isRun = true;
            myThread.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            myThread.isRun = false;
        }
    }


    class MyThread extends Thread {
        private SurfaceHolder holder;
        public boolean isRun;

        public MyThread(SurfaceHolder holder) {
            this.holder = holder;
            isRun = true;
        }

        @Override
        public void run() {
            int count = 0;
            while (isRun) {
                Canvas c = null;
                try {
                    c = holder.lockCanvas();
                    c.drawColor(Color.BLACK);
                    Paint p = new Paint();
                    p.setColor(Color.WHITE);
                    Rect r = new Rect(100, 50, 300, 250);
                    c.drawRect(r,p);

                    p.setTextSize(24);
                    c.drawText("这是第" + (count++) + "秒", 100 ,310, p );
                    Thread.sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if ( c != null) {
                        holder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }

}
