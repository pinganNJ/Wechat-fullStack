package cn.nupt.wechat.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @AUTHOR PizAn
 * @CREAET 2019-05-29 10:24
 */

public class WSServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();

        //http编解码器，netty自带，websocket基于http协议
        pipeline.addLast(new HttpServerCodec());
        //对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        //对heepMessage进行聚合，聚合成FullHttpRequest或FullHttpResponse
        //几乎再netty中的编程，都会使用到这个handler
        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

        //====================以上是用于支持http协议的handler==================

        /*
         * @Author PizAn
         * @Description  WebSocket服务器处理的协议，用于指定给客户端链接访问的路由："/ws"
         * 这个handler会帮你处理一些繁重的复杂的事，比如处理握手动作：handshaking(close,ping,pong),
         * ping+pong = 心跳
         * 对于sebsocket来说，都是以frames进行传输的，不同的数据类型对应的frames也不同
         **/

        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

        //自定义handler
        pipeline.addLast(new ChatHandler());


    }
}
