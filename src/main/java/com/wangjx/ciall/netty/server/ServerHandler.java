package com.wangjx.ciall.netty.server;

import com.google.gson.Gson;
import com.wangjx.ciall.netty.common.ConstantValue;
import com.wangjx.ciall.netty.common.StarData;
import com.wangjx.ciall.netty.common.StarProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    // 用于获取客户端发送的信息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // 用于获取客户端发来的数据信息
        StarProtocol body = (StarProtocol) msg;
        System.out.println("Server接受的客户端的信息 :" + body.toString());

        // 会写数据给客户端
        StarData starData = new StarData();
        starData.setType(ConstantValue.DATA_TYPE_TEST);
        starData.setMsg("Hi I am Server ...");
        Gson gson = new Gson();
        String str = gson.toJson(starData);
        StarProtocol response = new StarProtocol(str.getBytes().length,
                str.getBytes());
        // 当服务端完成写操作后，关闭与客户端的连接
        ctx.writeAndFlush(response);
        // .addListener(ChannelFutureListener.CLOSE);

        // 当有写操作时，不需要手动释放msg的引用
        // 当只有读操作时，才需要手动释放msg的引用
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // cause.printStackTrace();
        ctx.close();
    }
}
