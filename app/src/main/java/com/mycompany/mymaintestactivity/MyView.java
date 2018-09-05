package com.mycompany.mymaintestactivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Qingweid on 2016/8/29.
 */
public class MyView extends View {

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint  paint = new Paint();
        paint.setColor(Color.RED);// 设置灰色
        paint.setStyle(Paint.Style.FILL);//设置填满
        canvas.drawRect(0, 0, 800, 200, paint);// 长方形
    }
}
