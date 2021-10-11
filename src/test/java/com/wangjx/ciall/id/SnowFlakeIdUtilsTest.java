package com.wangjx.ciall.id;

import org.junit.jupiter.api.Test;

/**
 * @ClassName: SnowFlakeIdUtilsTest
 * @Description: 雪花ID工具类测试
 * @Author: wangjiaxing
 * @Date: 2021/10/11 16:35
 * @Version 1.0
 */
public class SnowFlakeIdUtilsTest {

    @Test
    public void test() throws InterruptedException {
        SnowflakeIdUtils idWorker = new SnowflakeIdUtils(0, 0);
        for (int i = 0; i < 10; i++) {
            long id = idWorker.nextId();
            Thread.sleep(1);
            System.out.println("生成ID：" + id);
        }
    }

}
