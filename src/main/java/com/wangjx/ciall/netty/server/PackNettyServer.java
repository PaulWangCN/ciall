package com.wangjx.ciall.netty.server;

import com.wangjx.ciall.netty.common.StarDecoder;
import com.wangjx.ciall.netty.common.StarEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @ClassName: PackNettyServer
 * @Description: 自定义协议netty服务端
 * @Author: wangjiaxing
 * @Date: 2021/04/19 17:36
 * @Version 1.0
 */
public class PackNettyServer {

    public void bind(int port) throws Exception {
        // 配置NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 服务器辅助启动类配置
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChildChannelHandler())//
                    .option(ChannelOption.SO_BACKLOG, 1024) // 设置tcp缓冲区 // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
            // 绑定端口 同步等待绑定成功
            ChannelFuture f = b.bind(port).sync(); // (7)
            // 等到服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅释放线程资源
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    /**
     * 网络事件处理器
     */
    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            // 添加自定义协议的编解码工具
            ch.pipeline().addLast(new StarEncoder());
            ch.pipeline().addLast(new StarDecoder());
            // 处理网络IO
            ch.pipeline().addLast(new ServerHandler());
        }
    }

    public static void main(String[] args) throws Exception {
        new PackNettyServer().bind(9999);
    }

}
