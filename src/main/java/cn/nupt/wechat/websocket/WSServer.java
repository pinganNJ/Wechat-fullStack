package cn.nupt.wechat.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @AUTHOR PizAn
 * @CREAET 2019-05-28 16:45
 */

public class WSServer {
    public static void main(String[] args) throws Exception {

        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup subGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap server = new ServerBootstrap();

            server.group(mainGroup, subGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WSServerInitializer());

            ChannelFuture future = server.bind(8088).sync();

            future.channel().closeFuture().sync();

        } finally {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();

        }


    }


}
