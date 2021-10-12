package com.wangjx.ciall.netty.common;

/**
 * @ClassName: StarData
 * @Description: 自定义数据格式
 * @Author: wangjiaxing
 * @Date: 2021/04/21 17:06
 * @Version 1.0
 */
public class StarData {

    private int type;
    private String msg;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
