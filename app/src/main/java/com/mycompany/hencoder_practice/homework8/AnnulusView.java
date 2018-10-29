package com.mycompany.hencoder_practice.homework8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.mycompany.util.Util;

public class AnnulusView extends View {
    private Paint paint ;
    private Paint textPaint;

    private final int STROKE_WIDTH = Util.dp2px(15);
    private final int RADIUS  = Util.dp2px(100);
    private final int TEXT_SIZE = Util.sp2px(60);

    public AnnulusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(STROKE_WIDTH);
        paint.setStrokeCap(Paint.Cap.ROUND);

        //set Text
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.DEFAULT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1. draw circle and arc
        paint.setColor(Color.GRAY);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, RADIUS, paint );

        paint.setColor(Color.RED);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS,
                getWidth() / 2 + RADIUS, getHeight() / 2 + RADIUS, -90f, 225f, false, paint);

        // 2. draw Arc
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float offset = Math.abs(Math.abs(fontMetrics.descent) - Math.abs(fontMetrics.ascent)) / 2.0f;
        canvas.drawText("abab", getWidth() / 2, getHeight() /2 + offset, textPaint);

    }
}
