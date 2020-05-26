package com.qianfeng.boot.config.resolver;


import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Author quincey
 * Date 2020/5/22 16:40
 */
//@Component("localeResolver")// 明确加上它不行
// myLocalResolver 不会被认为是一个 国际化的语言配置类，springMVC种语言配置类的名称 只能叫localeResolver它，所以这里要指定名称才会生效
public class MyLocalResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {// 就可以根据前端的需求来改变我们的国际化语言
//        System.err.println(request.getRequestURI());// 获取请求路径
        // 接收语言的参数 - 传进来的就是形如'zh_CN'这样的参数
        String lan = request.getParameter("lan");
        // 使用默认的语言 - 在文中就是login.properties文件里配置的
        Locale locale = Locale.getDefault();
        // 判断接收的参数是否为空，不为空就设置为该语言
        if (!StringUtils.isEmpty(lan)) {
            // 将参数分隔 - 假设传进来的是'zh_CN'
            String[] s = lan.split("_");
            // 语言编码:zh   地区编码:CN
            locale = new Locale(s[0], s[1]);
        }
        return locale;

    }
    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {
    }
}

