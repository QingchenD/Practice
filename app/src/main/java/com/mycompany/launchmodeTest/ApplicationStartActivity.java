package com.mycompany.launchmodeTest;

import android.util.Log;

/**
 * Created by Qingweid on 2018/3/30.
 */

public class ApplicationStartActivity extends LaunchModeBasicActivity {
    private final String TAG = "ApplicationStartAct";
    @Override
    protected void onResume() {
        super.onResume();

        setTitle("ApplicationStartActivity");
        Log.i(TAG, " TaskID:" + getTaskId());
    }
}
