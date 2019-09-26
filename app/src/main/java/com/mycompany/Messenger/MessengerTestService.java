package com.mycompany.Messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

import org.jetbrains.annotations.Nullable;

public class MessengerTestService extends Service {

    private static final String TAG = "MessengerTestService";
    private Handler mHandler =  new MessengerHandler();
    private Messenger mMessenger = new Messenger(mHandler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    private static class MessengerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MessengerTestActivity.MESSENGER_TEST:
                    Log.i(TAG, "Receive a message from client");
                    if (msg.replyTo != null) {
                        Message message = Message.obtain();
                        message.what = MessengerTestActivity.MESSENGER_TEST_RECEIVE;
                        Bundle bundle = new Bundle();
                        bundle.putString("Response", "Hi user, Your message has received");
                        message.setData(bundle);
                        message.obj = "Hi user, Your message has received not use bundle";
                        try {
                            msg.replyTo.send(message);
                            Log.i(TAG, " Send Message to client");
                        } catch (Exception e) {
                            Log.i("Error", e.getStackTrace().toString());
                        }
                    }
                    break;

                default:
                    break;
            }
        }
    }
}
