package com.mycompany.launchmodeTest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Qingweid on 2018/3/30.
 */

public class SingleInstanceActivity extends LaunchModeBasicActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitle("SingleInstanceActivity");
        Log.i("SingleInstanceActivity", " TaskID:" + getTaskId());
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }
}
