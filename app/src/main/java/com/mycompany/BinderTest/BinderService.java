package com.mycompany.BinderTest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class BinderService extends Service {


    private LocalBinder localBinder = new LocalBinder();
    private String me = "This is come from server";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class LocalBinder extends Binder {
        public BinderService getService() {
            return BinderService.this;
        }
    }

    public  String getContent() {
        return me;
    }
}
