package com.mycompany.hencoder_practice.homework8;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mycompany.mymaintestactivity.R;
import com.mycompany.util.Util;


public class ClicpView extends View {

    private final int BITMAP_WIDTH = Util.dp2px(300);
    private final int CAMERA_LOCATION = Util.dp2px(15);
    private final int BITMAP_TOP = 100;
    private final int BITMAP_LEFT = 100;


    private Paint paint;
    private Bitmap bitmap;
    private Camera camera;

    private ObjectAnimator animator;

    public ClicpView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.dog, options);
        options.inDensity = options.outWidth;
        options.inTargetDensity = BITMAP_WIDTH;
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog, options);

        animator = new ObjectAnimator();

        // set camera
        camera = new Camera();
        camera.rotateX(45);
        camera.setLocation(0, 0, Util.getZForCamera(8)); // -8 = -8 * 72

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制上半部分
//        canvas.save();
//        canvas.translate(100 + 600 / 2, 100 + 600 / 2);
//        canvas.rotate(-20);
//        canvas.clipRect(- 600, - 600, 600, 0);
//        canvas.rotate(20);
//        canvas.translate(- (100 + 600 / 2), - (100 + 600 / 2));
//        canvas.drawBitmap(bitmap, 100, 100, paint);
//        canvas.restore();

        // 绘制下半部分
//        canvas.save();
//        canvas.translate(100 + 600 / 2, 100 + 600 / 2);
//        canvas.rotate(-20);
//        camera.applyToCanvas(canvas);
//        canvas.clipRect(- 600, 0, 600, 600);
//        canvas.rotate(20);
//        canvas.translate(- (100 + 600 / 2), - (100 + 600 / 2));
//        canvas.drawBitmap(bitmap, 100, 100, paint);
//        canvas.restore();

        //上
        canvas.save();
        canvas.translate((BITMAP_LEFT + BITMAP_WIDTH / 2) , (BITMAP_TOP + BITMAP_WIDTH / 2));
        canvas.rotate(-30);
        canvas.clipRect(-BITMAP_WIDTH , -(int)(BITMAP_WIDTH / 2 + BITMAP_WIDTH * Math.sin(Math.toRadians(30)) ), BITMAP_WIDTH, 0);
        canvas.rotate(30);
        canvas.translate(- (BITMAP_LEFT + BITMAP_WIDTH / 2) , - (BITMAP_TOP + BITMAP_WIDTH / 2));
        canvas.drawBitmap(bitmap, BITMAP_LEFT, BITMAP_TOP, paint);
        canvas.restore();

        //下
        canvas.save();
        canvas.translate((BITMAP_LEFT + BITMAP_WIDTH / 2) , (BITMAP_TOP + BITMAP_WIDTH / 2));
        canvas.rotate(-30);
        camera.applyToCanvas(canvas);
        canvas.clipRect(-BITMAP_WIDTH, 0, BITMAP_WIDTH, (BITMAP_WIDTH ) );
        canvas.rotate(30);
        canvas.translate(- (BITMAP_LEFT + BITMAP_WIDTH / 2) , - (BITMAP_TOP + BITMAP_WIDTH / 2));
        canvas.drawBitmap(bitmap, BITMAP_LEFT, BITMAP_TOP, paint);
        canvas.restore();
    }
}
