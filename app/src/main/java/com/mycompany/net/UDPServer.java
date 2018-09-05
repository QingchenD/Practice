package com.mycompany.net;

import android.util.Log;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * Created by Qingweid on 2016/10/21.
 */

public class UDPServer {

    final String TAG = "UDPServer";
    /**
     * @throws InterruptedException
     */
    public void start() throws InterruptedException {
        // TODO Auto-generated method stub
        Bootstrap b = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        b.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new UDPSeverHandler());

        b.bind(6166).sync().channel().closeFuture().await();

        Log.i(TAG, " start UDPServer Success!!!");
    }

}