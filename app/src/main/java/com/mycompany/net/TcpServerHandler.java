package com.mycompany.net;

import android.util.Log;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public class TcpServerHandler extends SimpleChannelInboundHandler<Object> {

    private final String TAG = "TcpServerHandler";

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        Log.i(TAG, "SERVER接收到消息:" + msg);
        ctx.channel().writeAndFlush("yes, server is accepted you ,nice !" + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        Log.w(TAG, "Unexpected exception from downstream.", cause);
        ctx.close();
    }
}