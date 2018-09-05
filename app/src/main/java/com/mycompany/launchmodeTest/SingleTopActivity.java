package com.mycompany.launchmodeTest;

import android.app.Activity;
import android.util.Log;

/**
 * Created by Qingweid on 2018/3/30.
 */

public class SingleTopActivity extends LaunchModeBasicActivity {
    @Override
    protected void onResume() {
        super.onResume();

        setTitle("SingleTopActivity");
        Log.i("SingleTopActivity", " TaskID:" + getTaskId());
    }
}
