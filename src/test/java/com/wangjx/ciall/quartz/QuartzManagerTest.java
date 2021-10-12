package com.wangjx.ciall.quartz;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: QuartzManagerTest
 * @Description: quartz管理器测试
 * @Author: wangjiaxing
 * @Date: 2021/10/12 09:59
 * @Version 1.0
 */
public class QuartzManagerTest {

    @Test
    public void test() throws InterruptedException {
        String jobName = "test-job";
        String jobGroupName = "test-job-group";
        String triggerName = "test-trigger";
        String triggerGroupName = "test-trigger-group";
        System.out.println("准备添加一个10秒执行一次的定时任务：MyJob.class");
        QuartzManager.addJob(jobName, jobGroupName, triggerName, triggerGroupName, MyJob.class, "0/10 * * * * ?");
        System.out.println("添加完成，主线程休眠60秒");
        TimeUnit.SECONDS.sleep(60);
        System.out.println("60秒休眠完成，准备修改任务执行间隔为5秒");
        QuartzManager.modifyJobTime(jobName, jobGroupName, triggerName, triggerGroupName, "0/5 * * * * ?");
        System.out.println("修改完成，主线程休眠60秒");
        TimeUnit.SECONDS.sleep(60);
        System.out.println("60秒休眠完成，准备暂停定时任务");
        QuartzManager.pauseJob(jobName, jobGroupName, triggerName, triggerGroupName);
        System.out.println("暂停完成，主线程休眠15秒");
        TimeUnit.SECONDS.sleep(15);
        System.out.println("15秒休眠完成，准备恢复定时任务");
        QuartzManager.resumeJob(jobName, jobGroupName, triggerName, triggerGroupName);
        System.out.println("恢复完成，主线程休眠15秒");
        TimeUnit.SECONDS.sleep(15);
        System.out.println("15秒休眠完成，准备删除定时任务");
        QuartzManager.removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
        System.out.println("删除完成，主线程休眠15秒");
        System.out.println("15秒休眠完成，测试结束");
    }

}
