package com.hw.demo8;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.core.ChannelPipeline;
import org.jboss.netty.channel.core.ChannelPipelineFactory;
import org.jboss.netty.channel.core.Channels;
import org.jboss.netty.channel.socket.nio.client.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * Created by Lee on 2017/6/24.
 */
public class MessageClient {

    public static void main(String args[]) {
        // Client服务启动器
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
        // 设置一个处理服务端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(
                        new ObjectBufferClientHandler());
            }
        });
        // 连接到本地的8000端口的服务端
        bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));
    }
}