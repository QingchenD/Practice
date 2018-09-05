package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;

/**
 * Created by Qingweid on 2016/8/1.
 */
public class SetImageManualActivity extends Activity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_image_size_by_manual);

        imageView = (ImageView) findViewById(R.id.image_tst);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int mScreenWidth = metric.widthPixels;     // 屏幕宽度（像素）

        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.home_header_service_area_top_margin, typedValue, true);
         typedValue.getFloat();

        imageView.getLayoutParams().width
                = (int) (mScreenWidth * typedValue.getFloat());

        imageView.setImageResource(R.drawable.bells_wavfile);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}


