package com.mycompany.hencoder_practice.homework11;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.mycompany.util.Util;

import java.util.ArrayList;
import java.util.List;

public class TagLayout extends ViewGroup {

    List<Rect> childrenBounds = new ArrayList<>();

    private String TAG = getClass().getSimpleName();
    private int SPCACE = Util.dp2px(2);

    public TagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        View child;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int widthUsed = 0;
        int heightUsed = 0;
        int lineMaxHeight = 0;
        for (int i = 0; i < getChildCount() ; i++) {
            child = getChildAt(i);
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            if (specMode != MeasureSpec.UNSPECIFIED &&
                    widthUsed + child.getMeasuredWidth() > measureWidth ) {
                heightUsed += lineMaxHeight;
                widthUsed = 0;
                lineMaxHeight = 0;
               // measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }

            Rect bound;
            if (i >= childrenBounds.size()) {
                bound = new Rect();
                childrenBounds.add(bound);
            } else {
                bound = childrenBounds.get(i);
            }

            bound.set(widthUsed, heightUsed, widthUsed + child.getMeasuredWidth(), heightUsed + child.getMeasuredHeight());
            widthUsed += child.getMeasuredWidth();
            lineMaxHeight = Math.max(lineMaxHeight, child.getMeasuredHeight());
        }

        setMeasuredDimension(measureWidth, heightUsed + lineMaxHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount() ; i++) {
            Rect bounds = childrenBounds.get(i);
            View child = getChildAt(i);
            child.layout(bounds.left, bounds.top, bounds.right, bounds.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return  new MarginLayoutParams(getContext(), attrs);
    }
}
