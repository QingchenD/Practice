package com.mycompany.annotation;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.util.DisplayMetrics;

import com.mycompany.mymaintestactivity.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ButterknifeTestActivity extends Activity {

    private final String TAG = "ButterKnifeTestActivity";

    @Bind(R.id.tv)
    TextView mTv;
    @Bind(R.id.btn)
    Button mBtn;
    @Bind(R.id.list_view)
    ListView mListView;
    @Bind(R.id.image)
    ImageView mImage;
    @Bind(R.id.image_btn)
    ImageButton mImageBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.butterknife_test_layout);
        ButterKnife.bind(this);
        //ButterKnife.bind(this);

        Log.i(TAG , " onCreate()");

        mTv.setText("ButterKnife test");
        mBtn.setText("ButterKnife Button");
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            mImage.setImageDrawable(getDrawable(R.drawable.boot));
            mImageBtn.setBackground(getDrawable(R.drawable.candle));
        }

        getWith("onCreate");
        getStatusBarHeight("onCreate");

    }

    private void getWith(String tag) {
        //获取屏幕区域的宽高等尺寸
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        Log.i(tag , "getWith" + "widthPixels:" + widthPixels + " heightPixels:" + heightPixels);

        //应用程序APP玉玉宽高尺寸获取
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        Log.i(tag , "rect.width():" + rect.width() + " height:" + (rect.bottom-rect.top) );

        //获取状态栏高度
        Rect toolbarRect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(toolbarRect);
        int statusBarHeight = toolbarRect.top;
        Log.i(tag , "statusBarHeight:" + statusBarHeight );

        //View布局区域宽高等尺寸获取
        Rect viewRect = new Rect();
        getWindow().findViewById(Window.ID_ANDROID_CONTENT).getDrawingRect(viewRect);
        Log.i(tag , "viewRect.height:" + (viewRect.bottom - viewRect.top) );
    }


    private int getStatusBarHeight(String tag ) {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }

        Log.i(tag , "getStatusBarHeight() statusBarheight:" + (result) );

        return result;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG , " onStart()");
        getWith("onStart");
        getStatusBarHeight("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG , " onResume()");
        getWith("onResume");
        getStatusBarHeight("onResume");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG , " onPostResume()");
        getWith("onPostResume");
        getStatusBarHeight("onPostResume");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.i(TAG , " onWindowFocusChanged()");
        getWith("onWindowFocusChanged");
        getStatusBarHeight("onWindowFocusChanged");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
