package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Qingweid on 2016/5/9.
 */
public class ServiceBestPracticeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, LongRunningService.class);
        startService(intent);
    }
}
