package com.mycompany.launchmodeTest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mycompany.mymaintestactivity.R;

/**
 * Created by Qingweid on 2018/3/30.
 */

public class SingleTaskActivity extends LaunchModeBasicActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitle("SingleTaskActivity");
        Log.i("SingleTaskActivity", " TaskID:" + getTaskId());
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }
}
