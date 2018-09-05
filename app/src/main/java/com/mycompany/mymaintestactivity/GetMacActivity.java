package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.net.NetworkInterface;
import java.net.SocketException;

/**
 * Created by Qingweid on 2016/11/2.
 */

public class GetMacActivity extends Activity  implements View.OnClickListener{

    private final String TAG = "GetMacActivity";

    private TextView tvMac1 , tvMac2 , tvMac3;
    Button btnGetMac1, btnGetMac2, btnGetMac3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_mac_layout);

        tvMac1 = (TextView) findViewById(R.id.tv_mac_1);
        tvMac2 = (TextView) findViewById(R.id.tv_mac_2);
        tvMac3 = (TextView) findViewById(R.id.tv_mac_3);

        btnGetMac1 = (Button) findViewById(R.id.btn_mac_1);
        btnGetMac2 = (Button) findViewById(R.id.btn_mac_2);
        btnGetMac3 = (Button) findViewById(R.id.btn_mac_3);

        btnGetMac1.setOnClickListener(this);
        btnGetMac2.setOnClickListener(this);
        btnGetMac3.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

//    public String getMacAddress()
//    {
//        String macAddress = SystemProperties.get("ubootenv.var.ethaddr");
//
//        String[] strings = macAddress.toLowerCase().split(":");
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < strings.length; i++)
//        {
//            stringBuilder.append(strings[i]);
//        }
//        Log.i(TAG, "--- DVB Mac Address : " + stringBuilder.toString());
//        return stringBuilder.toString();
//    }

    public String getMacAddress2()
    {
        String mac = "";
        // 获取wifi管理器
        WifiManager wifiMng = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfor = wifiMng.getConnectionInfo();
        mac = wifiInfor.getMacAddress();
        Log.i(TAG, "--- DVB Mac Address : " + mac);

        tvMac2.setText(mac);
        return mac;
    }

    private String getMacAddress3(String netInterface) {
        String strMacAddr = "";
        byte[] b;
        try {
            NetworkInterface NIC = NetworkInterface.getByName(netInterface) ; //"wlan0");
            b = NIC.getHardwareAddress();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < b.length; i++)
            {
//                if (i != 0)
//                {
//                    buffer.append(':');
//                }
                String str = Integer.toHexString(b[i] & 0xFF);
                buffer.append(str.length() == 1 ? 0 + str : str);
            }
            strMacAddr = buffer.toString().toUpperCase();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "--- DVB Mac Address : " + strMacAddr);


        tvMac3.setText(strMacAddr);

        return strMacAddr;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_mac_1:
                //do nothing
                getMacAddress3("p2p0");
                break;

            case R.id.btn_mac_2:
                getMacAddress2();
                break;

            case R.id.btn_mac_3:
                getMacAddress3("wlan0");
                break;

            default:
                break;
        }
    }
}
