package com.mycompany.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mycompany.util.Util;

public class DemoCLockView extends View {
    private final String TAG = getClass().getSimpleName();
    private Paint paint;
    private Paint textPaint;

    public DemoCLockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DemoCLockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DemoCLockView(Context context) {
        super(context);
    }

    {
        paint = new Paint();
        paint.setColor(0xFF808080);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);

        textPaint = new Paint();
        textPaint.setTypeface(Typeface.SERIF);
        textPaint.setTextSize(Util.sp2px(14));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int radius = Math.min(width, height) / 2;
        int x = (width - 2 * radius) / 2 + radius;
        int y = (height - 2 * radius) / 2 + radius;
        int rotateAngle = 360 / 12;

        canvas.drawCircle(x, y, radius, paint);
        canvas.save();
        for (int i = 0; i < 12; i++) {
            canvas.drawLine(x, y - radius, x, y - radius + 100, paint);
            drawRotateText(canvas,i, x, y, radius);
            canvas.rotate(rotateAngle, x, y);
        }
        canvas.restore();

        //drawText(canvas, radius, x, y);

        System.out.println(TAG + " fontSpace:" + textPaint.getFontSpacing());
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        System.out.println(TAG + " one line text height:" + (fontMetrics.bottom - fontMetrics.top + fontMetrics.leading) );
        System.out.println(TAG + " descent - ascent:" + (fontMetrics.descent - fontMetrics.ascent ) );
        System.out.println(TAG + " descent:" + fontMetrics.descent + " ascent:" + fontMetrics.ascent + " top:" + fontMetrics.top +
                 " bottom:" + fontMetrics.bottom + " leading:" + fontMetrics.leading);
    }

    private void drawRotateText(Canvas canvas, int index, int x, int y, int radius) {
        String str = String.valueOf(index);
        Rect rect = new Rect();
        textPaint.getTextBounds(str, 0, str.length(), rect);
        int w = rect.width();
        int h = rect.height();
        canvas.drawText(str, x - w / 2, y - radius + 100 + h, textPaint);
    }


    private void drawText(Canvas canvas, int r, int x, int y) {
        // 获取文字高度用于设置文本垂直居中
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float textSize = (fontMetrics.bottom - fontMetrics.top);
        // 数字离圆心的距离,40为刻度的长度,20文字大小
        int distance = r - 100 - Util.sp2px(14);//r - 40 - 20;
        // 数字的坐标(a,b)
        float a, b;
        // 每30°写一个数字
        for (int i = 0; i < 12; i++) {
            a = (float) (distance * Math.sin(i * 30 * Math.PI / 180) + x);
            b = (float) (y - distance * Math.cos(i * 30 * Math.PI / 180));
            if (i == 0) {
                canvas.drawText("12", a, b + textSize / 3, textPaint);
            } else {
                canvas.drawText(String.valueOf(i), a, b + textSize / 3, textPaint);
            }
        }
    }
}
