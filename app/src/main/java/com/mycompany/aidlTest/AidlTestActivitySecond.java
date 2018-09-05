package com.mycompany.aidlTest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mycompany.mymaintestactivity.R;

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

public class AidlTestActivitySecond extends Activity {

    private static String TAG = "AidlTestActivitySecond";

    private IMyAidlInterface mAidl;

    @Bind(R.id.textViewResult)
    TextView mTvResult;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //连接后拿到 Binder，转换成 AIDL，在不同进程会返回个代理
            Log.i(TAG, " get binder");
            mAidl = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAidl = null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aidl_test_layout);

        mTvResult = (TextView) findViewById(R.id.textViewResult);
        mTvResult.setText(TAG);

        Button btn = (Button) findViewById(R.id.btn_add_person);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPerson();
            }
        });

        btn = (Button) findViewById(R.id.btn_start_service);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i(TAG, " startService");
                Intent intent1 = new Intent(getApplicationContext(), MyAidlService.class);
                startService(intent1);
            }
        });

        btn = (Button) findViewById(R.id.btn_stop_service);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i(TAG, " stopService");
                Intent intent1 = new Intent(getApplicationContext(), MyAidlService.class);
                stopService(intent1);
            }
        });

        btn = (Button) findViewById(R.id.btn_bind_service);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i(TAG, " bindService");
                Intent intent1 = new Intent(getApplicationContext(), MyAidlService.class);
                bindService(intent1, mConnection, BIND_AUTO_CREATE);
            }
        });

        btn = (Button) findViewById(R.id.btn_unbind_service);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i(TAG, " unbindService");
                //  Intent intent1 = new Intent(getApplicationContext(), MyAidlService.class);
                unbindService(mConnection);
            }
        });

        //Intent intent1 = new Intent(getApplicationContext(), MyAidlService.class);
        //bindService(intent1, mConnection, BIND_AUTO_CREATE);
    }

    @OnClick(R.id.btn_add_person)
    public void addPerson() {
        Random random = new Random();
        Person person = new Person("AidlTest" + random.nextInt(10), random.nextInt(30));

        try {
            mAidl.addPerson(person);
            List<Person> personList = mAidl.getPersonList();
            mTvResult.setText(personList.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
