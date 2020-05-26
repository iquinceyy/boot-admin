package com.qianfeng.boot.task;

/**
 * quincey
 * Date 2020/5/26 22:55
 */

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringTaskTest {
    @Scheduled(cron = "3/4 * * * * ?")
    public void addUser(){//单线程
        System.out.println("定时添加用户"+System.currentTimeMillis()/1000);
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 用在微信公众号 dev 刷新js的key 默认存活时间是两小时。
    @Scheduled(fixedRate = 5000)// 每过5000毫秒就执行这个方法，不管你方法执行了多久
    public void task2(){
        // 如果这个方法开始的时候是0秒开始的
        // 执行这个方法竟然花了三秒，请问，下次执行这个方法，时间是多少
        // 是5秒
        System.out.println("fixedRate"+System.currentTimeMillis()/1000);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 5000)// 每过5000毫秒就执行这个方法，但是是以方法运行完之后开始算
    public void task3(){
        // 如果这个方法开始的时候是0秒开始的
        // 执行这个方法竟然花了三秒，请问，下次执行这个方法，时间是多少
        // 是8秒
        System.out.println("fixedDelay"+System.currentTimeMillis()/1000);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
