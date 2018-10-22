package com.mycompany.hencoder_practice.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.mycompany.mymaintestactivity.R;
import com.mycompany.util.Util;

public class PartView extends View {

    private int PADDING = Util.dp2px(5);
    private int EDGE_WIDTH = Util.dp2px(3);
    private int WIDTH = Util.dp2px(200);
    private int HEIGTH = Util.dp2px(200);
    private Paint paint;
    private Bitmap bitmap ;
    private Xfermode xfermode;
    private RectF savedArea;


    public PartView(Context context) {
        super(context);
    }

    public PartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xfermode = new Xfermode();

        savedArea = new RectF(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.drawOval(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH, paint);
        int saved = canvas.saveLayer(savedArea, paint);
        canvas.drawOval(PADDING + EDGE_WIDTH, PADDING + EDGE_WIDTH, PADDING + WIDTH - EDGE_WIDTH, PADDING + WIDTH - EDGE_WIDTH, paint);
        paint.setXfermode(xfermode);
        canvas.drawBitmap(bitmap, PADDING, PADDING, paint);
        paint.setXfermode(null);
        canvas.restoreToCount(saved);
    }
}
