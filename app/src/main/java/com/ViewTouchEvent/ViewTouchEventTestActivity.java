package com.ViewTouchEvent;

import android.app.Activity;
import android.content.Context;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.mycompany.mymaintestactivity.BuildConfig;
import com.mycompany.mymaintestactivity.R;

public class ViewTouchEventTestActivity extends Activity {
    private static final String TAG = "ViewTouchEventTest";

    FrameLayout layout_a;
    FrameLayout layout_a_a;
    FrameLayout layout_a_a_a;
    FrameLayout layout_a_b;
    View child[];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touch_event_layout);

        layout_a = (FrameLayout) findViewById(R.id.layout_a);
        layout_a.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, " layout_a onTouch()" );
                return false;
            }
        });
        layout_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, " layout_a onclick()" );
            }
        });




        layout_a_a = (FrameLayout) findViewById(R.id.layout_a_a);
        layout_a_a.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, " layout_a_a onTouch()" );
                return false;
            }
        });
        layout_a_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, " layout_a_a onclick()" );
            }
        });


        layout_a_a_a = (FrameLayout) findViewById(R.id.layout_a_a_a);
        layout_a_a_a.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, " layout_a_a_a onTouch()" );
                return false;
            }
        });
        layout_a_a_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, " layout_a_a_a onclick()" );
                if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.LOLLIPOP) {
                    Log.i(TAG, " layout_a.z:" + layout_a.getZ() + " layout_a.x:" + layout_a.getX() );
                    Log.i(TAG, " layout_a_a.z:" + layout_a_a.getZ() + " layout_a_a.x:" + layout_a_a.getX() );
                    Log.i(TAG, " layout_a_a_a.z:" + layout_a_a_a.getZ() + " layout_a_a_a.x:" + layout_a_a_a.getX() );
                    Log.i(TAG, " layout_a_b.z:" + layout_a_b.getZ() + " layout_a_b.x:" + layout_a_b.getX() );


                    if (child != null) {
                        for (int i = 0 ; i < child.length ; i++ ) {
                            View view = child[i];
                            Log.i(TAG, " child layout_a.z:" + view.getZ() + " layout_a.x:" + view.getX() );
                        }
                    }
                }
            }
        });


        layout_a_b = (FrameLayout) findViewById(R.id.layout_a_b);
        layout_a_b.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, " layout_a_b onTouch()" );
                return false;
            }
        });
        layout_a_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, " layout_a_b onclick()" );

                if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.LOLLIPOP) {
                    Log.i(TAG, " layout_a.z:" + layout_a.getZ() + " layout_a.x:" + layout_a.getX() );
                    Log.i(TAG, " layout_a_a.z:" + layout_a_a.getZ() + " layout_a_a.x:" + layout_a_a.getX() );
                    Log.i(TAG, " layout_a_a_a.z:" + layout_a_a_a.getZ() + " layout_a_a_a.x:" + layout_a_a_a.getX() );
                    Log.i(TAG, " layout_a_b.z:" + layout_a_b.getZ() + " layout_a_b.x:" + layout_a_b.getX() );
                }
            }
        });
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.LOLLIPOP) {
            layout_a_b.setZ(1.0f);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.LOLLIPOP) {
            Log.i(TAG, " onResume() layout_a.z:" + layout_a.getZ() + " layout_a.x:" + layout_a.getX() );
            Log.i(TAG, " onResume() layout_a_a.z:" + layout_a_a.getZ() + " layout_a_a.x:" + layout_a_a.getX() );
            Log.i(TAG, " onResume() layout_a_a_a.z:" + layout_a_a_a.getZ() + " layout_a_a_a.x:" + layout_a_a_a.getX() );
            Log.i(TAG, " onResume() layout_a_b.z:" + layout_a_b.getZ() + " layout_a_b.x:" + layout_a_b.getX() );
        }

        child = new View[layout_a.getChildCount()];
        for (int i = 0 ; i < child.length; i++) {
            child[i] = layout_a.getChildAt(i);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG,  " onTouchEvent() " + event.getAction());
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG,  " dispatchTouchEvent() " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }


    public static class MyFrameLayout extends FrameLayout {

        public MyFrameLayout(@NonNull Context context) {
            super(context);
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            return super.dispatchTouchEvent(ev);
        }


    }
}
