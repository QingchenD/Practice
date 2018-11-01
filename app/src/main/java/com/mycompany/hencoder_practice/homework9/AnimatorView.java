package com.mycompany.hencoder_practice.homework9;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mycompany.mymaintestactivity.R;
import com.mycompany.util.Util;

public class AnimatorView extends View {

    private final int BITMAP_LEFT = Util.dp2px(100);
    private final int BITMAP_TOP = Util.dp2px(100);
    private final int BITMAP_WIDTH = Util.dp2px(150);

    private Paint paint;
    private Bitmap bitmap;
    private Camera camera;

    private float bottomFlip;
    private float flipRotation;
    private float topFlip;

    public AnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        camera = new Camera();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.dog, options);
        options.inDensity = options.outWidth;
        options.inTargetDensity = BITMAP_WIDTH;
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dog, options);
    }

    public float getBottomFlip() {
        return bottomFlip;
    }

    public void setBottomFlip(float bottomFlip) {
        this.bottomFlip = bottomFlip;
        invalidate();
    }

    public float getFlipRotation() {
        return flipRotation;
    }

    public void setFlipRotation(float flipRotation) {
        this.flipRotation = flipRotation;
        invalidate();
    }

    public float getTopFlip() {
        return topFlip;
    }

    public void setTopFlip(float topFlip) {
        this.topFlip = topFlip;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //上
        canvas.save();
        canvas.translate((BITMAP_LEFT + BITMAP_WIDTH / 2) , (BITMAP_TOP + BITMAP_WIDTH / 2));
        canvas.rotate(-flipRotation);

        camera.save();
        camera.rotateX(topFlip);
        camera.applyToCanvas(canvas);
        camera.restore();

        canvas.clipRect(-BITMAP_WIDTH , -(int)(BITMAP_WIDTH / 2 + BITMAP_WIDTH * Math.sin(Math.toRadians(30)) ), BITMAP_WIDTH, 0);
        canvas.rotate(flipRotation);
        canvas.translate(- (BITMAP_LEFT + BITMAP_WIDTH / 2) , - (BITMAP_TOP + BITMAP_WIDTH / 2));
        canvas.drawBitmap(bitmap, BITMAP_LEFT, BITMAP_TOP, paint);
        canvas.restore();

        //下
        canvas.save();
        canvas.translate((BITMAP_LEFT + BITMAP_WIDTH / 2) , (BITMAP_TOP + BITMAP_WIDTH / 2));
        canvas.rotate(-flipRotation);

        camera.save();
        camera.rotateX(bottomFlip);
        camera.applyToCanvas(canvas);
        camera.restore();

        canvas.clipRect(-BITMAP_WIDTH, 0, BITMAP_WIDTH, (BITMAP_WIDTH ) );
        canvas.rotate(flipRotation);
        canvas.translate(- (BITMAP_LEFT + BITMAP_WIDTH / 2) , - (BITMAP_TOP + BITMAP_WIDTH / 2));
        canvas.drawBitmap(bitmap, BITMAP_LEFT, BITMAP_TOP, paint);
        canvas.restore();
    }
}

