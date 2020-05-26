package com.qianfeng.boot.config.resolver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

/**
 * Author quincey
 * Date 2020/5/22 16:40
 */
@Configuration
public class ResolverConfig {

    //    @Bean("lsdhsuyghhuyocaleResolver")// 这样不行
    @Bean // 这样可以
    public LocaleResolver localeResolver() {// 配置国际化语言的支持
        return new MyLocalResolver();
    }
}