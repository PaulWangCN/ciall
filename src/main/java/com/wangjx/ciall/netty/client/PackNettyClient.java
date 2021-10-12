package com.wangjx.ciall.netty.client;

import com.wangjx.ciall.netty.common.StarDecoder;
import com.wangjx.ciall.netty.common.StarEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @ClassName: PackNettyClient
 * @Description: 自定义协议netty客户端
 * @Author: wangjiaxing
 * @Date: 2021/04/19 17:35
 * @Version 1.0
 */
public class PackNettyClient {

    /**
     * 连接服务器
     *
     * @param port
     * @param host
     * @throws Exception
     */
    public void connect(int port, String host) throws Exception {
        // 配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 客户端辅助启动类 对客户端配置
            Bootstrap b = new Bootstrap();
            b.group(group)//
                    .channel(NioSocketChannel.class)//
                    .option(ChannelOption.TCP_NODELAY, true)//
                    .handler(new MyChannelHandler());//
            // 异步链接服务器 同步等待链接成功
            ChannelFuture f = b.connect(host, port).sync();

            // 等待链接关闭
            f.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();
            System.out.println("客户端优雅的释放了线程资源...");
        }

    }

    /**
     * 网络事件处理器
     */
    private class MyChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            // 添加自定义协议的编解码工具
            ch.pipeline().addLast(new StarEncoder());
            ch.pipeline().addLast(new StarDecoder());
            // 处理网络IO
            ch.pipeline().addLast(new ClientHandler());
        }

    }

    public static void main(String[] args) throws Exception {
        new PackNettyClient().connect(9999, "127.0.0.1");
    }

}
