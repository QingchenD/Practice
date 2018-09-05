package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Qingweid on 2016/1/5.
 */
public class VirbrateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.virbrate_test_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    public void onTriggerVirbrate(View v) {

        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        getSystemService(VIBRATOR_SERVICE);  //获得一个震动的服务

        //震动5秒
        vibrator.vibrate(5000);

    }


    public void onCancelVirbrate(View v) {

        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        getSystemService(VIBRATOR_SERVICE);  //获得一个震动的服务

        vibrator.cancel();

    }
}
