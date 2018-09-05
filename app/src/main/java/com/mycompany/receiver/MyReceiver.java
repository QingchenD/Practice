package com.mycompany.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.mycompany.mymaintestactivity.MainActivity;

/**
 * Created by Qingweid on 2017/11/17.
 */

public class MyReceiver extends BroadcastReceiver {
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
            Toast.makeText(context, "Reboot Broadcast", Toast.LENGTH_LONG).show();
            Intent tmpIntent = new Intent(context, MainActivity.class);
            context.startActivity(tmpIntent);
        }
    }
}
