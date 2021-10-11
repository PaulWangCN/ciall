package com.wangjx.ciall.file.log;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: LogReader
 * @Description: 日志实时读取类
 * @Author: wangjiaxing
 * @Date: 2021/10/09 11:46
 * @Version 1.0
 */
public class LogReader {

    private long pointer = 0; //上次文件大小
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

    ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

    public void realtimeShowLog(File logFile) throws Exception{

        if(logFile == null) {
            throw new IllegalStateException("logFile can not be null");
        }

        //启动一个线程每2秒读取新增的日志信息
        exec.scheduleWithFixedDelay(() -> {

            //获得变化部分
            try {

                long len = logFile.length();
                if(len < pointer){
                    System.out.println("Log file was reset. Restarting logging from start of file.");
                    pointer = 0;
                }else{

                    //指定文件可读可写
                    RandomAccessFile randomFile= new RandomAccessFile(logFile,"rw");

                    //获取RandomAccessFile对象文件指针的位置，初始位置是0
                    System.out.println("RandomAccessFile文件指针的初始位置:"+pointer);

                    randomFile.seek(pointer);//移动文件指针位置

                    String tmp = "";
                    while((tmp = randomFile.readLine()) != null) {
                        System.out.println("info : " +new String(tmp.getBytes("utf-8")));
                        pointer = randomFile.getFilePointer();
                    }

                    randomFile.close();
                }

            } catch (Exception e) {
                //实时读取日志异常，需要记录时间和lastTimeFileSize 以便后期手动补充
                System.out.println(dateFormat.format(new Date())  + " File read error, pointer: "+pointer);
            } finally {
                //将pointer 落地以便下次启动的时候，直接从指定位置获取
            }
        }, 0, 10, TimeUnit.SECONDS);

    }
    public void stop(){
        if(exec != null){
            exec.shutdown();
            System.out.println("file read stop ！");
        }
    }


    public static void main(String[] args) throws Exception {

        LogReader view = new LogReader();
        final File tmpLogFile = new File("./mock.log");
        view.pointer = 0;
        view.realtimeShowLog(tmpLogFile);

    }

}
