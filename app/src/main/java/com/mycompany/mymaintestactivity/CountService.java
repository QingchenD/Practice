package com.mycompany.mymaintestactivity;

/**引入包*/
import android.app.Service;// 服务的类
import android.os.IBinder;
import android.os.Binder;
import android.content.Intent;
import android.util.Log;


/**
 * Created by Qingweid on 2016/1/6.
 */


/** 计数的服务 */
public class CountService extends Service {
    /** 创建参数 */
    boolean threadDisable;
    int count;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        Log.v("CountService", " onCreate() threadDisable:" + threadDisable);
        /** 创建一个线程，每秒计数器加一，并在控制台进行Log输出 */
        new Thread(new Runnable() {
            public void run() {
                while (!threadDisable) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {

                    }
                    count++;
                    Log.v("CountService", "Count is:" + count);
                }
            }
        }).start();
    }

    public void onDestroy() {
        super.onDestroy();
        /** 服务停止时，终止计数进程 */
        this.threadDisable = true;
    }

    public int getConunt() {
        return count;
    }

//此方法是为了可以在Acitity中获得服务的实例

    class ServiceBinder extends Binder {
        public CountService getService() {
            return CountService.this;
        }
    }
}
