package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Qingweid on 2016/7/27.
 */
public class UsbTestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUsbInfo();
    }

    public void getUsbInfo () {
        try {

            //获得外接USB输入设备的信息
            Process p=Runtime.getRuntime().exec("cat /proc/bus/input/devices");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = in.readLine())!= null) {
                String deviceInfo = line.trim();
                //对获取的每行的设备信息进行过滤，获得自己想要的。
                System.out.println(deviceInfo);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
