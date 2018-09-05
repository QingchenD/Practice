package com.mycompany.aidlTest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MyAidlService extends Service {
    private final String TAG = this.getClass().getSimpleName();

    private ArrayList<Person> mPersons;
    private int mStartID;

    /**
     * 创建生成的本地 Binder 对象，实现 AIDL 制定的方法
     */
    private IBinder mIBinder = new IMyAidlInterface.Stub() {

        @Override
        public void addPerson(Person person) throws RemoteException {
            mPersons.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return mPersons;
        }

        @Override
        public void stopServiceImmediately() throws RemoteException {
            Log.d(TAG, "MyAidlService stopServiceImmediately " + mStartID);


        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyAidlService onCreate");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {

                }
                stopSelfResult(1);
            }
        }).start();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d(TAG, "MyAidlService onStart() startId:" + startId);
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "MyAidlService onStartCommand startId:" + startId);
        mStartID = startId;
        int ret = super.onStartCommand(intent, flags, startId);
        Log.d(TAG, "MyAidlService onStartCommand ret:" + ret);
        return START_NOT_STICKY;
    }

    /**
     * 客户端与服务端绑定时的回调，返回 mIBinder 后客户端就可以通过它远程调用服务端的方法，即实现了通讯
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mPersons = new ArrayList<>();
        Log.d(TAG, "MyAidlService onBind");
        return mIBinder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "MyAidlService onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "MyAidlService onUnbind");
        super.onUnbind(intent);

        return true;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "MyAidlService onDestroy");
        super.onDestroy();
    }
}