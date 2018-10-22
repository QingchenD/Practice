package com.mycompany.hencoder_practice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mycompany.util.Util;

public class PanelView extends View {
    private final int ANGLE = 120;
    private final int PADDING = Util.dp2px(5);
    private final int RADIUS = Util.dp2px(200);
    private final int POINTER_LENGTH = Util.dp2px(150);
    private Paint paint;
    private Path dash;

    private Path arc;
    private PathEffect pathEffect;

    public PanelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        arc = new Path();
        arc.addArc(getWidth() / 2 - RADIUS , getHeight() / 2 - RADIUS , getWidth() / 2 + RADIUS , getHeight() /2 + RADIUS ,
                ANGLE + 30, 360 - ANGLE);

        PathMeasure pathMeasure = new PathMeasure(arc, false);
        float advance = (pathMeasure.getLength() - Util.dp2px(2) ) / 20;
        dash = new Path();
        dash.addRect(0,0,Util.dp2px(2), Util.dp2px(20), Path.Direction.CCW );
        pathEffect = new PathDashPathEffect(dash, advance , 0, PathDashPathEffect.Style.ROTATE);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#808000"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画线
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() /2 + RADIUS,
                ANGLE + 30, 360 - ANGLE, false , paint);

        //画刻度
        paint.setPathEffect(pathEffect);
        canvas.drawArc(getWidth() / 2 - RADIUS, getHeight() / 2 - RADIUS, getWidth() / 2 + RADIUS, getHeight() /2 + RADIUS,
                ANGLE + 30, 360 - ANGLE, false , paint);
        paint.setPathEffect(null);

        //画指针
        canvas.drawLine(getWidth() / 2 , getHeight() / 2 ,
                (float)(getWidth() / 2 + Math.cos(Math.toRadians(getDegree(5))) * POINTER_LENGTH),
                (float)(getHeight() / 2 + Math.sin(Math.toRadians(getDegree(5))) * POINTER_LENGTH),
                paint);

    }

    private double getDegree(int index) {
        return ANGLE + 30 + (360 - ANGLE) / 20.0f * index;
    }
}
