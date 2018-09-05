package com.mycompany.net;

import android.util.Log;

import com.mycompany.util.Util;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class TcpServer {
    private static final String TAG = "TcpServer";

    private static ChannelFuture mChannel;
    private static final String IP = Util.ipToString(Util.getIp());//"127.0.0.1";
    private static final int PORT = 6166;
    /** 用于分配处理业务线程的线程组个数 */
    protected static final int BIZGROUPSIZE = Runtime.getRuntime()
            .availableProcessors() * 2; // 默认
    /** 业务出现线程大小 */
    protected static final int BIZTHREADSIZE = 4;
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);

    public static void run() {
        ServerBootstrap b = new ServerBootstrap();
        try {
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
//                    pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
//                    pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
//                    pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
//                    pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                    pipeline.addLast(new TcpServerHandler());
                }
            });

            mChannel = b.bind(IP, PORT).sync();
            Log.i(TAG, "TCP服务器已启动" + " listen on:" + mChannel.channel().localAddress());
            mChannel.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bossGroup.shutdownGracefully().sync();//关闭EventLoopGroup，释放掉所有资源包括创建的线程
                workerGroup.shutdownGracefully().sync();//关闭EventLoopGroup，释放掉所有资源包括创建的线程
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected static void shutdown() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

//    public static void main(String[] args) throws Exception {
//        Log.i(TAG, "开始启动TCP服务器...");
//        TcpServer.run();
//        // TcpServer.shutdown();
//    }
}

