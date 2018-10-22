package com.mycompany.util;


import android.content.res.Resources;
import android.util.TypedValue;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created by Qingweid on 2016/10/20.
 */

public class Util {

    public static int getIp() {
        int ipaddress = 0;//String ipaddress = "";
        try {
            Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface nif = en.nextElement();
                Enumeration<InetAddress> inet = nif.getInetAddresses();
                while (inet.hasMoreElements()) {
                    InetAddress ip = inet.nextElement();
                    if (!ip.isLoopbackAddress()) { //&& InetAddressUtils.isIPv4Address(ip.getHostAddress())) {


                        byte[] addrBytes;
                        int addr;
                        addrBytes = ip.getAddress();
                        addr = ((addrBytes[3] & 0xff) << 24)| ((addrBytes[2] & 0xff)<< 16)
                                | ((addrBytes[1] & 0xff) << 8)
                                | (addrBytes[0] & 0xff);
                        return addr;

                        //return ipaddress = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipaddress;
    }

    public static String ipToString(int ipAddress) {
        return  ((ipAddress & 0xff) + "." + (ipAddress >> 8 & 0xff) + "."
                + (ipAddress >> 16 & 0xff) + "." + (ipAddress >> 24 & 0xff));
    }

    public static int dp2px(float dp) {
        return  (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, dp, Resources.getSystem().getDisplayMetrics());
    }
}
