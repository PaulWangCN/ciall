package com.wangjx.ciall.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

/**
 * @ClassName: MyJob
 * @Description: 自定义定时任务类
 * @Author: wangjiaxing
 * @Date: 2021/10/12 10:02
 * @Version 1.0
 */
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(LocalDateTime.now() + ": MyJob执行execute方法");
    }
}
