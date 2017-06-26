package com.hw.demo3;

import com.hw.demo4.ServerBufferHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.core.ChannelHandlerContext;
import org.jboss.netty.channel.core.ChannelPipeline;
import org.jboss.netty.channel.core.ChannelPipelineFactory;
import org.jboss.netty.channel.core.Channels;
import org.jboss.netty.channel.core.impl.SimpleChannelHandler;
import org.jboss.netty.channel.event.MessageEvent;
import org.jboss.netty.channel.socket.nio.server.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

/**
 * Created by Lee on 2017/6/24.
 */
public class MessageServer {

    public static void main(String args[]) {
        // Server服务启动器
        // 设置channelFactory
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        // 设置一个处理客户端消息和各种消息事件的类(Handler)
        // 设置PipelineFactory
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new ServerBufferHandler());
            }
        });
        // 开放8000端口供客户端访问。
        bootstrap.bind(new InetSocketAddress(8000));
    }

    private static class MessageServerHandler extends SimpleChannelHandler {

        /**
         * 用户接受客户端发来的消息，在有客户端消息到达时触发
         *
         * @author lihzh
         * @alia OneCoder
         */
        @Override
        public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
            ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
            System.out.println(buffer.toString(Charset.defaultCharset()));
        }

    }
}