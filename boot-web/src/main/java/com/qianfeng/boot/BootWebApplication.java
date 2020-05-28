package com.qianfeng.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = {"com.qianfeng.boot.dao"})// 将指定路径下面的dao动态代理生成代理实现类交给spring容器管理
@EnableScheduling
public class BootWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootWebApplication.class, args);
    }
}