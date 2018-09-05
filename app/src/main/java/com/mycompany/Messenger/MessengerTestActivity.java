package com.mycompany.Messenger;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mycompany.mymaintestactivity.R;


public class MessengerTestActivity extends Activity {

    private static final String TAG = "MessengerTestActivity";

    public static final int MESSENGER_TEST = 1;
    public static final int MESSENGER_TEST_RECEIVE = 2;

    private static TextView mTvResponse;

    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSENGER_TEST_RECEIVE:
                    Log.i(TAG, " Get Response from server");
                    Bundle bundle = msg.getData();
                    String response = bundle.getString("Response");
                    if (msg.obj instanceof  String) {
                        response += "\n" + msg.obj;
                    }
                    mTvResponse.setText(response);
                    break;

                default:
                    break;
            }
        }
    } ;

    private Messenger mLocalMessenger = new Messenger(mHandler) ;

    private Messenger mMessenger ;

    ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMessenger = null;
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messenger_test_layout);

        mTvResponse = (TextView) findViewById(R.id.tv_receive);

        Button btn = (Button) findViewById(R.id.btn_send_message);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Message message = Message.obtain();
                    message.replyTo = mLocalMessenger;
                    message.what = MESSENGER_TEST;
                    mMessenger.send(message);
                } catch (Exception e) {
                    Log.i("Error", e.getStackTrace().toString());
                }
            }
        });

        Intent intent = new Intent();
        intent.setClass(this , MessengerTestService.class);
        bindService(intent, mConn, Service.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(mConn);

        super.onDestroy();
    }
}
