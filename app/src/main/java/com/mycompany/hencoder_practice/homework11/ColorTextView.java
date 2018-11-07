package com.mycompany.hencoder_practice.homework11;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mycompany.util.Util;

import java.util.Random;

public class ColorTextView extends AppCompatTextView {

    private static int TEXT_BACKGROUD_COLOR[] = new int[] {
            Color.LTGRAY,
            Color.parseColor("#E91E63"),
            Color.parseColor("#673AB7"),
            Color.parseColor("#3F51B5"),
            Color.parseColor("#2196F3"),
            Color.parseColor("#009688"),
            Color.parseColor("#FF9800"),
            Color.parseColor("#FF5722"),
            Color.parseColor("#795548")
    } ;
    private static final int[] TEXT_SIZES = {16, 22, 28};

    private static final int CORNER_RADIUS = Util.dp2px(4);
    private static final int X_PADDING = Util.dp2px(16);
    private static final int Y_PADDING = Util.dp2px(8);

    private Paint paint ;
    private static final Random random = new Random();
    public ColorTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(TEXT_BACKGROUD_COLOR[random.nextInt(TEXT_BACKGROUD_COLOR.length)]);
        setTextColor(Color.WHITE);
        setPadding(X_PADDING, Y_PADDING, X_PADDING, Y_PADDING);
        setTextSize(TEXT_SIZES[random.nextInt(TEXT_SIZES.length)]);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(0,0 , getWidth(), getHeight(), CORNER_RADIUS, CORNER_RADIUS, paint);
        super.onDraw(canvas);
    }
}

