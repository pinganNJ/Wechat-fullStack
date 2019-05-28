package cn.nupt.wechat;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * 创建自定义助手类
 *
 * @AUTHOR PizAn
 * @CREAET 2019-05-28 14:58
 */

//SimpleChannelInboundHandler:对于请求来讲，相当于"入站，入境"
public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {


    @Override
    protected void channelRead0(ChannelHandlerContext chx
            , HttpObject msg) throws Exception {
        //获取channel
        Channel channel = chx.channel();

        //这里打印一下客户端的远程地址
        System.out.println("dd：" + channel.remoteAddress());

        //定义发送的数据消息
        ByteBuf content = Unpooled.copiedBuffer("hello netty~", CharsetUtil.UTF_8);

        //构建一个http response
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                content);

        //为相应数据增加数据类型和长度
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());


        //把相应刷到客户端
        chx.writeAndFlush(msg);
    }
}
