package com.mycompany.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.mycompany.mymaintestactivity.R;

public class ShaderImageView extends AppCompatImageView {
    private Paint paint;
    private Paint linePaint;

    public ShaderImageView(Context context) {
        super(context);
        init();
    }

    public ShaderImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆形

        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, getMeasuredWidth() / 2, paint);

        //canvas.drawLine(10, 10, 500, 10, null);
        canvas.drawLine(10, 50, 500, 50, linePaint);
    }

    private void init() {
        //位图着色
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.candle);
        Shader shader1 = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        paint = new Paint();
        paint.setShader(shader1);

        linePaint = new Paint();
        linePaint.setColor(Color.RED);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(5);

    }
}
