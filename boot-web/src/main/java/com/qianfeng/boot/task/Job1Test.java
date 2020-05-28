package com.qianfeng.boot.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Author quincey
 * Date 2020/5/27 19:02
 */
public class Job1Test implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.err.println("这个是定时任务1");
    }
}
