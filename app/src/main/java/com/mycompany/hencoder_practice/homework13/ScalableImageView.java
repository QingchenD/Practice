package com.mycompany.hencoder_practice.homework13;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Scroller;

import com.mycompany.mymaintestactivity.R;

public class ScalableImageView extends View {

    private final float BIG_SCALE_FACTOR = 1.3f;

    private GestureDetector gestureDetector;
    private DoubleDetector  doubleDetector;

    private Paint paint;
    private Bitmap bitmap;
    private float smallScale;
    private float bigScale;
    private float scale;
    private float scaleFraction;
    private boolean bBig;

    private float imgOriginalWidth;
    private float imgOriginalHeight;

    private float originalOffsetX;
    private float originalOffsetY;

    private float offsetX;
    private float offsetY;

    public ScalableImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        gestureDetector = new GestureDetector(context, new MyGestureDetectorListener());
        doubleDetector = new DoubleDetector();

//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeResource(getResources(), R.drawable.dog, options);
//        imgOriginalWidth = options.outWidth;
//        imgOriginalHeight = options.outHeight;

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        imgOriginalWidth = bitmap.getWidth();
        imgOriginalHeight = bitmap.getHeight();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        smallScale =  Math.min(getWidth() / imgOriginalWidth, getHeight() / imgOriginalHeight);
        bigScale = Math.max(getWidth() / imgOriginalWidth, getHeight() / imgOriginalHeight) * BIG_SCALE_FACTOR;
        scaleFraction = smallScale;
        bBig = false;

        originalOffsetX = ((float) getWidth() - bitmap.getWidth()) / 2;
        originalOffsetY = ((float) getHeight() - bitmap.getHeight()) / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        
        float factor = (scaleFraction - smallScale) / (bigScale - smallScale);
        canvas.translate(offsetX * factor, offsetY * factor);
        canvas.scale(scaleFraction, scaleFraction, getWidth()/2f, getHeight()/2f);
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY ,paint);
    }

    public float getScaleFraction() {
        return scaleFraction;
    }

    public void setScaleFraction(float scaleFraction) {
        this.scaleFraction = scaleFraction;
        invalidate();
    }

    private ObjectAnimator getAnimator() {
        return ObjectAnimator.ofFloat(this,"scaleFraction",smallScale, bigScale)
                .setDuration(1000);
    }

    class MyGestureDetectorListener  implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            bBig = !bBig;
            if (bBig) {
                getAnimator().start();
            } else {
                getAnimator().reverse();
            }
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (bBig) {
                offsetX -= distanceX;
                if (offsetX > (bigScale * imgOriginalWidth - getWidth()) / 2f) {
                    offsetX = (bigScale * imgOriginalWidth - getWidth()) / 2f;
                } else if ( offsetX <  -((bigScale * imgOriginalWidth - getWidth()) / 2f)) {
                    offsetX = -((bigScale * imgOriginalWidth - getWidth()) / 2f);
                }

                offsetY -= distanceY;
                if (offsetY > (bigScale * imgOriginalHeight - getHeight()) / 2f) {
                    offsetY = (bigScale * imgOriginalHeight - getHeight()) / 2f;
                } else if ( offsetY <  -((bigScale * imgOriginalHeight - getHeight()) / 2f)) {
                    offsetY = -((bigScale * imgOriginalHeight - getHeight()) / 2f);
                }

            }
            invalidate();
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }

    class DoubleDetector implements  GestureDetector.OnDoubleTapListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return false;
        }
    }
}
