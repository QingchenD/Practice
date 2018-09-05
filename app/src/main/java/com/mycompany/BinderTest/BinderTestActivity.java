package com.mycompany.BinderTest;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mycompany.mymaintestactivity.R;

public class BinderTestActivity extends Activity {

    private BinderService mService;

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service instanceof BinderService.LocalBinder) {
                mService = ((BinderService.LocalBinder) service).getService();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
                mService = null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.binder_test_layout);

        final TextView tv = (TextView) findViewById(R.id.tv_service_rt);
        Button btnDoWork = (Button) findViewById(R.id.btn_do_work);
        btnDoWork.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if (mService != null) {
                    tv.setText(mService.getContent());
                }
            }
        });

        Intent intent = new Intent();
        intent.setClass(this, BinderService.class);
        bindService(intent, mConn, Service.BIND_AUTO_CREATE);
    }
}
