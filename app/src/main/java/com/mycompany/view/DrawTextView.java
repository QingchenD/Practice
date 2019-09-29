package com.mycompany.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mycompany.util.Util;

public class DrawTextView extends View {

    private final String TAG = getClass().getSimpleName();

    private Paint paint;
    public DrawTextView(Context context) {
        super(context);
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint = new Paint();
        paint.setTextSize(Util.sp2px(28));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String text = "搞笑我们是认真的！！！";
        drawTextLines(canvas, text, 100, 300);
        paint.setColor(0xFF000000);
        text = "Are you kidding, I am Serious!";
        drawTextLines(canvas, text, 100, 500);

        //canvas.rotate(30);
        text = "搞笑我们是认真的";
        drawTextBounds(canvas, text);

        canvas.save();
        canvas.rotate(45);
        canvas.translate(200, 200);
        Rect  r = new Rect(-200, -200, 0, 0);
        canvas.drawCircle(0, 0, 5, paint);
        canvas.drawRect(r, paint);
        Rect  r2 = new Rect(-200, -400, 0, -200);
        canvas.drawRect(r2, paint);
        canvas.restore();

        canvas.save();
        text = "我是翻转的";
        drawTextFanZhuanBounds(canvas, text);
        canvas.restore();


//        canvas.translate(-300,-300);
//        canvas.rotate(180);
//        canvas.drawLine(0,0, -800,-800, paint);

        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        System.out.println(TAG + "");

    }

    private void drawTextLines(Canvas canvas, String text, int baselineX, int baselineY) {
        //int baselineX = 100;      //基线的 X 轴
        //int baselineY = 300;    //基线的 Y 轴

        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        //获取各个线距离 baseline 线的距离
        float ascent = fontMetrics.ascent;
        float descent = fontMetrics.descent;
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;

        //绘制文本
        canvas.drawText(text, baselineX, baselineY, paint);

        paint.setStrokeWidth(3);

        //绘制基线
        paint.setColor(Color.RED);
        canvas.drawLine(baselineX, baselineY, 1000, baselineY, paint);

        //绘制 ascent 线
        paint.setColor(Color.BLUE);
        canvas.drawLine(baselineX, baselineY + ascent, 1000, baselineY + ascent, paint);

        //绘制 descent 线
        paint.setColor(Color.BLACK);
        canvas.drawLine(baselineX, baselineY + descent, 1000, baselineY + descent, paint);

        //绘制 top 线
        paint.setColor(Color.GREEN);
        canvas.drawLine(baselineX, baselineY + top, 1000, baselineY + top, paint);

        //绘制 bottom 线
        paint.setColor(Color.YELLOW);
        canvas.drawLine(baselineX, baselineY + bottom, 1000, baselineY + bottom, paint);
    }

    private void drawTextBounds(Canvas canvas, String text) {
        int baselineX = 100;      //基线的 X 轴
        int baselineY = 700;    //基线的 Y 轴

        //String text = "搞笑我们是认真的";

        //获取当前线到baseline线的距离
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float top = fontMetrics.top;    //为负值
        float bottom = fontMetrics.bottom;
        //获取字符串所占高度
        float height = fontMetrics.bottom - fontMetrics.top;

        //获取字符串所占宽度
        float width = paint.measureText(text);

        //绘制字符串所占区域
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(baselineX, baselineY + top, width + baselineX, bottom + baselineY, paint);

        //获取最小矩形  默认是以（0，0）为基线获取，所以要想把最小矩形绘制到正确位置，需要 + baseline Y
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);

        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        int left = rect.left + baselineX;
        top = baselineY + rect.top;
        int right = rect.right + baselineX;
        bottom = rect.bottom + baselineY;
        //绘制最小矩形
        canvas.drawRect(left, top, right, bottom, paint);

        //绘制文本
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText(text, baselineX, baselineY, paint);
    }

    private void drawTextFanZhuanBounds(Canvas canvas, String text) {
        int baselineX = 100;      //基线的 X 轴
        int baselineY = 900;    //基线的 Y 轴

        float textWidth = paint.measureText(text);
        Paint.FontMetrics fm = paint.getFontMetrics();
        float textHeight = fm.descent - fm.ascent;

        float midX = baselineX + textWidth / 2.0f;
        float midY = baselineY - textHeight / 2.0f;

        System.out.println(TAG + " Midx:" + midX + " midY" + midY);
        canvas.translate(midX, midY);
        canvas.rotate(180);
        canvas.translate(-midX, -midY);

        //获取当前线到baseline线的距离
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float top = fontMetrics.top;    //为负值
        float bottom = fontMetrics.bottom;
        //获取字符串所占高度
        float height = fontMetrics.bottom - fontMetrics.top;

        //获取字符串所占宽度
        float width = paint.measureText(text);

        //绘制字符串所占区域
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(baselineX, baselineY + top, width + baselineX, bottom + baselineY, paint);

        //获取最小矩形  默认是以（0，0）为基线获取，所以要想把最小矩形绘制到正确位置，需要 + baseline Y
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);

        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        int left = rect.left + baselineX;
        top = baselineY + rect.top;
        int right = rect.right + baselineX;
        bottom = rect.bottom + baselineY;
        //绘制最小矩形
        canvas.drawRect(left, top, right, bottom, paint);

        //绘制文本
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText(text, baselineX, baselineY, paint);
    }
}
