package com.mycompany.net;

import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by Qingweid on 2016/10/21.
 */

public class UDPClient  {
    private final String TAG = "UDPClient";
    private String address = "192.168.43.255";
    private int port = 6166;
    // 创建发送方的套接字，IP为发送方广播地址（如192.168.1.255），端口号随机

    public void sendMessage(int port,String address, String msg) throws Exception {

        // 确定要发送的消息
        DatagramSocket sendSocket = new DatagramSocket();
        // 由于数据报的数据是以字符数组传的形式存  储的，所以传转数据
        byte[] buf = msg.getBytes("UTF-8");
        // 确定发送方的IP地址及端口号，地址为本地网络
        InetAddress ip = InetAddress.getByName(address);
        // 创建发送类型的数据报
        DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, ip, port);
        // 通过套接字发送数据
        sendSocket.send(sendPacket);
        sendSocket.close();
        Log.i(TAG, "send msg:" + msg + " success!!!");

    }
}
