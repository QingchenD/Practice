package com.mycompany.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mycompany.util.Util;

public class DemoView extends View {

    private Paint paint;
    private Paint textPaint;
    public DemoView(Context context) {
        super(context);
        init();
    }

    public DemoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DemoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(0xFF800000);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(3);

        textPaint = new Paint();
        textPaint.setColor(0xFF000080);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextSize(Util.sp2px(14));
        textPaint.setStrokeWidth(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //canvas.drawRGB(100, 200, 200);

        int left = 100;
        int right = 1000;
        int top = 100;
        int bottom = 1600;
        int index;
        int distatnce = 100;
        //1
        int one = canvas.save();
        index = 0;
        paint.setColor(0xFFFF0000);
        canvas.drawRect(left + distatnce * index,top + distatnce * index, right - distatnce * index, bottom - distatnce * index, paint);
        canvas.drawText("1",left + distatnce * index, top + distatnce * index, textPaint);

        // 2
        int two = canvas.save();
        index++;
        paint.setColor(0xFF800000);
        canvas.drawRect(left + distatnce * index,top + distatnce * index, right - distatnce * index, bottom - distatnce * index, paint);
        canvas.drawText("2",left + distatnce * index, top + distatnce * index, textPaint);

        //3
        int three = canvas.save();
        index++;
        canvas.rotate(45, 0, 0);
        paint.setColor(0xFF400000);
        canvas.drawRect(left + distatnce * index,top + distatnce * index, right - distatnce * index, bottom - distatnce * index, paint);
        canvas.drawText("3",left + distatnce * index, top + distatnce * index, textPaint);
        canvas.drawText("hahah this is 3",left + distatnce * index + 30, top + distatnce * index, textPaint);

        //4
        canvas.restoreToCount(two);
        canvas.drawText("hahah this is Restore 1111!",left + distatnce * index + 30 , top + distatnce * index, textPaint);

        // 5
        //index--;
        //canvas.restore();
        //canvas.drawText("hahah this is Restore 2222!",left + distatnce * index + 30, top + distatnce * index, textPaint);

//        // 6
//        index--;
//        canvas.restore();
//        canvas.drawText("hahah this is Restore 3333!",left + distatnce * index + 30, top + distatnce * index, textPaint);
    }
}
