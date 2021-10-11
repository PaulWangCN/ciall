//package com.wangjx.ciall.file.log;
//
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.util.concurrent.TimeUnit;
//
///**
// * @ClassName: LogWriterTest
// * @Description: TODO
// * @Author: wangjiaxing
// * @Date: 2021/10/11 18:10
// * @Version 1.0
// */
//public class LogWriterTest {
//
//    @Test
//    public void test() {
//        final LogWriter logWriter = new LogWriter();
//        final File tmpLogFile = new File("./mock.log");
//        final String msgInfo = "test !";
//
//        //启动一个线程每5秒向日志文件写一次数据
//        LogWriter.exec.scheduleWithFixedDelay(new Runnable(){
//
//            @Override
//            public void run() {
//                try {
//                    logWriter.logMsg(tmpLogFile, msgInfo);
//                    Thread.sleep(1000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    System.out.println("file write error ！");
//                }
//            }
//
//        }, 0, 5, TimeUnit.SECONDS);
//    }
//
//}
