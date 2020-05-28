package com.qianfeng.boot.controller;

import com.qianfeng.boot.config.quartz.QuartzManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * quincey
 * Date 2020/5/28 23:33
 */

@Controller
public class TestController {

    @Resource
    public QuartzManager quartzManager;

    @RequestMapping("/test")
    Object test() {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("1", "s");
        SecurityUtils.getSubject().login(usernamePasswordToken);
        return "定时任务创建失败";
    }

    @RequestMapping("/unAuthPage")
    String unAuthPage() {
        return "unAuthPage";
    }

    @RequiresPermissions("admin:goodsssss")
    @RequestMapping("abc")
    String abc() {
        return "abc";
    }
}
