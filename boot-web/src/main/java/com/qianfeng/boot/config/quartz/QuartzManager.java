package com.qianfeng.boot.config.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

/**
 * quincey
 * Date 2020/5/28 23:36
 */

@Component
public class QuartzManager {
    public Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();

    public QuartzManager() throws SchedulerException {
    }

    public boolean addJob(Job job, String jobName, String jobGroup, String triggerName, String triggerGroupName, String cron, JobDataMap... map) throws Exception {
        JobDetail jobDetail;
        if (map == null || map.length == 0) {

            jobDetail = JobBuilder.newJob(job.getClass())
                    .withIdentity(jobName, jobGroup)// 这个操作就是以为方便从任务池里边取出来对人物进行关闭，修改之类的
                    .build();
        } else {
            jobDetail = JobBuilder.newJob(job.getClass())
                    .usingJobData(map[0])
                    .withIdentity(jobName, jobGroup)// 这个操作就是以为方便从任务池里边取出来对人物进行关闭，修改之类的
                    .build();
        }
        // 第二步：创建一个CronTrigger
        Trigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .withIdentity(triggerName, triggerGroupName)// 这个给触发器取个唯一的名字，方便以后修改触发的时间表达式。
                .build();
        // 第三步：创建一个Scheduler的调度器去把这两个联系起来
        defaultScheduler.scheduleJob(jobDetail, cronTrigger);
        // 最后启动
        defaultScheduler.start();
        return true;
    }


    public boolean addJob(Class<? extends Job> jobClass, String jobName, String jobGroup, String triggerName, String triggerGroupName, String cron, JobDataMap... map) throws Exception {
        JobDetail jobDetail;
        if (map == null || map.length == 0) {
            jobDetail = JobBuilder.newJob(jobClass)
                    .withIdentity(jobName, jobGroup)// 这个操作就是以为方便从任务池里边取出来对人物进行关闭，修改之类的
                    .build();
        } else {
            jobDetail = JobBuilder.newJob(jobClass)
                    .usingJobData(map[0])
                    .withIdentity(jobName, jobGroup)// 这个操作就是以为方便从任务池里边取出来对人物进行关闭，修改之类的
                    .build();
        }
        // 第二步：创建一个CronTrigger
        Trigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .withIdentity(triggerName, triggerGroupName)// 这个给触发器取个唯一的名字，方便以后修改触发的时间表达式。
                .build();
        // 第三步：创建一个Scheduler的调度器去把这两个联系起来
        defaultScheduler.scheduleJob(jobDetail, cronTrigger);
        // 最后启动
        defaultScheduler.start();
        return true;
    }

    public boolean deleteJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName, jobGroup);
        try {
            return defaultScheduler.deleteJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateJob(String triggerName, String triggerGroupName, String newCron) {
        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName); // 获取老的触发器
        // 第二步：创建一个CronTrigger
        Trigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(newCron))
                .withIdentity(triggerName, triggerGroupName)// 这个给触发器取个唯一的名字，方便以后修改触发的时间表达式。
                .build();
        try {
            defaultScheduler.rescheduleJob(triggerKey, cronTrigger);
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }
}
