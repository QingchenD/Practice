package com.mycompany.hencoder_practice.homework8;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.mycompany.mymaintestactivity.R;
import com.mycompany.util.Util;

public class MixView extends View {

    private Bitmap bitmap;
    private Paint paint;
    private Paint textPaint;
    private BitmapFactory.Options options;
    private Matrix matrix;

    private final int BITMAP_WIDTH = Util.dp2px(150);
    private final int BITMAP_STARTY = Util.dp2px(150);
    private final int TEXT_SIZE = Util.sp2px(18);

    String str = "Note the \"Killable\" column in the above table -- for those methods that are marked as being killable, after that method returns the process hosting the activity may killed by the system at any time without another line of its code being executed. Because of this, you should use the onPause() method to write any persistent data (such as user edits) to storage. In addition, the method onSaveInstanceState(Bundle) is called before placing the activity in such a background state, allowing you to save away any dynamic instance state in your activity into the given Bundle, to be later received in onCreate(android.os.Bundle) if the activity needs to be re-created. See the Process Lifecycle section for more information on how the lifecycle of a process is tied to the activities it is hosting. Note that it is important to save persistent data in onPause() instead of onSaveInstanceState(android.os.Bundle) because the latter is not part of the lifecycle callbacks, so will not be called in every situation as described in its documentation.";

    public MixView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.dog, options);
        options.inDensity = options.outWidth;
        options.inTargetDensity = BITMAP_WIDTH;
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog, options);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(TEXT_SIZE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        options.inJustDecodeBounds = true;
        canvas.drawBitmap(bitmap, getWidth() - BITMAP_WIDTH, BITMAP_STARTY, paint );

        //draw text
        drawText(str, canvas);
    }

    private void drawText(String text, Canvas canvas) {
        if (text == null || text.length() == 0 ) return;
        int measuredCount = 0;
        float textHeight = 0;
        float[] measuredWidth = {0};
        for (int i = 0, lineIndex = 1; i < text.length(); lineIndex++ ) {
            textHeight = lineIndex * textPaint.getFontSpacing();
            measuredCount = textPaint.breakText(text, i, text.length(), true, getTextWidth(textHeight), measuredWidth);
            canvas.drawText(text,i, i + measuredCount, 0f, textHeight, textPaint );
            i += measuredCount;
        }
    }

    private float getTextWidth(float textHeight) {
        int bitmapTop = BITMAP_STARTY;
        int bitmapbottom = bitmapTop + options.outHeight;

        if (textHeight > bitmapTop && textHeight < bitmapbottom) {
            return getWidth() - BITMAP_WIDTH;
        }

        return getWidth();
    }
}
