package com.mycompany.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.mycompany.mymaintestactivity.R;

/**
 * Created by Qingweid on 2016/3/24.
 */
public class FragmentPracticeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_practice);
    }
}
