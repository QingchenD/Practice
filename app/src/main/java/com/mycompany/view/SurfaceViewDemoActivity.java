package com.mycompany.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mycompany.mymaintestactivity.R;

import java.io.ByteArrayOutputStream;

public class SurfaceViewDemoActivity extends Activity {
    Button button;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    Thread thread;
    private final String TAG = "MainActivity.java";
    private boolean isStarted = false;
    private ImageView imageView;

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

        imageView = findViewById(R.id.image_alpha_test);
    }

    @Override
    protected void onResume() {
        super.onResume();
        alpha();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void alpha() {
        Bitmap cookie = BitmapFactory.decodeResource(getResources(), R.drawable.cookie); // background
        Bitmap candle = BitmapFactory.decodeResource(getResources(), R.drawable.candle); // foreground

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        cookie.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] cookieData = baos.toByteArray();
//
//        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
//        candle.compress(Bitmap.CompressFormat.PNG, 100, baos1);
//        byte[] candleData = baos1.toByteArray();
//
//        byte[]  alphaData = new byte[cookieData.length];
//        for (int i = 0; i < cookieData.length; i++) {
//            if (i < candleData.length)
//                alphaData[i] = (byte)(cookieData[i] * 0.2 + candleData[i] * 0.8);
//        }
//        Bitmap alphaBitmap = BitmapFactory.decodeByteArray(alphaData, 0, alphaData.length);

        Bitmap alphaBitmap = Bitmap
                .createBitmap(cookie.getWidth(), cookie.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(alphaBitmap);
        Paint paint = new Paint();
        paint.setAlpha(55);
        canvas.drawBitmap(cookie, 0, 0, paint);
        paint.setAlpha(200);
        canvas.drawBitmap(candle, 0, 0, paint);
        imageView.setImageBitmap(alphaBitmap);
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
