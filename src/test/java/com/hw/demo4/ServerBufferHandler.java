package com.hw.demo4;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.core.ChannelHandlerContext;
import org.jboss.netty.channel.core.impl.SimpleChannelHandler;
import org.jboss.netty.channel.event.MessageEvent;

import java.nio.charset.Charset;

/**
 * Created by Lee on 2017/6/24.
 */
public class ServerBufferHandler extends SimpleChannelHandler {

    /**
     * 用户接受客户端发来的消息，在有客户端消息到达时触发
     *
     * @author lihzh
     * @alia OneCoder
     */
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        // 五位读取
        while (buffer.readableBytes() >= 5) {
            ChannelBuffer tempBuffer = buffer.readBytes(buffer.readableBytes());
            System.out.println(tempBuffer.toString(Charset.defaultCharset()));
        }
        // 读取剩下的信息
        System.out.println(buffer.toString(Charset.defaultCharset()));
    }

}