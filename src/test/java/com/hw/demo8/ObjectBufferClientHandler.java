package com.hw.demo8;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.impl.ChannelBuffers;
import org.jboss.netty.channel.core.Channel;
import org.jboss.netty.channel.core.ChannelHandlerContext;
import org.jboss.netty.channel.core.impl.SimpleChannelHandler;
import org.jboss.netty.channel.event.ChannelStateEvent;

/**
 * Created by Lee on 2017/6/24.
 */
public class ObjectBufferClientHandler extends SimpleChannelHandler {

    /**
     * 当绑定到服务端的时候触发，给服务端发消息。
     *
     * @alia OneCoder
     * @author lihzh
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        // 分段发送信息
        sendObject(e.getChannel());
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
    private void sendObject(Channel channel) {
        Command command = new Command();
        command.setActionName("Hello action");
        channel.write(command);
    }
}