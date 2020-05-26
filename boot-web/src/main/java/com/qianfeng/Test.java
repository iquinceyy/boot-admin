package com.qianfeng;

import com.qianfeng.boot.task.QuartzTaskTest;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * quincey
 * Date 2020/5/26 23:17
 */


public class Test {
    public static void main(String[] args) throws SchedulerException {
        //第一步创建好的job实现类（具体的定时任务的业务代码）
        JobDataMap map = new JobDataMap();
        map.put("orderId","123");
        map.put("name","张三");

        JobDetail jobDetail = JobBuilder.newJob(QuartzTaskTest.class)
                .usingJobData(map)
                .withIdentity("取消定时任务","取消订单任务组1")// 这个操作就是以为方便从任务池里边取出来对人物进行关闭，修改之类的
                .build();

        // 第二步：创建一个CronTrigger
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .withIdentity("触发器的名字", "触发器的组名称")// 这个给触发器取个唯一的名字，方便以后修改触发的时间表达式。
                .build();

        //第三步：创建一个Scheduler的调度器去把这两个联系起来
        Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
        defaultScheduler.scheduleJob(jobDetail,cronTrigger);
        //最后启动
        defaultScheduler.start();
    }

}
