package com.wangjx.ciall.file.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: LogWriter
 * @Description: 日志实时写入类
 * @Author: wangjiaxing
 * @Date: 2021/10/09 11:45
 * @Version 1.0
 */
public class LogWriter {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    private static ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

    public void logMsg(File logFile, String msgInfo) throws IOException {
        if(!logFile.exists()) {
            logFile.createNewFile();
        }
        Writer txtWriter = new FileWriter(logFile,true);
        txtWriter.write(dateFormat.format(new Date()) + "\t" + msgInfo + "\n");
        txtWriter.flush();
        txtWriter.close();
    }

    public void stop(){
        if(exec != null){
            exec.shutdown();
            System.out.println("file write stop ！");
        }
    }

    public static void main(String[] args) throws Exception {

        final LogWriter logWriter = new LogWriter();
        final File tmpLogFile = new File("./mock.log");
        final String msgInfo = "test !";

        //启动一个线程每5秒向日志文件写一次数据
        exec.scheduleWithFixedDelay(() -> {
            try {
                logWriter.logMsg(tmpLogFile, msgInfo);
                //Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("file write error ！");
            }
        }, 0, 5, TimeUnit.SECONDS);

    }


}
