package com.hw.demo15;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.core.ChannelHandlerContext;
import org.jboss.netty.channel.core.impl.SimpleChannelHandler;
import org.jboss.netty.channel.event.MessageEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Lee on 2017/6/24.
 */
public class FileServerHandler extends SimpleChannelHandler {

    private File file = new File("D:/data/dataServer.txt");
    private FileOutputStream fos;

    public FileServerHandler() {
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
            throws Exception {
        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        int length = buffer.readableBytes();
        buffer.readBytes(fos, length);
        fos.flush();
        buffer.clear();
    }

}
