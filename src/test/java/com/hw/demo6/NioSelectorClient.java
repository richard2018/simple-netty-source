package com.hw.demo6;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by Lee on 2017/6/24.
 */
public class NioSelectorClient {
    /**
     * @throws IOException
     * @author lihzh
     * @alia OneCoder
     */
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("127.0.0.1", 8000));
    }
}
