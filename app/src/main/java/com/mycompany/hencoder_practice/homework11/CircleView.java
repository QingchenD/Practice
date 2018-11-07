package com.mycompany.hencoder_practice.homework11;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mycompany.mymaintestactivity.R;
import com.mycompany.util.Util;

public class CircleView extends View {

    private Paint  paint;

    private int RADIUS_DEFAULT = Util.dp2px(100);
    private int PADDING_DEFAULT = Util.dp2px(10);

    private int radius;

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        radius  = typedArray.getDimensionPixelSize(R.styleable.CircleView_radius, RADIUS_DEFAULT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width =  getPaddingLeft() + radius *2 + getPaddingRight();
        int height = getPaddingTop() + radius *2 + getPaddingBottom();

         width = resolveSizeAndState(width, widthMeasureSpec, 0);
         height = resolveSizeAndState(height, heightMeasureSpec, 0);

         setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.RED);
        canvas.drawCircle(getPaddingLeft() + radius, getPaddingTop() + radius, radius, paint);
    }
}
