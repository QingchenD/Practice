package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by Qingweid on 2015/12/10.
 */
public class StartOneActivityForResult extends Activity {
    private final String TAG = "StartOneActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, " Mydebug" + "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_one_activity_for_result_layout);
    }

    @Override
    protected void onStart() {
        Log.i(TAG, " Mydebug" + "onStart()");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, " Mydebug" + "onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, " Mydebug" + "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, " Mydebug" + "onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, " Mydebug" + "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, " Mydebug" + "onDestroy()");
        super.onDestroy();
    }

    public void OnFinish (View view) {
        Intent intent  = new Intent();
        intent.putExtra("result","Back from " + this.getClass().getSimpleName() + " !" + "*********************************************");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
