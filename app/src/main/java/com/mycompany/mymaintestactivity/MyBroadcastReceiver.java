package com.mycompany.mymaintestactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Qingweid on 2016/3/28.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
//        abortBroadcast();

    }
}
