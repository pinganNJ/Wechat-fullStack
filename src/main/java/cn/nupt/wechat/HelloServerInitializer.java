package cn.nupt.wechat;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

import java.nio.channels.Channel;

/**
 * 初始化器，channel注册后，会执行里面的相应的初始化方法
 *
 * @AUTHOR PizAn
 * @CREAET 2019-05-28 14:47
 */

public class HelloServerInitializer  extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();

        //通过管道，添加handler
        //HttpServerCodec是由netty自己提供的助手类，可以理解为拦截器
        //当请求到服务器时，解码，再相应到客户端做编码
        pipeline.addLast("HttpServerCodec",new HttpServerCodec());

        //添加自定义的助手类，返回“hello netty”
        pipeline.addLast("customHandler",null);

    }
}
