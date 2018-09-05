package com.mycompany.mymaintestactivity;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mycompany.wifi.Wificonfig;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Qingweid on 2016/8/22.
 */
public class WiFiHotSpotActivity extends Activity {
    private WifiManager wifiManager;
    private Button open;
    private Button btnGetMac;
    private Button btnGetWifiConfig;

    private EditText editSSID;
    private EditText editKey;
    private TextView tvWifiConfig;
    private Wificonfig mWifiConfig;

//    @Bind(R.id.btn_connect_wifi)
    private Button btnConnectWifi;
    private EditText etWifiSSID;
    private EditText etWifiKey;

    private boolean flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotspot_layout);

        mWifiConfig = new Wificonfig(this);

        editSSID = (EditText) findViewById(R.id.edit_ssid);
        editKey = (EditText) findViewById(R.id.edit_key);
        btnGetMac = (Button) findViewById(R.id.get_mac);

        //获取wifi管理服务
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        open=(Button)findViewById(R.id.open_hotspot);
        //通过按钮事件设置热点
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果是打开状态就关闭，如果是关闭就打开
                //flag=!flag;
                boolean status = setWifiAp(flag);
                System.out.println("[Mydebug] " + (flag?"open":"close") + " : " + (status?"success":"failure"));
            }
        });

        btnGetMac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWifimac();
            }
        });

        btnGetWifiConfig = (Button) findViewById(R.id.btn_get_wificonfig);
        tvWifiConfig = (TextView) findViewById(R.id.tv_wifi_config);
        btnGetWifiConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiConfiguration config = getWifiConfig();
                String msg = "SSID:" + config.SSID + " key:" + config.preSharedKey + " bssid:" + config.BSSID ;
                tvWifiConfig.setText(msg);
            }
        });


        etWifiSSID = (EditText) findViewById(R.id.et_wifi_ssid);
        etWifiKey = (EditText) findViewById(R.id.et_wifi_key);
        btnConnectWifi = (Button) findViewById(R.id.btn_connect_wifi);
        btnConnectWifi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String ssid = etWifiSSID.getText().toString();
                String key = etWifiKey.getText().toString();
                mWifiConfig.selectedWifi(ssid, key, 4); //3
            }
        });
    }

    // wifi热点开关
    public boolean setWifiAp(boolean enabled) {
        if (!isWifiApEnabled()) { // disable WiFi in any case
            //wifi和热点不能同时打开，所以打开热点的时候需要关闭wifi
            wifiManager.setWifiEnabled(false);
        }
        try {
            //热点的配置类
            WifiConfiguration apConfig = new WifiConfiguration();
            //配置热点的名称(可以在名字后面加点随机数什么的)
            String ssid = editSSID.getText().toString();
            String key = editKey.getText().toString();
            if (!"".equals(ssid)) {
                apConfig.SSID = ssid;
            } else {
                apConfig.SSID = "HotSpotTest";
            }
            //配置热点的密码
            if (!"".equals(key)) {
                apConfig.preSharedKey = key;
            } else {
                apConfig.preSharedKey = "\"" + "123456" + "\"";
            }
            //通过反射调用设置热点
            Method method = wifiManager.getClass().getMethod(
                    "setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE);
            //返回热点打开状态
            return (Boolean) method.invoke(wifiManager, apConfig, enabled);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    private void getWifimac() {
        WifiManager wifiMan = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiMan.getConnectionInfo();
        String mac = info.getMacAddress();// 获得本机的MAC地址
        String ssid = info.getSSID();// 获得本机所链接的WIFI名称
        System.out.println("[Mydebug]" +" mac:" + mac + "SSID:" + ssid);


        int ipAddress = info.getIpAddress();
        String ipString = "";// 本机在WIFI状态下路由分配给的IP地址

        // 获得IP地址的方法一：
        if (ipAddress != 0) {
            ipString = ((ipAddress & 0xff) + "." + (ipAddress >> 8 & 0xff) + "."
                    + (ipAddress >> 16 & 0xff) + "." + (ipAddress >> 24 & 0xff));
            System.out.println("[Mydebug]" +"1 : ipString:" + ipString);
        }
        // 获得IP地址的方法二（反射的方法）：
        try {
            Field field = info.getClass().getDeclaredField("mIpAddress");
            field.setAccessible(true);
            ipString = (String) field.get(info);
            System.out.println("[Mydebug]" + "2 : ipString:" + ipString);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean isWifiApEnabled() {
        try {
            Method method = wifiManager.getClass().getMethod("isWifiApEnabled");
            method.setAccessible(true);
            return (Boolean) method.invoke(wifiManager);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public WifiConfiguration getWifiConfig() {

        if (true) {
            try {
                Method method = wifiManager.getClass().getMethod("getWifiApConfiguration");
                method.setAccessible(true);
                return  (WifiConfiguration) method.invoke(wifiManager);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    public class WifiAdmin {
        private final static String TAG = "WifiAdmin";
        private StringBuffer mStringBuffer = new StringBuffer();
        private List<ScanResult> listResult;
        private ScanResult mScanResult;
        // 定义WifiManager对象
        private WifiManager mWifiManager;
        // 定义WifiInfo对象
        private WifiInfo mWifiInfo;
        // 网络连接列表
        private List<WifiConfiguration> mWifiConfiguration;
        // 定义一个WifiLock
        WifiManager.WifiLock mWifiLock;

        /**
         * 构造方法
         */
        public WifiAdmin(Context context) {
            mWifiManager = (WifiManager) context
                    .getSystemService(Context.WIFI_SERVICE);
            mWifiInfo = mWifiManager.getConnectionInfo();
        }

        /**
         * 打开Wifi网卡
         */
        public void openNetCard() {
            if (!mWifiManager.isWifiEnabled()) {
                mWifiManager.setWifiEnabled(true);
            }
        }

        /**
         * 关闭Wifi网卡
         */
        public void closeNetCard() {
            if (mWifiManager.isWifiEnabled()) {
                mWifiManager.setWifiEnabled(false);
            }
        }

        /**
         * 检查当前Wifi网卡状态
         */
        public void checkNetCardState() {
            if (mWifiManager.getWifiState() == 0) {
                Log.i(TAG, "网卡正在关闭");
            } else if (mWifiManager.getWifiState() == 1) {
                Log.i(TAG, "网卡已经关闭");
            } else if (mWifiManager.getWifiState() == 2) {
                Log.i(TAG, "网卡正在打开");
            } else if (mWifiManager.getWifiState() == 3) {
                Log.i(TAG, "网卡已经打开");
            } else {
                Log.i(TAG, "没有获取到状态-_-");
            }
        }

        /**
         * 扫描周边网络
         */
        public void scan() {
            mWifiManager.startScan();
            listResult = mWifiManager.getScanResults();
            if (listResult != null) {
                Log.i(TAG, "当前区域存在无线网络，请查看扫描结果");
            } else {
                Log.i(TAG, "当前区域没有无线网络");
            }
        }

        /**
         * 得到扫描结果 获取无线信号列表
         */
        public String getScanResult() {
            // 每次点击扫描之前清空上一次的扫描结果
            if (mStringBuffer != null) {
                mStringBuffer = new StringBuffer();
            }
            // 开始扫描网络
            scan();
            listResult = mWifiManager.getScanResults();
            if (listResult != null) {
                for (int i = 0; i < listResult.size(); i++) {
                    mScanResult = listResult.get(i);
                    mStringBuffer = mStringBuffer.append("NO.").append(i + 1)
                            .append(" :").append(mScanResult.SSID).append("->")
                            .append(mScanResult.BSSID).append("->")
                            .append(mScanResult.capabilities).append("->")
                            .append(mScanResult.frequency).append("->")
                            .append(mScanResult.level).append("->")
                            .append(mScanResult.describeContents()).append("\n\n");
                }
            }
            Log.i(TAG, mStringBuffer.toString());
            return mStringBuffer.toString();
        }

        /**
         * 连接指定网络
         */
        public void connect() {

            mWifiInfo = mWifiManager.getConnectionInfo();
            if (mWifiInfo != null) {
                Log.d("wifiInfo", mWifiInfo.toString());
                Log.d("SSID", mWifiInfo.getSSID());
            }
        }

        /**
         * 断开当前连接的网络
         */
        public void disconnectWifi() {
            int netId = getNetworkId();
            mWifiManager.disableNetwork(netId);
            mWifiManager.disconnect();
            mWifiInfo = null;
        }

        /**
         * 检查当前网络状态
         *
         * @return String
         */
        public void checkNetWorkState() {
            if (mWifiInfo != null) {
                Log.i(TAG, "网络正常工作");
            } else {
                Log.i(TAG, "网络已断开");
            }
        }

        /**
         * 得到连接的ID
         */
        public int getNetworkId() {
            return (mWifiInfo == null) ? 0 : mWifiInfo.getNetworkId();
        }

        /**
         * 得到IP地址
         */
        public int getIPAddress() {
            return (mWifiInfo == null) ? 0 : mWifiInfo.getIpAddress();
        }

        // 锁定WifiLock
        public void acquireWifiLock() {
            mWifiLock.acquire();
        }

        // 解锁WifiLock
        public void releaseWifiLock() {
            // 判断时候锁定
            if (mWifiLock.isHeld()) {
                mWifiLock.acquire();
            }
        }

        // 创建一个WifiLock
        public void creatWifiLock() {
            mWifiLock = mWifiManager.createWifiLock("Test");
        }

        // 得到配置好的网络
        public List<WifiConfiguration> getConfiguration() {
            return mWifiConfiguration;
        }

        // 指定配置好的网络进行连接
        public void connectConfiguration(int index) {
            // 索引大于配置好的网络索引返回
            if (index >= mWifiConfiguration.size()) {
                return;
            }
            // 连接配置好的指定ID的网络
            mWifiManager.enableNetwork(mWifiConfiguration.get(index).networkId,
                    true);
        }

        // 得到MAC地址
        public String getMacAddress() {
            return (mWifiInfo == null) ? "NULL" : mWifiInfo.getMacAddress();
        }

        // 得到接入点的BSSID
        public String getBSSID() {
            return (mWifiInfo == null) ? "NULL" : mWifiInfo.getBSSID();
        }

        // 得到WifiInfo的所有信息包
        public String getWifiInfo() {
            return (mWifiInfo == null) ? "NULL" : mWifiInfo.toString();
        }

        // 添加一个网络并连接
        public int addNetwork(WifiConfiguration wcg) {
            int wcgID = mWifiManager.addNetwork(mWifiConfiguration.get(3));
            mWifiManager.enableNetwork(wcgID, true);
            return wcgID;
        }
    }

}
