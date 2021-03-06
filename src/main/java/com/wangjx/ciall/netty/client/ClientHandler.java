package com.wangjx.ciall.netty.client;

import com.google.gson.Gson;
import com.wangjx.ciall.netty.common.ConstantValue;
import com.wangjx.ciall.netty.common.StarData;
import com.wangjx.ciall.netty.common.StarProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

//用于读取客户端发来的信息
public class ClientHandler extends ChannelInboundHandlerAdapter {

    // 客户端与服务端，连接成功的售后
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 发送SmartCar协议的消息
        // 要发送的信息
        StarData starData = new StarData();
        starData.setType(ConstantValue.DATA_TYPE_TEST);
        starData.setMsg("I am client ...");
        Gson gson = new Gson();
        String data = gson.toJson(starData);
        // 获得要发送信息的字节数组
        byte[] content = data.getBytes();
        // 要发送信息的长度
        int contentLength = content.length;

        StarProtocol protocol = new StarProtocol(contentLength, content);

        ctx.writeAndFlush(protocol);
    }

    // 只是读数据，没有写数据的话
    // 需要自己手动的释放的消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            // 用于获取客户端发来的数据信息
            StarProtocol body = (StarProtocol) msg;
            System.out.println("Client接受的客户端的信息 :" + body.toString());

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        ctx.close();
    }

}
