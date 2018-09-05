package com.mycompany.launchmodeTest;

import android.app.Activity;
import android.util.Log;

/**
 * Created by Qingweid on 2018/3/30.
 */

public class StandardActivity extends LaunchModeBasicActivity {

    @Override
    protected void onResume() {
        super.onResume();

        setTitle("StandardActivity");
        Log.i("StandardActivity", " TaskID:" + getTaskId());
    }
}
