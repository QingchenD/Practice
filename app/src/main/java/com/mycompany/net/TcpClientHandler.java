package com.mycompany.net;


import android.util.Log;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TcpClientHandler extends SimpleChannelInboundHandler<Object> {
    private final String TAG = "TcpClientHandler";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // messageReceived方法,名称很别扭，像是一个内部方法.
        Log.i(TAG, "client接收到服务器返回的消息:" + msg);

    }

}