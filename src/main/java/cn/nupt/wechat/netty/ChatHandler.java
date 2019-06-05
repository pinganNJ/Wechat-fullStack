package cn.nupt.wechat.netty;





import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * 自定义的handler
 *
 * @AUTHOR PizAn
 * @CREAET 2019-05-29 11:19
 */


/**
 * @Description //处理消息的handler
 * @Param TextWebSocketFrame：在netty中，是用于为WebSocket专门处理文本的对象，frame是消息的载体
 * @return
 **/

public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    //用于记录和管理所以客户端的channel
    private static ChannelGroup clients =
            new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        //获取客户端传来的信息
        String content = msg.text();
        System.out.println("接受到的数据是：" + content);

/*        for (Channel channel : clients) {
            channel.writeAndFlush(
                    new TextWebSocketFrame(
                            "[服务器在]" + LocalDateTime.now()
                                    + "接受到消息，消息为:" + content)
            );

        }*/

        //和上面的for循环效果是一样的
        clients.writeAndFlush(
                new TextWebSocketFrame(
                        "[服务器在]" + LocalDateTime.now()
                                + "接受到消息，消息为:" + content)
        );


    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //当客户端链接服务器后（打开链接），获取科客户端的channel,并且放到channelGroup中进行管理
        clients.add(ctx.channel());

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        //当触发handleRemoved,channelGroup会自动移除对应客户端的channel,clients.remove(ctx.channel())
        System.out.println("客户端断开，chanel对应的长id为：" + ctx.channel().id().asLongText());
        System.out.println("客户端断开，chanel对应的短id为：" + ctx.channel().id().asShortText());
    }
}
