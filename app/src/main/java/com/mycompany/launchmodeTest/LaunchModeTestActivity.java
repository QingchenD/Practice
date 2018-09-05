package com.mycompany.launchmodeTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mycompany.mymaintestactivity.R;

/**
 * Created by Qingweid on 2018/3/30.
 */

public class LaunchModeTestActivity extends LaunchModeBasicActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setTitle("LaunchModeTestActivity");
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

}
