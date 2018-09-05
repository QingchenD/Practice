package com.mycompany.net;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Qingweid on 2016/10/19.
 */

public class NetServerActivity extends Activity {
    private final String TAG = "NetServerActivity";

    private final int SERVER_TCP_PORT = 6166;
    private final String SERVER_IP = "127.0.0.1";

    private final int THREADSIZE = 10;

    private TcpServer tcpServer;
    private UDPServer udpServer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        try {
//            TcpServer.run();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        udpServer = new UDPServer();
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            udpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        TcpServer.shutdown();
    }


    public boolean startTCP() {
        boolean isStart = false;
        NioEventLoopGroup bossGroup = null;
        NioEventLoopGroup workerGroup = null;
        ServerBootstrap bootstrap = null;
        try {
            bossGroup = new NioEventLoopGroup();
            workerGroup = new NioEventLoopGroup(THREADSIZE);
            //DefaultEventExecutorGroup e2 = new DefaultEventExecutorGroup(8);
            bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
//            bootstrap.childHandler(
//                    new ChannelInitializer() {
//                        @Override
//                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ChannelPipeline pipeline = ch.pipeline();
//                            //pipeline.addLast("frameDecoder",new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
//                            //pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
//                            //pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
//                            //pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
//                            pipeline.addLast(new TcpServerHandler());
//                        }
//                    }
//            );
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.option(ChannelOption.SO_BACKLOG, 128);
            ChannelFuture cf = bootstrap.bind(SERVER_IP, SERVER_TCP_PORT).sync();
            isStart = true ;
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            Log.i(TAG,"tcp start false:"+ SERVER_TCP_PORT);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
        return isStart;
    }
}
