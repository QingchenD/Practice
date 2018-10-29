package com.mycompany.hencoder_practice.homework7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mycompany.mymaintestactivity.R;
import com.mycompany.util.Util;

public class PartView extends View {

    private int PADDING = Util.dp2px(5);
    private int EDGE_WIDTH = Util.dp2px(3);
    private int WIDTH = Util.dp2px(140);
    private int HEIGTH = Util.dp2px(140);
    private Paint paint;
    private Bitmap bitmap ;
    private Xfermode xfermode;
    private RectF savedArea;
    private int bitMapWidth;
    private int bitMapHeight;


    public PartView(Context context) {
        super(context);
    }

    public PartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = getPicture(WIDTH);
        bitMapWidth = bitmap.getWidth();
        bitMapHeight = bitmap.getHeight();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

        if (isHardwareAccelerated()) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, paint);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        savedArea = new RectF(getWidth() / 2  - WIDTH / 2, getHeight() /2 - HEIGTH / 2, getWidth() / 2  + WIDTH / 2, getHeight() / 2  + HEIGTH / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawOval(getWidth() / 2  - WIDTH / 2, getHeight() /2 - HEIGTH / 2, getWidth() / 2  + WIDTH / 2, getHeight() / 2  + HEIGTH / 2, paint);
//        //int saved = canvas.saveLayer(savedArea, paint);
//        canvas.drawOval(getWidth() / 2  - WIDTH / 2 + EDGE_WIDTH, getHeight() /2 - HEIGTH / 2 + EDGE_WIDTH,
//                getWidth() / 2  + WIDTH / 2 - EDGE_WIDTH, getHeight() /2 + HEIGTH / 2 - EDGE_WIDTH, paint);
//        paint.setXfermode(xfermode);
//
//        canvas.drawBitmap(bitmap, getWidth() / 2 - bitMapWidth / 2, getHeight() / 2 - bitMapHeight / 2, paint);
//        paint.setXfermode(null);
//       // canvas.restoreToCount(saved);


        paint.setColor(Color.parseColor("#FFFFFFFF"));
        canvas.drawBitmap(bitmap, getWidth() / 2 - bitMapWidth / 2, getHeight() / 2 - bitMapHeight / 2, paint);
        paint.setXfermode(xfermode);
        //canvas.drawOval(getWidth() / 2  - WIDTH / 2, getHeight() /2 - HEIGTH / 2, getWidth() / 2  + WIDTH / 2, getHeight() / 2  + HEIGTH / 2, paint);
        //int saved = canvas.saveLayer(savedArea, paint);
        canvas.drawOval(getWidth() / 2  - WIDTH / 2 + EDGE_WIDTH, getHeight() /2 - HEIGTH / 2 + EDGE_WIDTH,
                getWidth() / 2  + WIDTH / 2 - EDGE_WIDTH, getHeight() /2 + HEIGTH / 2 - EDGE_WIDTH, paint);

        paint.setXfermode(null);
        // canvas.restoreToCount(saved);
    }

    private Bitmap getPicture(int width) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.dog, options);
        options.inDensity = options.outWidth;
        options.inTargetDensity = width;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(getResources(), R.drawable.dog, options);
    }
}
