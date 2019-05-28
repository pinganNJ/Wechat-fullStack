package cn.nupt.wechat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @AUTHOR PizAn
 * @Author 实现客户端发送一个请求，服务端返回 hello netty
 * @CREAET 2019-05-28 13:51
 */


public class HelloNetty {

    public static void main(String[] args) throws Exception {


        //定义一对线程组，主线程组用于接受客户端链接，但是不做任何处理，从线程组，从主线程组那边接受任务
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            //netty服务器的创建，ServerBootstrap是一个启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)  //设置主从线程组
                    .channel(NioServerSocketChannel.class)  //设置nio的双向通道
                    .childHandler(new HelloServerInitializer());  //子处理器，用于处理workGroup

            //启动server,设置8088为端口号，启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();

            //监听关闭的channel,设置为同步方式
            channelFuture.channel().closeFuture().sync();
        } finally {
            //优雅地关闭两个线程组
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();

        }


    }
}
