package com.mycompany.net;

import android.util.Log;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class TcpClient {
    private final String TAG = "TcpClient";

    public static String HOST = "192.168.43.255";
    public static int PORT = 6166;

    public Bootstrap bootstrap ;
    public Channel channel ;

    public TcpClient() {
        bootstrap = getBootstrap();
        channel = getChannel(HOST, PORT);
    }

    /**
     * 初始化Bootstrap
     * 
     * @return
     */
    public Bootstrap getBootstrap() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("frameDecoder",new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0,4, 0, 4));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("decoder",new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast("encoder",new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast("handler", new TcpClientHandler());
            }
        });
        b.option(ChannelOption.SO_KEEPALIVE, true);
        return b;
    }

    public Channel getChannel(String host, int port) {
        Channel channel = null;
        try {
            channel = bootstrap.connect(host, port).sync().channel();
        } catch (Exception e) {
            Log.e(TAG, String.format("连接Server(IP[%s],PORT[%s])失败", host, port));
            e.printStackTrace();
            return null;
        }
        return channel;
    }

    public void sendMsg(String msg) throws Exception {
        if (channel != null) {
            channel.writeAndFlush(msg).sync();
        } else {
            Log.w(TAG,"消息发送失败,连接尚未建立!");
        }
    }

//    public static void main(String[] args) throws Exception {
//        try {
//            long t0 = System.nanoTime();
//            for (int i = 0; i < 1000; i++) {
//                TcpClient.sendMsg(i + "你好1");
//            }
//            long t1 = System.nanoTime();
//            System.out.println((t1 - t0) / 1000000.0);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}

