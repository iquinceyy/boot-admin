package com.qianfeng.boot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Security;

/**
 * quincey
 * Date 2020/5/28 23:22
 */

@Controller
public class LoginController {
    @RequestMapping("/login")
    String login(Model model){// 这个方法是执行登录操作的
        //获取subject
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("18223170162", String.valueOf(123456));
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(usernamePasswordToken);// 只要 执行login方法，那么它就会跑到userRealm里边的认证方法（doGetAuthenticationInfo）

        }catch (AuthenticationException a){
            model.addAttribute("errorMsg",a.getMessage());
            return "loginPage";
        }
        return "pages/back/home";
    }

    // 这个方法是跳转到登录页面用的
    @RequestMapping("/loginPage")
    String loginPage(){
        return "loginPage";
    }
}
