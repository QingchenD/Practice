package com.mycompany.hencoder_practice.homework10;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.mycompany.mymaintestactivity.R;
import com.mycompany.util.Util;

import java.util.Locale;

public class MyEditTextView extends AppCompatEditText {

    private int TEXT_ANIMATION_OFFSET = Util.dp2px(16);
    private int TEXT_HORIZONTAL_OFFSET = Util.dp2px(2);
    private int TEXT_VERTICAL_OFFSET = Util.dp2px(22);
    private int TEXT_MARGIN = Util.dp2px(8);
    private int HIT_TEXT_COLOR = Color.parseColor("#40C00000");

    private static final float TEXT_SIZE = Util.sp2px(10);

    private TextPaint paint;
    private ObjectAnimator  animator;
    private boolean floatingLabelShown;
    private float floatingLabelFraction;
    private boolean useFloatingLabel;

    Rect backgroundPadding = new Rect();

    public MyEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    void init (Context context, AttributeSet attrs) {
        // get attribute
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyEditTextView);
        useFloatingLabel = typedArray.getBoolean(R.styleable.MyEditTextView_useFloatingLabel, true);

        // init paint
        paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(TEXT_SIZE);
        paint.setColor(HIT_TEXT_COLOR);
        //Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/calibri.ttf");
        //paint.setTypeface(tf); ///Typeface.SERIF);
        paint.setTextLocale(Locale.TAIWAN);
        paint.setFontFeatureSettings("smcp");
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();

        onUseFloatingLabelChanged();

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (floatingLabelShown && TextUtils.isEmpty(s)) {
                    floatingLabelShown = false;
                    getAnimator().reverse();
                } else if (!floatingLabelShown) {
                    floatingLabelShown = true;
                    getAnimator().start();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public float getFloatingLabelFraction() {
        return floatingLabelFraction;
    }

    public void setFloatingLabelFraction(float floatingLabelFraction) {
        this.floatingLabelFraction = floatingLabelFraction;
        invalidate();
    }

    private void onUseFloatingLabelChanged() {
        Drawable background = getBackground();
        if (background != null) {
            getBackground().getPadding(backgroundPadding);
        }
        if (useFloatingLabel) {
            setPadding(getPaddingLeft(), (int)(backgroundPadding.top + TEXT_SIZE + TEXT_MARGIN), getPaddingRight(), getPaddingBottom());
        } else {
            setPadding(getPaddingLeft(), backgroundPadding.top, getPaddingRight(), getPaddingBottom());
        }
    }

    private ObjectAnimator  getAnimator() {
        if (animator == null) {
            animator =  ObjectAnimator.ofFloat(this, "floatingLabelFraction", 0 , 1);
        }

        return animator;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draw hint string
        paint.setAlpha((int) (0xff * floatingLabelFraction));
        float extraOffset = TEXT_ANIMATION_OFFSET * (1 - floatingLabelFraction);
        canvas.drawText(getHint().toString(), TEXT_HORIZONTAL_OFFSET, TEXT_VERTICAL_OFFSET + extraOffset, paint);
    }
}
