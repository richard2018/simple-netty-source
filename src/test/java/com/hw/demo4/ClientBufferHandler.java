package com.hw.demo4;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.impl.ChannelBuffers;
import org.jboss.netty.channel.core.ChannelHandlerContext;
import org.jboss.netty.channel.core.impl.SimpleChannelHandler;
import org.jboss.netty.channel.event.ChannelStateEvent;

/**
 * Created by Lee on 2017/6/24.
 */
public class ClientBufferHandler extends SimpleChannelHandler {

    /**
     * 当绑定到服务端的时候触发，给服务端发消息。
     *
     * @alia OneCoder
     * @author lihzh
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        // 分段发送信息
        sendMessageByFrame(e);
    }

    /**
     * 将<b>"Hello, I'm client."</b>分成三份发送。 <br>
     * Hello, <br>
     * I'm<br>
     * client.<br>
     *
     * @param e
     *            Netty事件
     * @author lihzh
     */
    private void sendMessageByFrame(ChannelStateEvent e) {
        String msgOne = "Hello, ";
        String msgTwo = "I'm ";
        String msgThree = "client.";
        String msgDemo = "test1234567";
        e.getChannel().write(tranStr2Buffer(msgOne));
        e.getChannel().write(tranStr2Buffer(msgTwo));
        e.getChannel().write(tranStr2Buffer(msgThree));
        e.getChannel().write(tranStr2Buffer(msgDemo));
    }

    /**
     * 将字符串转换成{@link ChannelBuffer}，私有方法不进行字符串的非空判断。
     *
     * @param str
     *            待转换字符串，要求非null
     * @return 转换后的ChannelBuffer
     * @author lihzh
     */
    private ChannelBuffer tranStr2Buffer(String str) {
        ChannelBuffer buffer = ChannelBuffers.buffer(str.length());
        buffer.writeBytes(str.getBytes());
        return buffer;
    }
}