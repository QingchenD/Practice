package com.mycompany.hencoder_practice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mycompany.util.Util;

public class PieView extends View {

    private final int RADIUS = Util.dp2px(80);
    private final int TRANSLATE_DISTANCE = Util.dp2px(6);
    private final Paint paint;
    private int[] angle  = new int[] {60 , 100, 90, 80, 30 };
    private int[] colors = new int[] {Color.GREEN, Color.BLUE, Color.RED, Color.BLACK, Color.YELLOW };
    private RectF rectF;
    private int transferIndex = 2;

    public PieView(Context context) {
        super(context);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(Util.dp2px(3));
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        rectF = new RectF(getWidth() / 2 - RADIUS , getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS , getHeight() / 2 + RADIUS);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int startAngle = 0;
        for ( int i = 0; i < angle.length; i++) {
            paint.setColor(colors[i]);
            if (true) {
                canvas.save();
                canvas.translate(TRANSLATE_DISTANCE * (float)Math.cos(Math.toRadians((startAngle + angle[i] / 2.0f))),
                        TRANSLATE_DISTANCE * (float)Math.sin(Math.toRadians((startAngle + angle[i] / 2.0f))));
                canvas.drawArc(getWidth() / 2 - RADIUS , getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS , getHeight() / 2 + RADIUS, startAngle, angle[i], true, paint);
                canvas.restore();
            } else {
                canvas.drawArc(rectF, startAngle, angle[i], true, paint);
            }
            startAngle += angle[i];
        }
    }
}
