package com.hw.demo15;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.impl.ChannelBuffers;
import org.jboss.netty.channel.core.Channel;
import org.jboss.netty.channel.core.ChannelHandlerContext;
import org.jboss.netty.channel.core.impl.SimpleChannelHandler;
import org.jboss.netty.channel.event.ChannelStateEvent;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Lee on 2017/6/24.
 */
public class FileClientHandler extends SimpleChannelHandler {

    // 每次处理的字节数
    private int readLength = 8;

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
            throws Exception {
        // 发送文件
        sendFile(e.getChannel());
    }

    private void sendFile(Channel channel) throws IOException {
        File file = new File("D:/data/data.txt");
        FileInputStream fis = new FileInputStream(file);
        int count = 0;
        BufferedInputStream bis = new BufferedInputStream(fis);
        for (; ; ) {
            byte[] bytes = new byte[readLength];
            int readNum = bis.read(bytes, 0, readLength);
            if (readNum == -1) {
                return;
            }
            sendToServer(bytes, channel, readNum);
            System.out.println("Send count: " + ++count);
        }

    }

    private void sendToServer(byte[] bytes, Channel channel, int length)
            throws IOException {
        ChannelBuffer buffer = ChannelBuffers.copiedBuffer(bytes, 0, length);
        channel.write(buffer);
    }

}
