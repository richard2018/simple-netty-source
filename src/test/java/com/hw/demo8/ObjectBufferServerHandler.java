package com.hw.demo8;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.core.ChannelHandlerContext;
import org.jboss.netty.channel.core.impl.SimpleChannelHandler;
import org.jboss.netty.channel.event.MessageEvent;

import java.nio.charset.Charset;

/**
 * Created by Lee on 2017/6/24.
 */
public class ObjectBufferServerHandler extends SimpleChannelHandler {
    /**
     * 用户接受客户端发来的消息，在有客户端消息到达时触发
     * @author lihzh
     * @alia OneCoder
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        Command command = (Command) e.getMessage();
        // 打印看看是不是我们刚才传过来的那个
        System.out.println(command.getActionName());
    }
}