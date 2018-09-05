package com.mycompany.mymaintestactivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.widget.TextView;

/**
 * Created by Qingweid on 2016/10/9.
 */
public class PhoneInfoActivity extends Activity {

    private TextView tvPhoneInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.phone_info_layout);
        tvPhoneInfo = (TextView) findViewById(R.id.tv_phone_info);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String phoneInfo = "Product: " + android.os.Build.PRODUCT + "\n";
        phoneInfo += "CPU_ABI: " + android.os.Build.CPU_ABI + "\n";
        phoneInfo += "TAGS: " + android.os.Build.TAGS + "\n";
        phoneInfo += "VERSION_CODES.BASE: "+ android.os.Build.VERSION_CODES.BASE + "\n";
        phoneInfo += "MODEL: " + android.os.Build.MODEL + "\n";
        phoneInfo += "SDK: " + android.os.Build.VERSION.SDK + "\n";
        phoneInfo += "VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE+ "\n";
        phoneInfo += "DEVICE: " + android.os.Build.DEVICE + "\n";
        phoneInfo += "DISPLAY: " + android.os.Build.DISPLAY + "\n";
        phoneInfo += "BRAND: " + android.os.Build.BRAND + "\n";
        phoneInfo += "BOARD: " + android.os.Build.BOARD + "\n";
        phoneInfo += "FINGERPRINT: " + android.os.Build.FINGERPRINT + "\n";
        phoneInfo += "ID: " + android.os.Build.ID + "\n";
        phoneInfo += "MANUFACTURER: " + android.os.Build.MANUFACTURER + "\n";
        phoneInfo += "USER: " + android.os.Build.USER + "\n";
        phoneInfo += "BOOTLOADER: " + android.os.Build.BOOTLOADER + "\n";
        phoneInfo += "HARDWARE: " + android.os.Build.HARDWARE + "\n";
        phoneInfo += "INCREMENTAL: " + android.os.Build.VERSION.INCREMENTAL+ "\n";
        phoneInfo += "CODENAME: " + android.os.Build.VERSION.CODENAME + "\n";
        phoneInfo += "SDK: " + android.os.Build.VERSION.SDK_INT + "\n";

//        TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
//            try {
//                phoneInfo += "DeviceID: " + tm.getDeviceId() + "\n";
//                phoneInfo += "Telphone Number: " + tm.getLine1Number() + "\n"; //手机号码
//                phoneInfo += "imei:" + tm.getSimSerialNumber() + "\n" ;
//                phoneInfo += "imsi:" + tm.getSubscriberId() + "\n";
//            } catch (Exception e) {
//
//            }
//        }

        tvPhoneInfo.setText(phoneInfo);
    }
}
