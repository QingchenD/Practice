package com.mycompany.broadcastbestpractice;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Qingweid on 2016/3/28.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
