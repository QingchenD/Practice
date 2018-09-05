package com.mycompany.mymaintestactivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Qingweid on 2016/9/22.
 */
public class UnderlineEditText extends TextView {
    private static final String TAG = "UnderlineEditText";
    private Paint mPaint;
    private Rect mRect;
    private float mult = 1.1f;
    private float add = 1.0f;

    public UnderlineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UnderlineEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        mRect = new Rect();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GRAY);
        mPaint.setAntiAlias(true);
        this.setLineSpacing(add, mult);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Log.d(TAG, "func [onDraw]");
        int count = getLineCount();
        for (int i = 0; i < count; i++) {
            getLineBounds(i, mRect);
            int baseline = (i + 1) * getLineHeight();
            canvas.drawLine(mRect.left, baseline, mRect.right, baseline, mPaint);
        }
        super.onDraw(canvas);
    }

}
